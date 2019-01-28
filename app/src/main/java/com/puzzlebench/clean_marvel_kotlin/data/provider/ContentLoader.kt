package com.puzzlebench.clean_marvel_kotlin.data.provider

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.data.provider.CharacterProvider.Companion.DESCRIPTION_CHARACTER_PROVIDER_COLUMN
import com.puzzlebench.clean_marvel_kotlin.data.provider.CharacterProvider.Companion.EXTENSION_CHARACTER_PROVIDER_COLUMN
import com.puzzlebench.clean_marvel_kotlin.data.provider.CharacterProvider.Companion.ID_CHARACTER_PROVIDER_COLUMN
import com.puzzlebench.clean_marvel_kotlin.data.provider.CharacterProvider.Companion.NAME_CHARACTER_PROVIDER_COLUMN
import com.puzzlebench.clean_marvel_kotlin.data.provider.CharacterProvider.Companion.PATH_CHARACTER_PROVIDER_COLUMN
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharecterView


class ContentLoader(val context: MainActivity, val view: CharecterView) : LoaderManager.LoaderCallbacks<Cursor> {

    private val showCharactersUpdate:ShowCharactersInterface = view

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        return CursorLoader(context,CharacterProvider.CONTENT_URI,null,null,null,null)
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>?, cursor: Cursor?) {
        var characters:MutableList<Character> = mutableListOf()

        cursor?.let{
            it.moveToFirst()
            while (!it.isAfterLast()) {
                characters.add(
                        Character(
                                it.getInt(it.getColumnIndex(ID_CHARACTER_PROVIDER_COLUMN)),
                                it.getString(it.getColumnIndex(NAME_CHARACTER_PROVIDER_COLUMN)),
                                it.getString(it.getColumnIndex(DESCRIPTION_CHARACTER_PROVIDER_COLUMN)),
                                    Thumbnail(
                                            it.getString(it.getColumnIndex(PATH_CHARACTER_PROVIDER_COLUMN)),
                                            it.getString(it.getColumnIndex(EXTENSION_CHARACTER_PROVIDER_COLUMN)))
                        )
                )
                it.moveToNext()
            }
        }

        showCharactersUpdate.updateCharacter(characters)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface ShowCharactersInterface{
        fun updateCharacter(characters: List<Character>)
    }
}
