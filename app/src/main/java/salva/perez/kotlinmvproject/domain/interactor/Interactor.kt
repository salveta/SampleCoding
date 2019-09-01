package salva.perez.kotlinmvproject.domain.interactor

interface Interactor {

    fun run()

    fun removeCallbacks()

    fun destroy()
}
