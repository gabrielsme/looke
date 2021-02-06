package br.eti.gabrielmedeiros.looke.service

import br.eti.gabrielmedeiros.looke.model.Product
import retrofit2.http.GET

interface ProductsService {

    @GET("teste.json")
    suspend fun get(): List<Product>

}
