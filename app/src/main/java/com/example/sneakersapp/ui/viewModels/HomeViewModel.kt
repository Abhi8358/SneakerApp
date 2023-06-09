package com.example.sneakersapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.model.SneakerViewData
import com.example.sneakersapp.repository.HomeRepositoryInterface
import com.example.sneakersapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepositoryInterface
) : ViewModel() {

    private val _sneakerLiveData: MutableLiveData<Resource<SneakerViewData>> = MutableLiveData()
    val sneakerLiveData: LiveData<Resource<SneakerViewData>>
        get() = _sneakerLiveData

    init {
        getSneakers()
        Log.d("AAAAAAAA", "1 init")
    }
    fun getSneakers() {
        viewModelScope.launch {
            _sneakerLiveData.postValue(Resource.Loading())
            val response = repository.getTopSneakers()
            _sneakerLiveData.postValue(handleNewsResponse(response))
        }
    }

    fun getSearchedItemsList(input: String) {
        viewModelScope.launch {
            _sneakerLiveData.postValue(Resource.Loading())
            val response = repository.getSearchedItem(input)
            _sneakerLiveData.postValue(handleNewsResponse(response))
        }
    }

    private fun handleNewsResponse(response: SneakerViewData): Resource<SneakerViewData> {
        if (response.message == "ok") {
            return Resource.Success(response)
        }
        return Resource.Error("No response found")
    }
}
