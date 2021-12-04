package com.example.reminder.ui.createWord

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.databinding.FragmentCreateWordBinding
import com.example.reminder.ui.common.ProgressDialog
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CreateWordFragment : BaseFragment<FragmentCreateWordBinding, CreateWordViewModel>() {

    override val viewModel by viewModel<CreateWordViewModel>()
    override val bindingInflation: BindingInflation<FragmentCreateWordBinding>
        get() = FragmentCreateWordBinding::inflate
    private val progressDialog = ProgressDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeViewModel()
    }

    private fun init() {
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

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            try {
                viewModel.state.collect { state ->
                    when (state.isLoading) {
                        true -> progressDialog.showProgressBar(requireContext())
                        false -> progressDialog.hideProgressBar()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                e.printStackTrace()
            }
        }
    }

}