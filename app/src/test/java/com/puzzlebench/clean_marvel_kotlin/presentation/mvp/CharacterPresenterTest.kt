package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.data.database.CharacterPersistenceImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

//TODO fix on second iteration
// error: However, there was exactly 1 interaction with this mock:
class CharacterPresenterTest {

    private var view = mock(CharecterView::class.java)
    private var characterServiceImp = mock(CharacterServicesImpl::class.java)
    private var characterPersistenceImpl = mock(CharacterPersistenceImpl::class.java)
    private lateinit var characterPresenter: CharacterPresenter
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var getCharacterSaveUseCase: GetCharacterSaveUseCase


    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        getCharacterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        getCharacterSaveUseCase = GetCharacterSaveUseCase(characterPersistenceImpl)
        val subscriptions = mock(CompositeDisposable::class.java)
        characterPresenter = CharacterPresenter(view, getCharacterServiceUseCase, getCharacterSaveUseCase, subscriptions)
    }

    @Test
    fun reposeWithError() {
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(Single.error(Exception("")))
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacters()
        verify(view).hideLoading()
        verify(view).showToastNetworkError("")
    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharacters = CharactersFactory.getMockCharacterList()
        val observable = Single.just(itemsCharacters)
        val observableSave = Single.just(true)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        Mockito.`when`(getCharacterSaveUseCase.invoke(itemsCharacters)).thenReturn(observableSave)
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacters()
        verify(view).hideLoading()
        verify(view).sendUpdateList()
    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharacters = emptyList<Character>()
        val observable = Single.just(itemsCharacters)
        val observableSave = Single.just(true)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        Mockito.`when`(getCharacterSaveUseCase.invoke(itemsCharacters)).thenReturn(observableSave)

        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacters()
        verify(view).hideLoading()
        verify(view).showToastNoItemToShow()
    }
}
