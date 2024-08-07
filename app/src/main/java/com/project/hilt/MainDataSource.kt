package com.project.hilt

import android.util.Log
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainDataSource @Inject constructor() {
    suspend fun getSomeData(id: String): String {
        delay(3000)
        Log.i("mLogHilt", "getSomeData $id")
        return "Some Data $id"
    }
}
