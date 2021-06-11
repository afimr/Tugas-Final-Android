package com.example.pendataan_barang.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserViewModel(application: Application): AndroidViewModel(application) {

    private var repository = BarangRepository(application)
    private var barangs: LiveData<List<BarangEntity>>? = repository.getBarangs()

    fun insertBarang(barangEntity: BarangEntity) {
        repository.insert(barangEntity)
    }

    fun getBarangs(): LiveData<List<BarangEntity>>? {
        return barangs
    }

    fun deleteBarang(barangEntity: BarangEntity) {
        repository.delete(barangEntity)
    }

    fun updateBarang(barangEntity: BarangEntity) {
        repository.update(barangEntity)
    }

}