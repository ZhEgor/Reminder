package com.example.reminder.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass


typealias BindingInflation<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel<*, *>> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!
    protected abstract val bindingInflation: BindingInflation<VB>
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflation(inflater, container, false)
        return binding.root
    }

    protected fun <T> LiveData<T>.subscribe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, block)
    }

}
