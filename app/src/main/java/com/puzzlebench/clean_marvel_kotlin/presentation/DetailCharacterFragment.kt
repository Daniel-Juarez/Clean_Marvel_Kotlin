package com.puzzlebench.clean_marvel_kotlin.presentation


import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterDetailServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxDialogFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailView
import android.content.DialogInterface
import android.view.Window
import kotlinx.android.synthetic.main.fragment_detail_character.*


private const val ARG_ID_CHARACTER = "ID_CHARACTER"
const val TAG_FRAGMENT_DETAIL_DIALOG = "DetailDialog"

class DetailCharacterFragment : BaseRxDialogFragment() {
    var idCharacter: Int? = null

    val getCharacterDetailServiceUseCase = GetCharacterDetailServiceUseCase(CharacterServicesImpl())
    val presenter = CharacterDetailPresenter(CharacterDetailView(this), getCharacterDetailServiceUseCase, subscriptions)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_detail_character, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arguments?.let {
            idCharacter = it.getInt(ARG_ID_CHARACTER)
        }

        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        presenter.init()
    }

    companion object {
        @JvmStatic
        fun newInstance(idCharacter: Int) =
                DetailCharacterFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ID_CHARACTER, idCharacter)
                    }
                }
    }
}
