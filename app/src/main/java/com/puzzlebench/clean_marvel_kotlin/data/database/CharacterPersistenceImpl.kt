package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperPersistence
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Single
import io.realm.Realm

class CharacterPersistenceImpl(private val mapper: CharacterMapperPersistence = CharacterMapperPersistence()) : CharacterPersistence{

    override fun saveCharacter(listCharacters: List<Character>): Single<Boolean> {
        return Single.fromCallable {
            val listToSave = mapper.transform(listCharacters)

            Realm.getDefaultInstance().use{ realm ->
                realm.beginTransaction()
                listToSave.map { realm.copyToRealmOrUpdate(it) }
                realm.commitTransaction()
            }
            true
        }
    }
}
