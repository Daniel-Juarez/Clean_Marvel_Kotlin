package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistence
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Single

class GetCharacterSaveUseCase(private val characterPersistence: CharacterPersistence) {
   operator fun invoke(characters: List<Character>): Single<Boolean> = characterPersistence.saveCharacter(characters)
}
