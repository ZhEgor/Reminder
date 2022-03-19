package com.example.reminder.ui.createWord

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.reminder.R
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.data.entity.Category
import com.example.reminder.databinding.FragmentCreateWordBinding
import com.example.reminder.ui.common.CreateCategoryDialog
import com.example.reminder.ui.common.ProgressDialog
import com.example.reminder.ui.common.selectItemDialog.SelectItemDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateWordFragment : BaseFragment<FragmentCreateWordBinding, CreateWordViewModel>() {

    override val viewModel by viewModel<CreateWordViewModel>()
    override val bindingInflation: BindingInflation<FragmentCreateWordBinding>
        get() = FragmentCreateWordBinding::inflate
    private val progressDialog = ProgressDialog()
    private val selectCategoriesDialog by lazy {
        SelectItemDialog(
            onClickedItem = { item ->
                binding.tvCategoryField.text = item
            },
            onCreateNewItem = {
                createCategoryDialog.show(parentFragmentManager, TAG_CREATE_SELECT_CATEGORY_DIALOG)
            }
        )
    }
    private val createCategoryDialog by lazy {
        CreateCategoryDialog(
            onCreateCategory = { categoryName, language ->
                viewModel.addNewCategory(categoryName, language)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
        viewModel.loadCategories()
    }

    private fun initView() = with(binding) {
        btnAdd.setOnClickListener {
            addWord()
        }
        tvCategoryField.setOnClickListener {
            ivCategoryState.setImageDrawable(null)
            selectCategoriesDialog.show(parentFragmentManager, TAG_SELECT_CATEGORY_DIALOG)
        }
    }

    private fun addWord() {
        val errorIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_error_24)
        errorIcon?.setBounds(0, 0, errorIcon.intrinsicWidth, errorIcon.intrinsicHeight)

        binding.apply {
            val spelling = etSpellingField.text.toString()
            val translation = etTranslationField.text.toString()
            val pronunciation = etPronunciationField.text.toString()
            val category = viewModel.categories.find { it.name == tvCategoryField.text.toString() }
            var hasError = false

            if (!viewModel.validateFieldsSpelling(spelling)) {
                etSpellingField.setError(getString(R.string.this_field_cannot_be_blank), errorIcon)
                hasError = true
            }
            if (!viewModel.validateFieldsTranslation(translation)) {
                etTranslationField.setError(getString(R.string.this_field_cannot_be_blank), errorIcon)
                hasError = true
            }
            if (category == null) {
                ivCategoryState.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_error_24))
                hasError = true
            }
            if (!hasError) {
                etSpellingField.text = null
                etTranslationField.text = null
                etPronunciationField.text = null
                viewModel.addNewWord(
                    spelling = spelling,
                    translation = translation,
                    pronunciation = pronunciation,
                    categoryId = category!!.id
                )
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            try {
                viewModel.state.collect { state ->
                    when (state) {
                        is CreateWordState.IsLoading -> changeProgressDialogState(state.state)
                        is CreateWordState.ReceivedCategories -> submitCategories(state.categories)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun submitCategories(categories: List<Category>) {
        categories.getOrNull(0)?.let { category -> binding.tvCategoryField.text = category.name}
        selectCategoriesDialog.submitList(categories.map { it.name })
    }

    private fun changeProgressDialogState(state: Boolean) {
        when (state) {
            true -> progressDialog.showProgressBar(requireContext())
            false -> progressDialog.hideProgressBar()
        }
    }

    companion object {
        const val TAG_SELECT_CATEGORY_DIALOG = "select_category_dialog"
        const val TAG_CREATE_SELECT_CATEGORY_DIALOG = "create_category_dialog"
    }
}
