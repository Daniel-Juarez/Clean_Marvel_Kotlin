package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

//TODO fix on second iteration
// error: However, there was exactly 1 interaction with this mock:
class CharacterDetailPresenterTest {
    private var view = mock(CharacterDetailView::class.java)
    private var characterServiceImp = mock(CharacterServicesImpl::class.java)
    private lateinit var characterDetailPresenter: CharacterDetailPresenter
    private lateinit var getCharacterDetailServiceUseCase: GetCharacterDetailServiceUseCase


    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        getCharacterDetailServiceUseCase = GetCharacterDetailServiceUseCase(characterServiceImp)
        val subscriptions = mock(CompositeDisposable::class.java)
        characterDetailPresenter = CharacterDetailPresenter(view, getCharacterDetailServiceUseCase, subscriptions)
    }

    @Test
    fun reposeWithError() {
        Mockito.`when`(getCharacterDetailServiceUseCase.invoke(1)).thenReturn(Single.error(Exception("")))
        characterDetailPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacterDetail(1)
        verify(view).hideLoading()
        verify(view).showToastNetworkError("")

    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharacters = CharactersFactory.getMockCharacterList()
        val observable = Single.just(itemsCharacters)
        Mockito.`when`(getCharacterDetailServiceUseCase.invoke(1)).thenReturn(observable)
        Mockito.`when`(view?.idCharacter).thenReturn(1)
        characterDetailPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacterDetail(1)
        verify(view).hideLoading()
        verify(view).showCharacters(itemsCharacters[0])
    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharacters = emptyList<Character>()
        val observable = Single.just(itemsCharacters)
        Mockito.`when`(getCharacterDetailServiceUseCase.invoke(1)).thenReturn(observable)
        Mockito.`when`(view?.idCharacter).thenReturn(1)
        characterDetailPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacterDetail(1)
        verify(view).hideLoading()
        verify(view).showToastNoItemToShow()
    }
}
