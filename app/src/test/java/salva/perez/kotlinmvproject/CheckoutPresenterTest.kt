package salva.perez.kotlinmvproject

import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutContract
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutImpl
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutPresenter
import salva.perez.kotlinmvproject.data.repository.CheckoutRepository
import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import salva.perez.kotlinmvproject.domain.model.Checkout
import salva.perez.kotlinmvproject.domain.model.Product


class CheckoutPresenterTest {

    @InjectMocks
    var mock: CheckoutPresenter? = null
    @Mock
    private var mView: CheckoutContract.View? = null
    @Mock
    private var mInteractor: CheckoutImpl? = null
    @Mock
    private var checkoutRepository: CheckoutRepository? = null
    @Mock
    private var voucherRepository: VoucherRepository? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun show_products_to_checkout(){
        var checkoutList: MutableList<Checkout> = mutableListOf<Checkout>()
        mock!!.createCheckoutObject(3,2,1)
        verify(mView!!).showCheckoutProducts(checkoutList)
    }

    @Test
    fun add_voucher(){
        var checkoutList: MutableList<Product> = mutableListOf<Product>()
        var checkout = Product("VOUCHER", "voucher", 5.0)

        checkoutList.add(checkout)
        mock!!.addVoucherInCheckout(checkoutList, 1)
    }

    @Test
    fun restart_data_after_shopping_done() {
        mock!!.onShoppingDone()
        mock!!.cleanCheckout()
        verify(mView!!).shoppingDone()
    }
}