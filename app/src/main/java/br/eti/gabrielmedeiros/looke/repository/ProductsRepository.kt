package br.eti.gabrielmedeiros.looke.repository

import br.eti.gabrielmedeiros.looke.base.Resource
import br.eti.gabrielmedeiros.looke.base.safeApiCall
import br.eti.gabrielmedeiros.looke.model.Product
import br.eti.gabrielmedeiros.looke.model.Topping
import br.eti.gabrielmedeiros.looke.service.ProductsService

class ProductsRepository(
    private val productsService: ProductsService
) {
    suspend fun getProducts(): Resource<List<Product>> {
        return safeApiCall {
            productsService.get()
        }
    }

    suspend fun getToppings(): Resource<List<Topping>> {
        val result = safeApiCall {
            productsService.get()
        }

        return when (result) {
            is Resource.Success -> {
                val items = result.value.flatMap {
                    it.topping
                }
                Resource.Success(items.distinctBy { it.type })
            }
            is Resource.NetworkError -> Resource.NetworkError(result.error)
            is Resource.GenericError -> Resource.GenericError(result.code, result.error)
            else -> Resource.Loading
        }
    }
}
