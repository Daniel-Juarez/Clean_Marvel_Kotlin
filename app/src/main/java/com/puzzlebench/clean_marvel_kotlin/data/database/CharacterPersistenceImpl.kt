package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.data.database.model.CharacterDB
import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperPersistence
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm

class CharacterPersistenceImpl(val mapper: CharacterMapperPersistence = CharacterMapperPersistence()) : CharacterPersistence{

    override fun saveCharacter(listCharacters: List<Character>) = Completable.fromCallable {
        Realm.getDefaultInstance().use{ realm ->
            realm.beginTransaction()
            mapper.transform(listCharacters).map { realm.copyToRealmOrUpdate(it) }
            realm.commitTransaction()
        }
    }

    override fun listCharacters() =  Single.fromCallable {
        var characterList = emptyList<Character>()

        Realm.getDefaultInstance().use{ realm ->
            val query = realm.where(CharacterDB::class.java)
             characterList = mapper.transformDB(query.findAll().toList())
        }
        characterList
    }
}
