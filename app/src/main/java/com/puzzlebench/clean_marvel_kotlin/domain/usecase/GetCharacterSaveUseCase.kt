package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import android.util.Log
import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistenceImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class GetCharacterSaveUseCase(private val characterPersistenceImpl: CharacterPersistenceImpl) {

   open operator fun invoke(characters: List<Character>): Observable<Boolean> = characterPersistenceImpl.saveCharacter(characters)
}