package com.example.platzitest.data.local.services

import com.example.platzitest.data.local.dbdatasource.PlatziDatabase
import com.example.platzitest.data.model.entities.SoundEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LocalServicesImp(db: PlatziDatabase) : LocalServices {

    private val dao = db.platziDao()

    override suspend fun feedDatabase(soundList: List<SoundEntity>) {
        withContext(Dispatchers.IO) {
            dao.feedDataBase(soundList)
        }
    }

    override suspend fun insertSound(sound: SoundEntity) {
        withContext(Dispatchers.IO) {
            dao.insertSound(sound)
        }
    }

    override suspend fun updateSound(sound: SoundEntity) {
        withContext(Dispatchers.IO) {
            dao.updateSound(sound)
        }
    }

    override suspend fun getSounds() = flow {
        dao.getSounds().collect {
            emit(it)
        }
    }

    override suspend fun deleteSound(sound: SoundEntity) {
        withContext(Dispatchers.IO) {
            dao.deleteSound(sound)
        }
    }
}