package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetCharacterServiceUseCaseTest {

    private lateinit var characterServiceImp: CharacterServicesImpl

    @Before
    fun setUp() {
        val videoItems = CharactersFactory.getMockCharacterList()
        val observable = Single.just(videoItems)
        characterServiceImp = mock(CharacterServicesImpl::class.java)
        `when`(characterServiceImp.getCharacters()).thenReturn(observable)

    }

    @Test operator fun invoke() {
        val getCharacterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        getCharacterServiceUseCase.invoke()
        verify(characterServiceImp).getCharacters()
    }
}
