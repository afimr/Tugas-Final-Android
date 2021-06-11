package com.example.pendataan_barang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.pendataan_barang.User.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)
        addBtn.setOnClickListener {
            tampilkanDialog()
        }
    }

    private fun tampilkanDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null)
        val builder = this.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        val mDialog = builder?.show()
    }
}