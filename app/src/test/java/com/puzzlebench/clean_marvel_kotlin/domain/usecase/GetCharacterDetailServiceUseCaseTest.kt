package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetCharacterDetailServiceUseCaseTest {

    private lateinit var characterServiceImp: CharacterServicesImpl

    @Before
    fun setUp() {
        val characterItems = CharactersFactory.getMockCharacterSingle()
        val observable = Single.just(characterItems)
        characterServiceImp = mock(CharacterServicesImpl::class.java)
        `when`(characterServiceImp.getCharacterDetail(1)).thenReturn(observable)

    }

    @Test operator fun invoke() {
        val getCharacterDetailServiceUseCase = GetCharacterDetailServiceUseCase(characterServiceImp)
        getCharacterDetailServiceUseCase.invoke(1)
        verify(characterServiceImp).getCharacterDetail(1)
    }

}