package com.zhiroke.reminder.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.zhiroke.reminder.databinding.DialogYesNoBinding

class YesNoDialog(
    private val textBtnNo: String? = null,
    private val textBtnYes: String? = null
) : DialogFragment() {

    private var binding: DialogYesNoBinding? = null
    private var topicText: String? = null
    private var onPressNo: (() -> Unit)? = null
    private var onPressYes: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogYesNoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun show(
        fragmentManager: FragmentManager,
        tag: String?,
        topicText: String,
        onPressYes: () -> Unit,
        onPressNo: (() -> Unit)? = null
    ) {
        this.topicText = topicText
        this.onPressNo = onPressNo
        this.onPressYes = onPressYes
        show(fragmentManager, tag)
    }

    private fun initView() {
        binding?.apply {
            tvTopic.text = topicText
            textBtnNo?.let { btnNo.text = it }
            textBtnYes?.let { btnYes.text = it }
            btnYes.setOnClickListener {
                onPressYes?.invoke()
                dismiss()
            }
            btnNo.setOnClickListener {
                onPressNo?.invoke()
                dismiss()
            }
        }
    }
}
