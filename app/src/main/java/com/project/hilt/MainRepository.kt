package com.project.hilt

import javax.inject.Inject

class MainRepository @Inject constructor(private val mainDataSource: MainDataSource) {
    suspend fun getSomeData(id: String): String {
        return mainDataSource.getSomeData(id)
    }
}
