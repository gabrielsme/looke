package br.eti.gabrielmedeiros.looke

import android.app.Application
import br.eti.gabrielmedeiros.looke.di.networkModule
import br.eti.gabrielmedeiros.looke.di.productsModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LookeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@LookeApplication)
            modules(listOf(
                networkModule,
                productsModule
            ))
        }
    }

}
