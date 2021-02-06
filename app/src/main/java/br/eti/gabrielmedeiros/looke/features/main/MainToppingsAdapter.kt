package br.eti.gabrielmedeiros.looke.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.eti.gabrielmedeiros.looke.databinding.AdapterMainToppingsBinding
import br.eti.gabrielmedeiros.looke.model.Product
import br.eti.gabrielmedeiros.looke.model.Topping

class MainToppingsAdapter(
    private val items: List<Topping>
) : RecyclerView.Adapter<MainToppingsAdapter.MainToppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainToppingViewHolder {
        val itemBinding = AdapterMainToppingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainToppingViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainToppingViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MainToppingViewHolder(private val itemBind: AdapterMainToppingsBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun setItem(item: Topping) {
            itemBind.textTitle.text = item.type
        }
    }
}
