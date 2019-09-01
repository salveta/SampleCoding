package salva.perez.kotlinmvproject

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import salva.perez.kotlinmvproject.di.applicationModule

class ChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ChallengeApplication)
            modules(listOf(applicationModule))
        }
    }
}
