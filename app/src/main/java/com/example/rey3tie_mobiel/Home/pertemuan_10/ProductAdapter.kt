package com.example.rey3tie_mobiel.Home.pertemuan_10

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rey3tie_mobiel.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]

        with(holder.binding) {
            // Pastikan item.name bertipe String. Jika tidak, tambahkan .toString()
            tvProductName.text = item.name

            // Pastikan item.price bertipe String. Jika Double/Int, gunakan item.price.toString()
            tvProductPrice.text = item.price

            // Load gambar menggunakan Glide
            // Perhatikan penggunaan holder.itemView.context, bukan hanya itemView
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgProduct)

            // Aksi ketika card/item diklik
            root.setOnClickListener {
                onItemClick(item)
            }
        }
        Log.e("RV-TEST",item.name)
    }

    override fun getItemCount(): Int = productList.size
}