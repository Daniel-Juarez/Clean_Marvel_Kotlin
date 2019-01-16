package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.DetailCharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class CharecterView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private val SPAN_COUNT = 1
    var adapter = CharacterAdapter { character ->
        val fragment = DetailCharacterFragment.newInstance(character.id)
        fragment.show(activity.fragmentManager,"DetailDialog")
    }

    fun init() {
        activityRef.get()?.let {
            it.recycleview_list_characters.layoutManager = GridLayoutManager(it, SPAN_COUNT)
            it.recycleview_list_characters.adapter = adapter
        }
        showLoading()
    }

    fun showToastNoItemToShow() {
        activityRef.get()?.let {
            val message = it.baseContext.resources.getString(R.string.message_no_items_to_show)
            it.applicationContext.showToast(message)
        }
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()?.let { it.applicationContext.showToast(error) }
    }

    fun hideLoading() {
        activityRef.get()?.let { it.progressbar_list_loader.visibility = View.GONE }
    }

    fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    fun showLoading() {
        activityRef.get()?.let { it.progressbar_list_loader.visibility = View.VISIBLE }
    }

    fun setOnClickListenerFloatButton(clickListener: View.OnClickListener) {
        activityRef.get()?.let { it.button_list_refresh.setOnClickListener(clickListener) }
    }
}
