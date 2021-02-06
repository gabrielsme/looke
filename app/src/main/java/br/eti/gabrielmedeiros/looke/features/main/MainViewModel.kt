package br.eti.gabrielmedeiros.looke.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.eti.gabrielmedeiros.looke.base.Resource
import br.eti.gabrielmedeiros.looke.model.Product
import br.eti.gabrielmedeiros.looke.model.Topping
import br.eti.gabrielmedeiros.looke.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _toppings = MutableLiveData<Resource<List<Topping>>>()
    val toppings: LiveData<Resource<List<Topping>>>
        get() = _toppings

    fun getToppings() {
        _toppings.value = Resource.Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                productsRepository.getToppings()
            }

            _toppings.value = result
        }
    }
}
