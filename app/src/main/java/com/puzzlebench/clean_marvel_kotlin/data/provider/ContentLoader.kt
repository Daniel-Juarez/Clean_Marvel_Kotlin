package com.puzzlebench.clean_marvel_kotlin.data.provider

import android.app.LoaderManager
import android.content.Context
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.util.Log
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
                                it.getInt(0),
                                it.getString(1),
                                it.getString(2),
                                Thumbnail(it.getString(3),it.getString(4))
                        )
                )
                it.moveToNext()
            }
        }

        showCharactersUpdate.updateCharacter(characters)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>?) { }

    interface ShowCharactersInterface{
        fun updateCharacter(characters: List<Character>)
    }
}
