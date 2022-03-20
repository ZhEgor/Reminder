package com.zhiroke.reminder.ui.common.selectItemDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.zhiroke.reminder.R
import com.zhiroke.reminder.databinding.DialogSelectItemBinding

class SelectItemDialog(onClickedItem: (String) -> Unit, onCreateNewItem: () -> Unit) :
    DialogFragment() {

    private var binding: DialogSelectItemBinding? = null
    private val adapter by lazy {
        SelectItemRvAdapter(
            onItemClicked = { item ->
                if (item == createNewItem.firstOrNull()) onCreateNewItem()
                else onClickedItem(item)
                dialog?.dismiss()
            }
        )
    }
    private var itemList: List<String> = emptyList()
    private val createNewItem by lazy {
        listOf(getString(R.string.create_new_item))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSelectItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding?.apply {
            rvItems.adapter = adapter
            etSearch.doOnTextChanged { text, _, _, _ ->
                adapter.submitList(findBySearchField(text.toString()))
            }
        }
        adapter.submitList(findBySearchField(binding?.etSearch?.text.toString()))
    }

    fun submitList(itemList: List<String>) {
        this.itemList = itemList
        if (isVisible) {
            adapter.submitList(findBySearchField(binding?.etSearch?.text.toString()))
        }
    }

    private fun findBySearchField(text: String): List<String> {
        return createNewItem + if (text.isBlank()) {
            itemList
        } else {
            itemList.filter { item ->
                item.contains(text, ignoreCase = true)
            }
        }
    }
}
