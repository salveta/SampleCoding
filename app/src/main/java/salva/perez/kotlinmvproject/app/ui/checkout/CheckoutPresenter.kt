package salva.perez.kotlinmvproject.app.ui.checkout

import org.koin.core.KoinComponent
import salva.perez.kotlinmvproject.data.repository.CheckoutRepository

import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice.Companion.MUG
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice.Companion.TSHIRT_DISCOUNT_CODE
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice.Companion.VOUCHER_DISCOUNT_CODE
import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import salva.perez.kotlinmvproject.domain.model.Checkout
import salva.perez.kotlinmvproject.domain.model.Product

class CheckoutPresenter (private var view: CheckoutContract.View?, private var mCheckoutInteractor : CheckoutImpl, private var voucherRepository: VoucherRepository, private var checkoutRepository: CheckoutRepository) : CheckoutContract.Presenter, CheckoutContract.Callback, KoinComponent{

    override fun create() {
        view?.initView()
        getCheckoutProducts()
    }

    override fun destroy() {
        view = null
    }

    private fun getCheckoutProducts(){
        val itemsVoucher = voucherRepository.getProductsInShoppinCart()?.count { it.code == VOUCHER_DISCOUNT_CODE }
        val itemsTshirt= voucherRepository.getProductsInShoppinCart()?.count { it.code == TSHIRT_DISCOUNT_CODE }
        val itemsMug = voucherRepository.getProductsInShoppinCart()?.count { it.code == MUG }
        createCheckoutObject(itemsVoucher, itemsTshirt, itemsMug)
    }

    private fun createCheckoutObject(itemsVoucher: Int?, itemsTshirt: Int?, itemsMug: Int?){
        var removeEqualElements = voucherRepository.getProductsInShoppinCart()
        removeEqualElements!!.distinct()

        addVoucherInCheckout(removeEqualElements, itemsVoucher)
        addTshirtInCheckout(removeEqualElements, itemsTshirt)
        addMugInCheckout(removeEqualElements, itemsMug)

        view?.showCheckoutProducts(checkoutRepository.getProductsInShoppinCart()!!.distinct())
    }

    private fun addVoucherInCheckout(removeEqualElements : List<Product>, itemsVoucher: Int?){
        for(product in removeEqualElements){
            if(product.code.contentEquals(VOUCHER_DISCOUNT_CODE)) {
                checkoutRepository.addProductToShoppingCart(Checkout(product.name, product.code, CalculateFinalPrice().getDiscountVoucher(), itemsVoucher!!))
            }
        }
    }
    private fun addTshirtInCheckout(removeEqualElements : List<Product>, itemsTshirt: Int?){
        for(product in removeEqualElements){
            if(product.code.contentEquals(TSHIRT_DISCOUNT_CODE)) {
                checkoutRepository.addProductToShoppingCart(Checkout(product.name, product.code, CalculateFinalPrice().getDiscountTshirt(), itemsTshirt!!))
            }
        }
    }
    private fun addMugInCheckout(removeEqualElements : List<Product>, itemsMug: Int?){
        for(product in removeEqualElements){
            if(product.code.contentEquals(MUG)) {
                checkoutRepository.addProductToShoppingCart(Checkout(product.name, product.code, CalculateFinalPrice().getTotalMug(), itemsMug!!))
            }
        }
    }

    override fun paypalCheckout(){
        mCheckoutInteractor.checkoutPaypal(this)
    }

    override fun creditCardCheckout(){
        mCheckoutInteractor.checkoutCreditCard(this)
    }

    override fun cleanCheckout(){
        checkoutRepository.cleanCheckout()
    }

    override fun onShoppingDone() {
        cleanShoppingCart()
        view?.shoppingDone()
    }

    private fun cleanShoppingCart(){

    }

    override fun onError() {}
}