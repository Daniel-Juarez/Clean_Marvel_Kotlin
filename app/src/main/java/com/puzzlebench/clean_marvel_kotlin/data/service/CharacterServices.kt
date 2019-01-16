package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable
import io.reactivex.Single

interface CharacterServices {
    fun getCharacters(): Single<List<Character>>
    fun getCharacterDetail(id: Int): Single<List<Character>>
}
