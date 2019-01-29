package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistenceImpl
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class GetCharacterSaveUseCaseTest {
    private lateinit var characterPersistenceImpl: CharacterPersistenceImpl

    @Before
    fun setUp() {
        val characterItems = CharactersFactory.getMockCharacterSingle()
        val observable = Completable.complete()
        characterPersistenceImpl = mock(CharacterPersistenceImpl::class.java)

        characterPersistenceImpl.stub {
            on { characterPersistenceImpl.saveCharacter(characterItems) } doReturn observable
        }
    }

    @Test
    fun `Test save characters use case`() {
        val characterItems = CharactersFactory.getMockCharacterSingle()
        val getCharacterSaveUseCase = GetCharacterSaveUseCase(characterPersistenceImpl)
        getCharacterSaveUseCase(characterItems)
        verify(characterPersistenceImpl).saveCharacter(characterItems)
    }
}
