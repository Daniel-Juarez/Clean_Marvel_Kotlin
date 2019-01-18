package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistenceImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetCharacterSaveUseCaseTest {

    private lateinit var characterPersistenceImpl: CharacterPersistenceImpl

    @Before
    fun setUp() {
        val characterItems = CharactersFactory.getMockCharacterSingle()

        val observable = Single.just(true)
        characterPersistenceImpl = mock(CharacterPersistenceImpl::class.java)
        `when`(characterPersistenceImpl.saveCharacter(characterItems)).thenReturn(observable)

    }

    @Test operator fun invoke() {
        val characterItems = CharactersFactory.getMockCharacterSingle()

        val getCharacterSaveUseCase = GetCharacterSaveUseCase(characterPersistenceImpl)
        getCharacterSaveUseCase.invoke(characterItems)
        verify(characterPersistenceImpl).saveCharacter(characterItems)
    }
}
