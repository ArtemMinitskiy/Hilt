package com.project.hilt

class DataRepositoryImpl : DataRepository {
    override fun getData(): List<Data> = listOf(Data(1), Data(2), Data(3), Data(4), Data(5))
}