package br.eti.gabrielmedeiros.looke.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.eti.gabrielmedeiros.looke.R
import br.eti.gabrielmedeiros.looke.databinding.AdapterMainProductsBinding
import br.eti.gabrielmedeiros.looke.model.Product

class MainProductsAdapter(
    private val items: List<Product>
) : RecyclerView.Adapter<MainProductsAdapter.MainProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductViewHolder {
        val itemBinding = AdapterMainProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainProductViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainProductViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MainProductViewHolder(private val itemBind: AdapterMainProductsBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun setItem(product: Product) {
            itemBind.textTitle.text = product.name
            itemBind.textType.text = product.type
        }
    }
}
