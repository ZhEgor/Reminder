package com.example.reminder.ui.createWord

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.reminder.R
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.databinding.FragmentCreateWordBinding
import com.example.reminder.states.CreateWordState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateWordFragment : BaseFragment<FragmentCreateWordBinding, CreateWordViewModel>() {

    override val viewModel by viewModel<CreateWordViewModel>()
    override val bindingInflation: BindingInflation<FragmentCreateWordBinding>
        get() = FragmentCreateWordBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.state.subscribe(::renderState)
        binding.buttonAdd.setOnClickListener {
            addWord()
        }
    }

    private fun addWord() {
        val spelling = binding.editTextSpelling.text.toString()
        val translation = binding.editTextTranslation.text.toString()
        val pronunciation = binding.editTextPronunciation.text.toString()
        if (spelling != "" || translation != "") {
            binding.editTextSpelling.setText("")
            binding.editTextTranslation.setText("")
            binding.editTextPronunciation.setText("")
            viewModel.addNewWord(
                spelling = spelling,
                translation = translation,
                pronunciation = pronunciation
            )
        }
    }

    private fun renderState(state: CreateWordState) {
        if (state.wordIsAdded) {
            Toast.makeText(
                requireContext(),
                getString(R.string.toast_added_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}