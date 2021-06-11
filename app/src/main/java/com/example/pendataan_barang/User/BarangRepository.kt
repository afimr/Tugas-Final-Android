package com.example.pendataan_barang.User

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BarangRepository(application: Application) {

    private val barangDao: BarangDao
    private var barangs: LiveData<List<BarangEntity>>? = null

    init {
        val db = UserDatabase.getDatabase(application.applicationContext)
        barangDao = db.barangDao()
        barangs = barangDao.getBarangs()
    }

    fun getBarangs(): LiveData<List<BarangEntity>>? {
        return barangs
    }

    fun insert(barangEntity: BarangEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            barangDao.insertBarang(barangEntity)
        }
    }

    fun delete(barangEntity: BarangEntity) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                barangDao.deleteBarang(barangEntity)
            }
        }
    }

    fun update(barangEntity: BarangEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            barangDao.updateBarang(barangEntity)
        }
    }

}