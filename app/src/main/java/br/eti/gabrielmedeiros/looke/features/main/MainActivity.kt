package br.eti.gabrielmedeiros.looke.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import br.eti.gabrielmedeiros.looke.R
import br.eti.gabrielmedeiros.looke.base.Resource
import br.eti.gabrielmedeiros.looke.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObservers()

        viewModel.getProducts()
    }

    private fun setupObservers() {
        viewModel.products.observe(this) {
            setupProducts(it)
        }
    }

    private fun setupProducts(resource: Resource<List<Product>>) {
        when (resource) {
            is Resource.Loading -> {

            }
            is Resource.NetworkError -> {

            }
            is Resource.GenericError -> {

            }
            is Resource.Success -> {

            }
        }
    }
}
