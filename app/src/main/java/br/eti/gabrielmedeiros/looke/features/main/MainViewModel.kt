package br.eti.gabrielmedeiros.looke.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.eti.gabrielmedeiros.looke.base.Resource
import br.eti.gabrielmedeiros.looke.model.Product
import br.eti.gabrielmedeiros.looke.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _products = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>>
        get() = _products

    fun getProducts() {
        _products.value = Resource.Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                productsRepository.get()
            }

            _products.value = result
        }
    }
}
