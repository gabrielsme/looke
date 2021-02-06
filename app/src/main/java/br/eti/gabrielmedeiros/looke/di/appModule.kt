package br.eti.gabrielmedeiros.looke.di

import br.eti.gabrielmedeiros.looke.repository.ProductsRepository
import br.eti.gabrielmedeiros.looke.service.ProductsService
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(okHttpClient = get()) }
}

val productsModule = module {
    factory { provideProductsService(retrofit = get()) }
    factory { ProductsRepository(productsService = get()) }
//    viewModel { MainViewModel(productsRepository = get()) }
}

fun provideProductsService(retrofit: Retrofit): ProductsService = retrofit.create(ProductsService::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://sampletestingproject-4a8fc.web.app/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}
