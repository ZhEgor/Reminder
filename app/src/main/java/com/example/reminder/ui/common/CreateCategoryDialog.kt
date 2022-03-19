package com.example.reminder.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.reminder.R
import com.example.reminder.databinding.DialogCreateCategoryBinding

class CreateCategoryDialog(val onCreateCategory: (String, String) -> Unit) : DialogFragment() {

    private var binding: DialogCreateCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCreateCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding?.apply {
            val errorIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_error_24)
            errorIcon?.setBounds(0, 0, errorIcon.intrinsicWidth, errorIcon.intrinsicHeight)

            btnCreate.setOnClickListener {
                val categoryName = etCategoryName.text.toString()
                val language = etLanguage.text.toString()
                var hasError = false
                if (categoryName.isBlank()) {
                    etCategoryName.setError(getString(R.string.this_field_cannot_be_blank), errorIcon)
                    hasError = true
                }
                if (language.isBlank()) {
                    etLanguage.setError(getString(R.string.this_field_cannot_be_blank), errorIcon)
                    hasError = true
                }
                if (!hasError) {
                    onCreateCategory(categoryName, language)
                    etCategoryName.text = null
                    etLanguage.text = null
                    dismiss()
                }
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }
}
