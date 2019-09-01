package salva.perez.kotlinmvproject.data.repository

import salva.perez.kotlinmvproject.app.ui.voucher.VoucherContract
import salva.perez.kotlinmvproject.domain.model.Product

object VoucherRepository: VoucherContract.Model {

    var vouchers: MutableList<Product> = mutableListOf<Product>()

    override fun addProductToShoppingCart(voucherProduct: Product){
        vouchers.add(voucherProduct)
    }

    override fun getProductsInShoppinCart() : List<Product>?{
        return vouchers
    }

    override fun cleanProductsInShoppinCart(){
        vouchers.clear()
    }
}