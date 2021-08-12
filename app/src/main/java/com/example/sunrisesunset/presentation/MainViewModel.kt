package com.example.sunrisesunset.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunrisesunset.data.network.model.DayInfo
import com.example.sunrisesunset.data.repository.SunRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: SunRepository
) : ViewModel(){

    private var retrieveSunJob: Job? = null

    private val _dayInfoLiveData = MutableLiveData<DayInfo>()
    private val _errorLiveData = MutableLiveData<String?>()

    val dayInfoLiveData: LiveData<DayInfo> = _dayInfoLiveData

    val errorLiveData: LiveData<String?> = _errorLiveData

    fun retrieveSun(latitude: Double, longitude: Double) {
        retrieveSunJob = viewModelScope.launch {
            val sun = repository.retrieveSunDetailsByLocation(latitude, longitude)
            _dayInfoLiveData.postValue(sun)
        }
    }

    fun cancel() {
        retrieveSunJob?.cancel()
    }
}