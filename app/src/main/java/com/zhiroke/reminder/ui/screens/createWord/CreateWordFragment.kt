package com.zhiroke.reminder.ui.screens.createWord

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.zhiroke.reminder.R
import com.zhiroke.reminder.base.BaseBottomSheetDialogFragment
import com.zhiroke.reminder.base.BindingInflation
import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.databinding.FragmentCreateWordBinding
import com.zhiroke.reminder.ui.common.CreateCategoryDialog
import com.zhiroke.reminder.ui.common.ProgressDialog
import com.zhiroke.reminder.ui.common.selectItemDialog.SelectItemDialog
import com.zhiroke.reminder.util.ext.formatField
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateWordFragment :
    BaseBottomSheetDialogFragment<FragmentCreateWordBinding, CreateWordViewModel>() {

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
    private val clipboardManager by lazy {
        context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
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
        ibSpellingPaste.setOnClickListener {
            etSpellingField.setText(clipboardManager.primaryClip?.getItemAt(0)?.text)
        }
        ibTranslationPaste.setOnClickListener {
            etTranslationField.setText(clipboardManager.primaryClip?.getItemAt(0)?.text)
        }
        ibPronunciationPaste.setOnClickListener {
            etPronunciationField.setText(clipboardManager.primaryClip?.getItemAt(0)?.text)
        }
        tvCategoryField.setOnClickListener {
            ivCategoryState.setImageDrawable(null)
            selectCategoriesDialog.show(parentFragmentManager, TAG_SELECT_CATEGORY_DIALOG)
        }
    }

    private fun addWord() {
        binding.apply {
            val spelling = etSpellingField.text.toString().formatField()
            val translation = etTranslationField.text.toString().formatField()
            val pronunciation = etPronunciationField.text.toString().formatField()
            val category = viewModel.categories.find { it.name == tvCategoryField.text.toString() }
            val hasError = validateFields(spelling, translation, category)

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

    private fun validateFields(spelling: String, translation: String, category: Category?): Boolean {
        val errorIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_error_24)
        errorIcon?.setBounds(0, 0, errorIcon.intrinsicWidth, errorIcon.intrinsicHeight)
        var hasError = false
        binding.apply {

            if (!viewModel.validateFieldsSpelling(spelling)) {
                etSpellingField.setError(getString(R.string.this_field_cannot_be_blank), errorIcon)
                hasError = true
            }
            if (!viewModel.validateFieldsTranslation(translation)) {
                etTranslationField.setError(
                    getString(R.string.this_field_cannot_be_blank),
                    errorIcon
                )
                hasError = true
            }
            if (category == null) {
                ivCategoryState.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_error_24
                    )
                )
                hasError = true
            }
        }
        return hasError
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            try {
                viewModel.state.collect { state ->
                    changeProgressDialogState(state.isLoading)
                    submitCategories(state.categories)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun submitCategories(categories: List<Category>) {
        categories.getOrNull(0)?.let { category -> binding.tvCategoryField.text = category.name }
        selectCategoriesDialog.submitList(categories.map { it.name })
    }

    private fun changeProgressDialogState(state: Boolean) {
        when (state) {
            true -> progressDialog.showProgressBar(requireContext())
            false -> progressDialog.hideProgressBar()
        }
    }

    companion object {
        private const val TAG_SELECT_CATEGORY_DIALOG = "select_category_dialog"
        private const val TAG_CREATE_SELECT_CATEGORY_DIALOG = "create_category_dialog"
    }
}
