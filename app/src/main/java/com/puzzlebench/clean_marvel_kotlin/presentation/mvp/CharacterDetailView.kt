package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.DetailCharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character_cards_layout.view.*
import kotlinx.android.synthetic.main.fragment_detail_character.*
import kotlinx.android.synthetic.main.fragment_detail_character.view.*
import java.lang.ref.WeakReference

class CharacterDetailView(fragment: DetailCharacterFragment) {
    private val fragmentRef = WeakReference(fragment)
    var idCharacter:Int? = null

    fun init() {
        fragmentRef.get()?.let {
            showLoading()
            idCharacter = it.idCharacter
            it.button_detail_dismiss.setOnClickListener{
                closeDialog()
            }
        }
    }

    fun showToastNoItemToShow() {
        fragmentRef.get()?.let {
            val message = it.resources.getString(R.string.message_no_items_to_show)
            it.activity.showToast(message)
        }
    }

    fun showToastNetworkError(error: String) {
        fragmentRef.get()?.let { it.activity.showToast(error) }
    }

    fun hideLoading() {
        fragmentRef.get()?.let { it.progressbar_detail_character.visibility = View.GONE }
    }

    fun showCharacters(character: Character) {
        fragmentRef.get()?.let {
            it.text_detail_character_name?.text = character.name
            it.text_detail_character_description?.text = character.description
            val string = character?.thumbnail.path + "." + character?.thumbnail.extension
            it.image_detail_character_thumbnail?.getImageByUrl(string)
        }
    }

    fun showLoading() {
        fragmentRef.get()?.let { it.progressbar_detail_character.visibility = View.VISIBLE }
    }

    fun closeDialog(){
        fragmentRef.get()?.let { it.dismiss() }
    }
}
