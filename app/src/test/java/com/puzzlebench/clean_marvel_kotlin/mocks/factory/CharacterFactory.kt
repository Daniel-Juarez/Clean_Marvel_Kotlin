package com.puzzlebench.clean_marvel_kotlin.mocks.factory

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail


class CharactersFactory {

    companion object Factory {
        private const val BASE_ID = 1
        private const val BASE_NAME = "Name"
        private const val BASE_DESCRIPTION = "Description"
        private const val BASE_PATH = "image"
        private const val BASE_EXTENSION = ".png"

        open fun getMockCharacterList(): List<Character> {
            return listOf(1..5).map {
                Character(BASE_ID, "$BASE_NAME$it", "$BASE_DESCRIPTION$it", Thumbnail("$BASE_PATH$it", BASE_EXTENSION))
            }
        }

        open fun getMockCharacterSingle(): List<Character> {
            return listOf(1..1).map {
                Character(BASE_ID, "$BASE_NAME$it", "$BASE_DESCRIPTION$it", Thumbnail("$BASE_PATH$it", BASE_EXTENSION))
            }
        }

        open fun getMockCharacter(): Character {
            return Character(BASE_ID, "$BASE_NAME$", "$BASE_DESCRIPTION$", Thumbnail("$BASE_PATH$", BASE_EXTENSION))
        }
    }
}
