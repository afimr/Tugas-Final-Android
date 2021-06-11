package com.example.pendataan_barang.User

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_barang")
data class BarangEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var jenis: String,
    var nama: String,
    var harga: Long
)