package salva.perez.kotlinmvproject

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherContract
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherPresenter
import org.mockito.Mockito
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherImpl
import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import salva.perez.kotlinmvproject.domain.model.Product


class VoucherPresenterTest : KoinTest {

    @InjectMocks
    var mock: VoucherPresenter? = null

    @Mock
    private var mView: VoucherContract.View? = null
    @Mock
    private var mInteractor: VoucherImpl? = null
    @Mock
    private var repository: VoucherRepository? = null


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun process_show_vouchers() {
        mock!!.onResponseVouchers(fakeProducts())
        Mockito.verify(mView)!!.hideProgress()
        Mockito.verify(mView)!!.showVouchers(fakeProducts())
    }

    @Test
    fun process_error_no_vouchers(){
        mock!!.onError()
        Mockito.verify(mView)!!.hideProgress()
        Mockito.verify(mView)!!.onErrorVouchers()
    }

    @Test
    fun add_voucher_shopping_cart(){
        VoucherRepository.addProductToShoppingCart(fakeProduct())
        mock!!.addVoucherToShoppingCart(fakeProduct())

        Assert.assertTrue(VoucherRepository.getProductsInShoppinCart()!!.isNotEmpty())
    }

    private fun fakeProducts(): List<Product>{
        val productList: MutableList<Product> = mutableListOf<Product>()

        val product = Product("VOUCHER","Voucher", 5.0)
        val product2 = Product("VOUCHER","Voucher", 5.0)
        val product3 = Product("VOUCHER","Voucher", 5.0)

        productList.add(product)
        productList.add(product2)
        productList.add(product3)

        return productList
    }

    private fun fakeProduct(): Product{
        return Product("VOUCHER","Voucher", 5.0)
    }
}