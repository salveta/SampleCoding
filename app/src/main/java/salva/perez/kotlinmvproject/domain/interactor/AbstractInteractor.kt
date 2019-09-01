package salva.perez.kotlinmvproject.domain.interactor

import io.reactivex.disposables.Disposable

abstract class AbstractInteractor : Interactor {
    var disposable: Disposable? = null

    protected fun clearComposite() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}