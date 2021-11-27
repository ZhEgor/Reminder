package com.example.reminder.ui.wordList

import android.os.Bundle
import android.view.View
import com.example.reminder.base.BaseFragment
import com.example.reminder.base.BindingInflation
import com.example.reminder.databinding.FragmentWordListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordListFragment : BaseFragment<FragmentWordListBinding, WordListViewModel>() {

    override val viewModel by viewModel<WordListViewModel>()
    override val bindingInflation: BindingInflation<FragmentWordListBinding>
        get() = FragmentWordListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}