package com.example.pendataan_barang.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BarangDao {

    @Query("SELECT * FROM tabel_barang")
    fun getBarangs(): LiveData<List<BarangEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBarang(barangEntity: BarangEntity)

    @Update
    fun updateBarang(barangEntity: BarangEntity)

    @Delete
    fun deleteBarang(barangEntity: BarangEntity)
}