package com.project.hilt

import android.util.Log
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
    private val mainRepository: MainRepository,
    private val dataRepository: DataRepository,
    private val dataRepositoryBinds: DataRepositoryBinds
) : ViewModel() {

    init {
//        getSomeData()
        getData()
        getData2()
    }

    private val mutableStateFlow = MutableStateFlow("")
    val stateFlow: StateFlow<String> = mutableStateFlow.asStateFlow()

    private fun getSomeData() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableStateFlow.emit(mainRepository.getSomeData("DATA"))
        }
    }

    private val mutableDataFlow = MutableStateFlow<List<Data>> (listOf())
    val stateDataFlow: StateFlow<List<Data>> = mutableDataFlow.asStateFlow()

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("mLogHilt", "getData: ${dataRepository.getData()}")
            mutableDataFlow.emit(dataRepository.getData())
        }
    }

    private val mutableDataFlow2 = MutableStateFlow<List<Data>> (listOf())
    val stateDataFlow2: StateFlow<List<Data>> = mutableDataFlow.asStateFlow()

    private fun getData2() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("mLogHilt", "getDataBinds: ${dataRepositoryBinds.getData2()}")
            mutableDataFlow.emit(dataRepositoryBinds.getData2())
        }
    }
}