package com.example.pendataan_barang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pendataan_barang.User.BarangEntity
import kotlinx.android.synthetic.main.item_barang.view.*

class MainAdapter(private val list: List<BarangEntity>, private val listener: MainAdapter.Listener): RecyclerView.Adapter<MainAdapter.Holder>() {

    interface Listener {
        fun onClick(barangEntity: BarangEntity)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(barangEntity: BarangEntity, listener: Listener) {
            with(itemView){
                jenis_barang.text = "Jenis Barang : ${barangEntity.jenis}"
                nama_barang.text = "Nama Barang : ${barangEntity.nama}"
                harga_barang.text = "Harga Barang : Rp.${barangEntity.harga}"
                itemView.setOnClickListener {
                    listener.onClick(barangEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.Holder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}