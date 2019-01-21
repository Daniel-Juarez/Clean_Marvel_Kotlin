package com.puzzlebench.clean_marvel_kotlin.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import io.realm.Realm
import io.realm.RealmConfiguration
import android.database.MatrixCursor
import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistence
import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistenceImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Single


class CharacterProvider: ContentProvider(){

    companion object {
        val AUTHORITY = "com.puzzlebench.clean_marvel_kotlin.data.provider.CharacterProvider"
        private val CHARACTERS_TABLE = "characters"
        val CONTENT_URI : Uri = Uri.parse("content://$AUTHORITY/$CHARACTERS_TABLE")

        const val ID_CHARACTER_PROVIDER_COLUMN = "_id"
        const val NAME_CHARACTER_PROVIDER_COLUMN = "name"
        const val DESCRIPTION_CHARACTER_PROVIDER_COLUMN = "description"
        const val PATH_CHARACTER_PROVIDER_COLUMN = "path"
        const val EXTENSION_CHARACTER_PROVIDER_COLUMN = "extension"
    }

    private val CHARACTERS = 1
    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        sURIMatcher.addURI(AUTHORITY, CHARACTERS_TABLE, CHARACTERS)
    }

    override fun onCreate(): Boolean {
        val realmConfiguration = RealmConfiguration.Builder(context)
        realmConfiguration.name("character")
        realmConfiguration.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(realmConfiguration.build())
        return false
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val cursor = MatrixCursor(arrayOf(ID_CHARACTER_PROVIDER_COLUMN, NAME_CHARACTER_PROVIDER_COLUMN, DESCRIPTION_CHARACTER_PROVIDER_COLUMN, PATH_CHARACTER_PROVIDER_COLUMN, EXTENSION_CHARACTER_PROVIDER_COLUMN))

        val characters = CharacterPersistenceImpl().listCharacters().blockingGet()
        for (character in characters) {
            cursor.addRow(arrayOf(character.id,character.name,character.description,character.thumbnail.path, character.thumbnail.extension))
        }
        cursor.setNotificationUri(context.contentResolver,CONTENT_URI)

        return cursor
    }

    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(p0: Uri?): String? {
        return null
    }
}
