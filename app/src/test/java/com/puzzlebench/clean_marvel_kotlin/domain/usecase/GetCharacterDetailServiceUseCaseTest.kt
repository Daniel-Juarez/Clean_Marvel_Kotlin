package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GetCharacterDetailServiceUseCaseTest {
    private lateinit var characterServiceImp: CharacterServicesImpl

    @Before
    fun setUp() {
        val characterItems = CharactersFactory.getMockCharacterSingle()
        val observable = Single.just(characterItems)
        characterServiceImp = mock(CharacterServicesImpl::class.java)
        characterServiceImp.stub {
            on { characterServiceImp.getCharacterDetail(1) } doReturn observable
        }
    }

    @Test
    fun `Test character detail service use case`() {
        val getCharacterDetailServiceUseCase = GetCharacterDetailServiceUseCase(characterServiceImp)
        getCharacterDetailServiceUseCase(1)
        verify(characterServiceImp).getCharacterDetail(1)
    }
}
