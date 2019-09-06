package salva.perez.kotlinmvproject

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import salva.perez.kotlinmvproject.di.applicationModule

class ChallengeApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ChallengeApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ChallengeApplication)
            modules(listOf(applicationModule))
        }
    }
}
