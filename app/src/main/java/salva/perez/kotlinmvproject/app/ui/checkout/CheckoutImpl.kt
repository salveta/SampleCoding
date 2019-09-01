package salva.perez.kotlinmvproject.app.ui.checkout


import salva.perez.kotlinmvproject.domain.interactor.AbstractInteractor

class CheckoutImpl : AbstractInteractor() {

    private val TAG = "CheckoutImpl"

    override fun run() {}

    fun checkoutPaypal(callback: CheckoutContract.Callback){
        callback.onShoppingDone()
    }

    fun checkoutCreditCard(callback: CheckoutContract.Callback){
        callback.onShoppingDone()
    }

    override fun removeCallbacks() {
        clearComposite()
    }

    override fun destroy() {
        removeCallbacks()
    }
}