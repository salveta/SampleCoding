package salva.perez.kotlinmvproject.app.ui.voucher

import org.koin.core.KoinComponent
import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import salva.perez.kotlinmvproject.domain.model.Product


class VoucherPresenter(private var view: VoucherContract.View?, private var mCodeRechargeInteract : VoucherImpl, private var repository: VoucherRepository) : VoucherContract.Presenter, VoucherContract.Callback, KoinComponent {

    override fun create() {
        view?.initView()
        view?.showProgress()
        mCodeRechargeInteract.getVouchers(this)
    }

    override fun destroy() {
        view = null
        mCodeRechargeInteract.removeCallbacks()
    }

    override fun onItemClick(product: Product) {
        view?.onItemClick(product)
    }

    override fun checkIfShoppingCartHaveItems(){
        view?.hideCart(repository.getProductsInShoppinCart()!!.isEmpty())
    }

    override fun addVoucherToShoppingCart(voucherProduct: Product){
        repository.addProductToShoppingCart(voucherProduct)
    }

    override fun openCheckoutActivity() {
        when (repository.getProductsInShoppinCart()?.size) {
            0 -> view?.emptyShoppingCart()
            else -> view?.goToCheckoutActivity()
        }
    }

    override fun onResponseVouchers(vouchers: List<Product>?) {
        view?.hideProgress()
        view?.showVouchers(vouchers)
    }

    override fun onError() {
        view?.hideProgress()
        view?.onErrorVouchers()
    }
}