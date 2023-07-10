package com.example.konsinyasiapp.ui.deposit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DepositViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Deposit Fragment"
    }
    val text: LiveData<String> = _text
}