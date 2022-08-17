package org.meicode.latihanproject1.VideoModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.meicode.latihanproject1.DataModel.MainRepository

class MyViewModelFactory constructor(private val repository : MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}