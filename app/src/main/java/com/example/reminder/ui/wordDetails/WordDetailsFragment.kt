//package com.example.reminder.ui.wordDetails
//
//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.reminder.R
//import com.example.reminder.base.BaseFragment
//import com.example.reminder.ui.wordList.WordListViewModel
//
//class WordDetailsFragment : BaseFragment<WordDetailsViewModel<>>(R.layout.fragment_word_details) {
//
//    companion object {
//        fun newInstance() = WordDetailsFragment()
//    }
//
//    private lateinit var viewModel: WordDetailsViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_word_details, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(WordDetailsViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}