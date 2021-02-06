package br.eti.gabrielmedeiros.looke.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.eti.gabrielmedeiros.looke.R
import br.eti.gabrielmedeiros.looke.base.Resource
import br.eti.gabrielmedeiros.looke.databinding.ActivityMainBinding
import br.eti.gabrielmedeiros.looke.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        viewModel.getProducts()
    }

    private fun setupObservers() {
        viewModel.products.observe(this) {
            setupProducts(it)
        }
    }

    private fun setupProducts(resource: Resource<List<Product>>) {
        showProgressBar(false)

        when (resource) {
            is Resource.Loading -> showProgressBar(true)
            is Resource.NetworkError -> showToast(resource.error?.error ?: getString(R.string.without_internet))
            is Resource.GenericError -> showToast(resource.error?.error ?: getString(R.string.unknown_error))
            is Resource.Success -> {
                val adapter = MainProductsAdapter(resource.value)
                binding.recyclerviewProducts.adapter = adapter
                binding.recyclerviewProducts.layoutManager = LinearLayoutManager(this)
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) ProgressBar.VISIBLE else ProgressBar.INVISIBLE
    }
}
