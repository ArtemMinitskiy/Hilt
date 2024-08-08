package com.project.hilt

import javax.inject.Inject

class DataRepositoryImplBinds @Inject constructor() : DataRepositoryBinds {
    override fun getData2(): List<Data> = listOf(Data(1), Data(2), Data(3), Data(4), Data(5))
}