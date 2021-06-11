package com.example.pendataan_barang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pendataan_barang.User.BarangEntity
import com.example.pendataan_barang.User.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        recylerView.setHasFixedSize(true)
        recylerView.layoutManager = LinearLayoutManager(this)

        viewmodel.getBarangs()?.observe(this, Observer {
            recylerView.adapter = MainAdapter(it, object : MainAdapter.Listener {
                override fun onClick(barangEntity: BarangEntity) {
                    tampilkanDialogUpdate(barangEntity)
                }
            })
        })

        addBtn.setOnClickListener {
            tampilkanDialog()
        }
    }

    private fun tampilkanDialogUpdate(barangEntity: BarangEntity) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
        val builder = this.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        val mDialog = builder?.show()
        with(dialogView) {
            hapusBtn.visibility = View.VISIBLE
            savebtn.text = "Update"
            jenisinput.setText(barangEntity.jenis)
            namainput.setText(barangEntity.nama)
            hargainput.setText(barangEntity.harga.toString())

            savebtn.setOnClickListener {
                val jenis = jenisinput.text.toString()
                val nama = namainput.text.toString()
                val harga = hargainput.text.toString()
                if( jenis != "" &&  nama != "" &&  harga != "") {
                    viewmodel.updateBarang(
                        BarangEntity(
                            barangEntity.id, jenis, nama, harga.toLong()
                        )
                    )
                    mDialog?.dismiss()
                    Toast.makeText(this@MainActivity, "Barang Berhasil diubah", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Harap Mengisi Semua Kolom", Toast.LENGTH_SHORT).show()
                }
            }

            hapusBtn.setOnClickListener {
                viewmodel.deleteBarang(barangEntity)
                mDialog?.dismiss()
                Toast.makeText(this@MainActivity, "Barang Berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun tampilkanDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
        val builder = this.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        val mDialog = builder?.show()

        with(dialogView) {
            hapusBtn.visibility = View.GONE
            savebtn.setOnClickListener {
                val jenis = jenisinput.text.toString()
                val nama = namainput.text.toString()
                val harga = hargainput.text.toString()
                if( jenis != "" &&  nama != "" &&  harga != "") {
                    viewmodel.insertBarang(
                        BarangEntity(
                            0, jenis, nama, harga.toLong()
                        )
                    )
                    mDialog?.dismiss()
                    Toast.makeText(this@MainActivity, "Barang Berhasil Dimasukkan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Harap Mengisi Semua Kolom", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}