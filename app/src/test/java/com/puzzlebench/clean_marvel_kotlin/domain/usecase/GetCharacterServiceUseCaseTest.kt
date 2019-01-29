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

class GetCharacterServiceUseCaseTest {

    private lateinit var characterServiceImp: CharacterServicesImpl

    @Before
    fun setUp() {
        val videoItems = CharactersFactory.getMockCharacterList()
        val observable = Single.just(videoItems)
        characterServiceImp = mock(CharacterServicesImpl::class.java)

        characterServiceImp.stub {
            on { characterServiceImp.getCharacters() } doReturn observable
        }
    }

    @Test
    fun `Test get list characters service use case`() {
        val getCharacterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        getCharacterServiceUseCase()
        verify(characterServiceImp).getCharacters()
    }
}
