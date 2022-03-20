package com.zhiroke.reminder.ui.wordList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.zhiroke.reminder.base.BaseFragment
import com.zhiroke.reminder.base.BindingInflation
import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.databinding.FragmentWordListBinding
import com.zhiroke.reminder.ui.common.ProgressDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WordListFragment : BaseFragment<FragmentWordListBinding, WordListViewModel>() {

    override val viewModel by viewModel<WordListViewModel> {
        parametersOf(category?.id)
    }
    override val bindingInflation: BindingInflation<FragmentWordListBinding>
        get() = FragmentWordListBinding::inflate
    private val progressDialog = ProgressDialog()
    private val adapter by lazy {
        WordRvAdapter(
            onWordClicked = { word ->
                viewModel.navigateToWordDetailsScreen(word)
            }, onEndReached = { position, limit ->
                viewModel.loadWordsFromPosition(position, limit)
            }
        )
    }
    private val category by lazy {
        arguments?.getParcelable<Category>(ARG_CATEGORY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
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
                    changeProgressDialogState(state.isLoading)
                    submitWords(state.words)
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

    companion object {

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
