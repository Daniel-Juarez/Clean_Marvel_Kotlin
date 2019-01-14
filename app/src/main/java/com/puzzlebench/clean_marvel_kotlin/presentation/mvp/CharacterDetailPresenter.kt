package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailPresenter(view: CharacterDetailView, private val getChatacterDetailServiceUseCase: GetCharacterDetailServiceUseCase, val subscriptions: CompositeDisposable): Presenter<CharacterDetailView>(view) {
    fun init() {
        view.init()
        requestGetCharacterDetail()
    }

    private fun requestGetCharacterDetail() {
        val subscription = getChatacterDetailServiceUseCase.invoke(view.idCharacter!!).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ character ->
            if (character.isEmpty()) {
                view.showToastNoItemToShow()
            } else {
                view.showCharacters(character[0])
            }
            view.hideLoading()

        }, { e ->
            view.hideLoading()
            view.showToastNetworkError(e.message.toString())
        })
        subscriptions.add(subscription)
    }
}
