package com.puzzlebench.clean_marvel_kotlin.data.database

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Single

interface CharacterPersistence {
    fun saveCharacter(listCharacters: List<Character>): Single<Boolean>
    fun listCharacters(offset: Int = 10): Single<List<Character>>
}
