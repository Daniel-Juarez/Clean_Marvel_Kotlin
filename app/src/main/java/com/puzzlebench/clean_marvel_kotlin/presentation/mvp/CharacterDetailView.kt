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
        val fragment = fragmentRef.get()
        if (fragment != null) {
            showLoading()
            idCharacter = fragment.idCharacter
            fragment.closeButton.setOnClickListener{
                closeDialog()
            }
        }

    }

    fun showToastNoItemToShow() {
        val fragment = fragmentRef.get()
        if (fragment != null) {
            val message = fragment.activity.resources.getString(R.string.message_no_items_to_show)
            fragment.activity.showToast(message)

        }
    }

    fun showToastNetworkError(error: String) {
        fragmentRef.get()!!.activity.showToast(error)
    }

    fun hideLoading() {
        fragmentRef.get()!!.progressBarDetail.visibility = View.GONE
    }

    fun showCharacters(character: Character) {
        val fragment = fragmentRef.get()
        if (fragment != null) {
            fragment.textName.text = character.name
            fragment.textDescription.text = character.description
            val string = character.thumbnail.path + "." + character.thumbnail.extension
            fragment.image_thumbnail_detail.getImageByUrl(string)
        }


    }

    fun showLoading() {
        fragmentRef.get()!!.progressBarDetail.visibility = View.VISIBLE
    }

    fun closeDialog(){
        fragmentRef.get()!!.dismiss()
    }
}
