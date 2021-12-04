package com.example.reminder.ui.wordList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.reminder.adapters.WordAdapter
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.data.entity.Word
import com.example.reminder.databinding.FragmentWordListBinding
import com.example.reminder.ui.common.ProgressDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class WordListFragment : BaseFragment<FragmentWordListBinding, WordListViewModel>() {

    override val viewModel by viewModel<WordListViewModel>()
    override val bindingInflation: BindingInflation<FragmentWordListBinding>
        get() = FragmentWordListBinding::inflate
    private val progressDialog = ProgressDialog()
    private val adapter by lazy {
        WordAdapter(
            onWordClicked = { word ->
                viewModel.navigateToWordDetailsScreen(word)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeViewModel()
    }


    private fun init() {
        binding.recyclerViewWords.adapter = adapter
        adapter.submitList(listOf(
            Word(
                id = UUID.randomUUID().toString(),
                spelling = "spelling1",
                translation = "translation1",
                pronunciation = "pronunciation1",
                creationDate = System.currentTimeMillis().toString(),
                lastShowDate = "",
                complexity = 1,
                repetitionsShowed = 0
            ),
            Word(
                id = UUID.randomUUID().toString(),
                spelling = "spelling2",
                translation = "translation2",
                pronunciation = "pronunciation2",
                creationDate = System.currentTimeMillis().toString(),
                lastShowDate = "",
                complexity = 1,
                repetitionsShowed = 0
            )
        ))

    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            try {
                viewModel.state.collect { state ->
                    when (state.isLoading) {
                        true -> progressDialog.showProgressBar(requireContext())
                        false -> progressDialog.hideProgressBar()
                    }

                    adapter.submitList(state.words)
                }
            } catch (e: Exception) {
                Timber.e(e)
                e.printStackTrace()
            }
        }
    }

}
