package com.example.phonenew.vista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonenew.data.Repository
import com.example.phonenew.data.local.PhoneDataBase
import com.example.phonenew.data.remote.PhoneRetrofit
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:Repository

    fun phoneLiveData() = repository.getPhoneEntity()

    fun detailsLiveData(id:String) = repository.getPhoneDetailsEntity(id)

    init {
        val phoneApi = PhoneRetrofit.getPhoneRetrofit()
        val phoneDataBase = PhoneDataBase.getDataBase(application).getPhoneDao()
        repository = Repository(phoneApi, phoneDataBase)

    }
    fun getAllPhones() = viewModelScope.launch {
        repository.getPhones()
    }
    fun getDetailsVM(id:String)=viewModelScope.launch {
        repository.getPhoneDetails(id)
    }
}