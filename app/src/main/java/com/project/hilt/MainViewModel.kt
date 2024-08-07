package com.project.hilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//В Hilt для ViewModel есть специальная аннотация @HiltViewModel, ViewModel также как и раньше будут привязаны к жизненным циклам Fragment или Activity, смотря где вы захотите её создать.
@HiltViewModel
//This is called constructor injection. The @Inject keyword comes from the Hilt, which enables the framework to find this constructor and inject it with appropriate class instances.
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    init {
        getSomeData()
    }

    private val mutableStateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = mutableStateFlow.asStateFlow()

    private fun getSomeData() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableStateFlow.emit(mainRepository.getSomeData("DATA"))
        }
    }
}