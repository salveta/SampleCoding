package salva.perez.kotlinmvproject

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.`when`
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutContract
import salva.perez.kotlinmvproject.domain.model.Checkout
import java.io.IOException
import salva.perez.kotlinmvproject.data.repository.CheckoutRepository


class CheckoutRepositoryTest {

    @Test
    fun check_objects_are_removed(){
        CheckoutRepository.addProductToShoppingCart(fakeObject())
        CheckoutRepository.addProductToShoppingCart(fakeObject())

        val checkoutGet = CheckoutRepository.getProductsInShoppinCart()
        assertNotNull(fakeObject())
        assertEquals(2, checkoutGet!!.size)

        CheckoutRepository.cleanCheckout()
        assertEquals(0, checkoutGet!!.size)
    }

    @Test
    fun check_save_get_objects() {
        CheckoutRepository.addProductToShoppingCart(fakeObject())
        val checkoutGet = CheckoutRepository.getProductsInShoppinCart()

        assertNotNull(fakeObject())
        assertNotNull(checkoutGet)
        assertEquals(fakeObject().name, checkoutGet!![0].name)

        CheckoutRepository.cleanCheckout()
        assertEquals(0, checkoutGet.size)
    }

    @Test
    @Throws(IOException::class)
    fun check_get_products() {
        val mock = mock<CheckoutContract.Model>()
        val myList = listOf(fakeObject() , fakeObject() , fakeObject())

        assertNotNull(fakeObject())
        `when`(mock.getProductsInShoppinCart()).thenReturn(myList)
    }

    private fun fakeObject(): Checkout{
        return Checkout("VOUCHER", "voucher", 35.0, 5)
    }
}