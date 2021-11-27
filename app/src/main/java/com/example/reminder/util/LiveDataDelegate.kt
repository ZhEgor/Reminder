package com.example.reminder.util

import androidx.lifecycle.MutableLiveData
import com.example.reminder.base.BaseViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> MutableLiveData<T>.delegate() = LiveDataDelegate(this)

class LiveDataDelegate<T>(
    private val liveData: MutableLiveData<T>
) : ReadWriteProperty<BaseViewModel<*, *>, T> {

    override fun getValue(thisRef: BaseViewModel<*, *>, property: KProperty<*>): T {
        return liveData.value!!
    }

    override fun setValue(thisRef: BaseViewModel<*, *>, property: KProperty<*>, value: T) {
        liveData.value = value!!
    }

}