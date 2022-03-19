package com.example.reminder.ui.wordListHolder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.data.entity.Category
import com.example.reminder.databinding.FragmentWordListHolderBinding
import com.example.reminder.ui.common.ProgressDialog
import com.example.reminder.ui.util.FragmentPagerAdapter
import com.example.reminder.ui.wordList.WordListFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordListHolderFragment :
    BaseFragment<FragmentWordListHolderBinding, WordListHolderViewModel>() {

    override val viewModel by viewModel<WordListHolderViewModel>()
    override val bindingInflation: BindingInflation<FragmentWordListHolderBinding>
        get() = FragmentWordListHolderBinding::inflate
    private val progressDialog = ProgressDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.apply {

        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            try {
                viewModel.state.collect { state ->
                    when (state) {
                        is WordListHolderState.IsLoading -> changeProgressDialogState(state.state)
                        is WordListHolderState.ReceivedCategories -> submitCategories(state.categories)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun submitCategories(categories: List<Category>) {
        binding.apply {
            vpWordCategories.adapter = FragmentPagerAdapter(
                createCategoryFragment(categories),
                childFragmentManager,
                lifecycle
            )
            TabLayoutMediator(
                tblWordCategories,
                vpWordCategories
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = categories[position].name
            }.attach()
        }
    }

    private fun changeProgressDialogState(state: Boolean) {
        when (state) {
            true -> progressDialog.showProgressBar(requireContext())
            false -> progressDialog.hideProgressBar()
        }
    }

    private fun createCategoryFragment(categories: List<Category>): List<Fragment> {
        return categories.map { category ->
            WordListFragment.newInstance(category)
        }
    }
}
