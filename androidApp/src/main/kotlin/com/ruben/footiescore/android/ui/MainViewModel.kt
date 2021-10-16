package com.ruben.footiescore.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.footiescore.usecase.GetAllCompetitionsUseCase
import kotlinx.coroutines.launch

/**
 * Created by Ruben Quadros on 15/10/21
 **/
class MainViewModel(private val useCase: GetAllCompetitionsUseCase): ViewModel() {

    init {
        getAllCompetitions()
    }

    private fun getAllCompetitions() {
        viewModelScope.launch { useCase.invoke(Unit) }
    }
}