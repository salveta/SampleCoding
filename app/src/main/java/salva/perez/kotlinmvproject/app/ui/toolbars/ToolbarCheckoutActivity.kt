package salva.perez.kotlinmvproject.app.ui.toolbars

import kotlinx.android.synthetic.main.checkout_toolbar.*
import salva.perez.kotlinmvproject.app.base.BaseActivity
import salva.perez.kotlinmvproject.data.repository.CheckoutRepository
import salva.perez.kotlinmvproject.data.repository.VoucherRepository

abstract class ToolbarCheckoutActivity : BaseActivity() {

    protected fun configureActionBar() {
        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar_checkout!!)
        supportActionBar

        rlDeleteProducts?.setOnClickListener { deleteAllProducts() }
    }

    private fun deleteAllProducts(){
        deleteShoppingCart()
        finish()
    }

    protected fun deleteShoppingCart(){
        VoucherRepository.cleanProductsInShoppinCart()
        CheckoutRepository.cleanCheckout()
    }
}