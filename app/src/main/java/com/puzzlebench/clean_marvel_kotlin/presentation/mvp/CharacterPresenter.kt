package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.view.View
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterSaveUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(view: CharecterView, private val getCharacterServiceUseCase: GetCharacterServiceUseCase, private val getCharacterSaveUseCase: GetCharacterSaveUseCase, val subscriptions: CompositeDisposable) : Presenter<CharecterView>(view) {

    fun init() {
        view.init()
        view.setOnClickListenerFloatButton(View.OnClickListener {
            view.showLoading()
            requestGetCharacters()
        })
        requestGetCharacters()
    }

    private fun requestGetCharacters() {
        view.showLoading()
        val subscription = getCharacterServiceUseCase().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ characters ->
            if (characters.isEmpty()) {
                view.showToastNoItemToShow()
            } else {
                saveCharactersOnDB(characters)
            }
            view.hideLoading()
        }, { requestError ->
            view.hideLoading()
            view.showToastNetworkError(requestError.message.toString())
        })
        subscriptions.add(subscription)
    }

    private fun saveCharactersOnDB(characters: List<Character>) {
        val subscription = getCharacterSaveUseCase(characters).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            view.showToastSaved()
            view.sendUpdateList()
        }  , {saveError ->
            view.showToastNetworkError(saveError.message.toString())
        })
        subscriptions.add(subscription)
    }
}
