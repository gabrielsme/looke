package br.eti.gabrielmedeiros.looke.repository

import br.eti.gabrielmedeiros.looke.model.Product
import br.eti.gabrielmedeiros.looke.service.ProductsService

class ProductsRepository(
    private val productsService: ProductsService
) {
    suspend fun get(): List<Product> {
        return productsService.get()
    }

}
