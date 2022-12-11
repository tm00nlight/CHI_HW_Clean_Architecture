package com.example.chi_hw_clean_architecture.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chi_hw_clean_architecture.data.model.Marvel
import com.example.chi_hw_clean_architecture.domain.usecase.GetMarvels
import com.example.chi_hw_clean_architecture.domain.usecase.SaveMarvels
import com.example.chi_hw_clean_architecture.domain.usecase.UseCaseHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MarvelListViewModel(
    val useCaseHandler: UseCaseHandler,
    private val getMarvels: GetMarvels, private val saveMarvels: SaveMarvels
) : ViewModel() {

//    private var _marvels: MutableStateFlow<List<Marvel>> = MutableStateFlow(emptyList())
    private var _marvels = MutableStateFlow<MarvelUIState>(MarvelUIState.Empty())
    var marvels = _marvels.asStateFlow()

    suspend fun fetchMarvels() = viewModelScope.launch {
//        _marvels.value = getMarvels.execute().single()
        _marvels.value = MarvelUIState.Success(getMarvels.execute().single())
        marvels = _marvels.asStateFlow()
        println((marvels.value as MarvelUIState.Success).list)
    }

    fun addMarvels() = viewModelScope.launch {
        if (marvels.value is MarvelUIState.Success) saveMarvels.execute((marvels.value as MarvelUIState.Success).list)
    }
}

sealed class MarvelUIState {
    data class Success(val list: List<Marvel>) : MarvelUIState()
    class Empty constructor() : MarvelUIState()
}