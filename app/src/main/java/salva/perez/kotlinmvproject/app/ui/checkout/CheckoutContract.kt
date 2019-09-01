package salva.perez.kotlinmvproject.app.ui.checkout

import salva.perez.kotlinmvproject.domain.model.Checkout

interface CheckoutContract {

    interface View {
        fun initView()
        fun showCheckoutProducts(checkoutVouchers : List<Checkout>)
        fun shoppingDone()
    }

    interface Presenter{
        fun create()
        fun destroy()
        fun paypalCheckout()
        fun creditCardCheckout()
        fun cleanCheckout()
    }

    interface Callback {
        fun onShoppingDone()
        fun onError()
    }

    interface Model{
        fun addProductToShoppingCart(CheckoutProducts: Checkout)
        fun getProductsInShoppinCart(): List<Checkout>?
        fun cleanCheckout()
    }
}