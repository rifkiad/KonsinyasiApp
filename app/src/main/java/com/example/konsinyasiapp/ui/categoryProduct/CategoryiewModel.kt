package com.example.konsinyasiapp.ui.categoryProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryiewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Category Product Fragment"
    }
    val text: LiveData<String> = _text
}