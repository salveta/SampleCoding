package salva.perez.kotlinmvproject.app.ui.voucher

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.voucher.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import salva.perez.kotlinmvproject.R
import salva.perez.kotlinmvproject.app.navigator.Navigator
import salva.perez.kotlinmvproject.app.ui.toolbars.ToolbarVoucherActivity
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutActivity
import salva.perez.kotlinmvproject.app.ui.utils.showToast
import salva.perez.kotlinmvproject.app.ui.voucher.adapter.VoucherAdapter
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice
import salva.perez.kotlinmvproject.domain.model.Product

class VoucherActivity : ToolbarVoucherActivity(), VoucherContract.View{

    override val layoutId: Int = R.layout.voucher
    private val presenter: VoucherContract.Presenter by inject { parametersOf(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.create()
    }

    override fun onRestart() {
        super.onRestart()
        presenter.checkIfShoppingCartHaveItems()
    }

    override fun onDestroy(){
        super.onDestroy()
        presenter.destroy()
    }

    override fun initView() {
        configureActionBar(this)
    }

    override fun showVouchers(vouchers: List<Product>?) {
        rvVouchers?.adapter = VoucherAdapter(vouchers) {
            presenter.onItemClick(it)
        }
    }

    override fun onErrorVouchers() {
        txVoucherUnavailable?.visibility = View.VISIBLE
        rvVouchers?.visibility = View.GONE
    }

    override fun onItemClick(voucherProduct: Product) {
        presenter.addVoucherToShoppingCart(voucherProduct)
        showShoppingCartInToolbar()
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
        rvVouchers?.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
        rvVouchers?.visibility = View.VISIBLE
    }

    fun checkShoppingCart(){
        presenter.openCheckoutActivity()
    }

    override fun emptyShoppingCart() {
        showToast(this, getString(R.string.empty_shopping_cart))
    }

    override fun goToCheckoutActivity() {
        Navigator.Checkout.openCheckoutActivity(this, CheckoutActivity::class.java, CalculateFinalPrice().getTotalSumProductCheckOut())
    }

    override fun hideCart(vouchers: Boolean) {
        hideShoppingCart(vouchers)
    }
}
