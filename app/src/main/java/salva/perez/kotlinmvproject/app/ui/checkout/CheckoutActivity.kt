package salva.perez.kotlinmvproject.app.ui.checkout

import android.os.Bundle
import kotlinx.android.synthetic.main.checkout.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import salva.perez.kotlinmvproject.app.ui.checkout.adapter.CheckoutAdapter
import salva.perez.kotlinmvproject.R
import salva.perez.kotlinmvproject.app.ui.toolbars.ToolbarCheckoutActivity
import salva.perez.kotlinmvproject.app.ui.utils.ArgumentsNameConfig.TOTAL_CHECKOUT
import salva.perez.kotlinmvproject.app.ui.utils.showToast
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice
import salva.perez.kotlinmvproject.domain.model.Checkout

class CheckoutActivity : ToolbarCheckoutActivity(), CheckoutContract.View{

    private var totalPrice : Double = 0.0
    override val layoutId: Int = R.layout.checkout
    private val presenter: CheckoutContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.create()

        totalPrice  = intent.getDoubleExtra(TOTAL_CHECKOUT, 0.0)

        var currency = "${CalculateFinalPrice().formatCredit(totalPrice.toString())} ${resources.getString(R.string.euro)}"
        txTotalPayment?.text = currency
        btnPaypal?.setOnClickListener { buyVoucherPaypal() }
        btnCreditCard?.setOnClickListener { buyCreditCardVoucher() }
    }

    override fun initView() {
        configureActionBar()
    }

    override fun showCheckoutProducts(checkoutVouchers: List<Checkout>) {
        rvCheckout?.adapter = CheckoutAdapter(checkoutVouchers){}
    }

    private fun buyVoucherPaypal(){
        presenter.paypalCheckout()
        deleteShoppingCart()
    }
    private fun buyCreditCardVoucher(){
        presenter.creditCardCheckout()
        deleteShoppingCart()
    }

    override fun shoppingDone() {
        showToast(this, getString(R.string.shopping_success))
        finish()
    }

    override fun onDestroy() {
        presenter.cleanCheckout()
        super.onDestroy()
    }
}