package com.example.sneakersapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.repository.SneakerDetailRepository
import com.example.sneakersapp.utils.Constants.Companion.DEFAULT_COLOR
import com.example.sneakersapp.utils.Constants.Companion.DEFAULT_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerDetailViewModel @Inject constructor(private val sneakerDetailRepository: SneakerDetailRepository) :
    ViewModel() {

    var sneakerSize: Int = DEFAULT_SIZE
    var sneakerColor: Int = DEFAULT_COLOR

    fun saveSneakerDetails(sneakerTable: SneakerTable): Boolean {
        viewModelScope.launch {
            sneakerDetailRepository.upsetSneaker(sneakerTable)
        }
        return true
    }
}