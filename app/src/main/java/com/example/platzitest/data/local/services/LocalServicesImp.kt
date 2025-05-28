package com.example.platzitest.data.local.services

import com.example.platzitest.data.local.dbdatasource.PlatziDatabase
import com.example.platzitest.data.model.entities.SoundEntity
import kotlinx.coroutines.flow.flow

class LocalServicesImp(db: PlatziDatabase) : LocalServices {

    private val dao = db.platziDao()

    override suspend fun insertSound(soundList: List<SoundEntity>) {
        dao.insertSound(soundList)
    }

    override suspend fun getSounds() = flow {
        dao.getSounds().collect {
            emit(it)
        }
    }
}