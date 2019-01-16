package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServices
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Single

class GetCharacterServiceUseCase(private val characterService: CharacterServices) {
   operator fun invoke(): Single<List<Character>> = characterService.getCharacters()
}
