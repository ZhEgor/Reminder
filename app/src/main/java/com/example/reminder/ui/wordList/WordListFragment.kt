package com.example.reminder.ui.wordList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.data.entity.Category
import com.example.reminder.data.entity.Word
import com.example.reminder.databinding.FragmentWordListBinding
import com.example.reminder.ui.common.ProgressDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordListFragment : BaseFragment<FragmentWordListBinding, WordListViewModel>() {

    override val viewModel by viewModel<WordListViewModel>()
    override val bindingInflation: BindingInflation<FragmentWordListBinding>
        get() = FragmentWordListBinding::inflate
    private val progressDialog = ProgressDialog()
    private val adapter by lazy {
        WordRvAdapter(
            onWordClicked = { word ->
                viewModel.navigateToWordDetailsScreen(word)
            }, onEndReached = { position, limit ->
                category?.id?.let { categoryId ->
                    viewModel.loadWordsFromPosition(categoryId, position, limit)
                }
            }
        )
    }
    private var category: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        category?.id?.let { categoryId ->
            viewModel.loadWordsFromPosition(categoryId, START_POSITION, START_LIMIT)
        }
        observeViewModel()
    }

    private fun initView() {
        binding.apply {
            rvWords.adapter = adapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            try {
                viewModel.state.collect { state ->
                    when (state) {
                        is WordListState.IsLoading -> changeProgressDialogState(state.state)
                        is WordListState.ReceivedWords -> submitWords(state.words)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun submitWords(words: List<Word>) {
        adapter.submitList(words)
    }

    private fun changeProgressDialogState(state: Boolean) {
        when (state) {
            true -> progressDialog.showProgressBar(requireContext())
            false -> progressDialog.hideProgressBar()
        }
    }

    private fun receiveArgs() {
        arguments?.let {
            category = it.getParcelable(ARG_CATEGORY)
        }
    }

    companion object {
        private const val START_LIMIT = 30
        private const val START_POSITION = 0
        private const val ARG_CATEGORY = "category"

        @JvmStatic
        fun newInstance(category: Category) =
            WordListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CATEGORY, category)
                }
            }
    }
}
