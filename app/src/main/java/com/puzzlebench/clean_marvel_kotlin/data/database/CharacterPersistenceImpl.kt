package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperPersistence
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable
import io.realm.Realm
import io.realm.exceptions.RealmException

class CharacterPersistenceImpl(private val mapper: CharacterMapperPersistence = CharacterMapperPersistence()){

    fun saveCharacter(listCharacters: List<Character>): Observable<Boolean> {
        return Observable.create { subscriber ->
            val listToSave = mapper.transform(listCharacters)
            val realm = Realm.getDefaultInstance()
            try {
                realm.beginTransaction()
                listToSave.map { realm.copyToRealmOrUpdate(it) }
                realm.commitTransaction()

                subscriber.onNext(true)
                subscriber.onComplete()
            }catch (e: RealmException) {
                subscriber.onError(Throwable(e.message))
            }
        }
    }
}