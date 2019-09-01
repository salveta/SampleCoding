package salva.perez.kotlinmvproject.app.ui.voucher


import salva.perez.kotlinmvproject.domain.model.Product


interface VoucherContract {

    interface View{
        fun initView()
        fun showVouchers(vouchers: List<Product>?)
        fun onErrorVouchers()
        fun onItemClick(voucherProduct: Product)
        fun showProgress()
        fun hideProgress()
        fun hideCart(vouchers : Boolean)
        fun emptyShoppingCart()
        fun goToCheckoutActivity()
    }

    interface Presenter {
        fun create()
        fun destroy()
        fun checkIfShoppingCartHaveItems()
        fun addVoucherToShoppingCart(voucherProduct: Product)
        fun onItemClick(product: Product)
        fun openCheckoutActivity()
    }

    interface Callback {
        fun onResponseVouchers(vouchers: List<Product>?)
        fun onError()
    }

    interface Model{
        fun addProductToShoppingCart(voucherProduct: Product)
        fun getProductsInShoppinCart(): List<Product>?
        fun cleanProductsInShoppinCart()
    }
}