package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServices
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Single

class GetCharacterDetailServiceUseCase(private val characterService: CharacterServices) {
   operator fun invoke(id: Int): Single<List<Character>> = characterService.getCharacterDetail(id)
}
