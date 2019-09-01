package salva.perez.kotlinmvproject.data.repository


import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutContract
import salva.perez.kotlinmvproject.domain.model.Checkout


object CheckoutRepository: CheckoutContract.Model {

    var checkout: MutableList<Checkout> = mutableListOf<Checkout>()

    override fun addProductToShoppingCart(checkoutProduct: Checkout){
        checkout.add(checkoutProduct)
    }

    override fun getProductsInShoppinCart() : List<Checkout>?{
        return checkout
    }

    override fun cleanCheckout(){
        checkout.clear()
    }
}