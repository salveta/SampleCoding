package salva.perez.kotlinmvproject

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherContract
import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import salva.perez.kotlinmvproject.domain.model.Product
import java.io.IOException

class VoucherRepositoryTest {
    @Test
    fun check_objects_are_removed(){
        VoucherRepository.addProductToShoppingCart(fakeObject())
        VoucherRepository.addProductToShoppingCart(fakeObject())

        val checkoutGet = VoucherRepository.getProductsInShoppinCart()
        Assert.assertNotNull(fakeObject())
        Assert.assertEquals(2, checkoutGet!!.size)

        VoucherRepository.cleanProductsInShoppinCart()
        Assert.assertEquals(0, checkoutGet!!.size)
    }

    @Test
    fun check_save_get_objects() {
        VoucherRepository.addProductToShoppingCart(fakeObject())
        val checkoutGet = VoucherRepository.getProductsInShoppinCart()

        Assert.assertNotNull(fakeObject())
        Assert.assertNotNull(checkoutGet)
        Assert.assertEquals(fakeObject().name, checkoutGet!![0].name)

        VoucherRepository.cleanProductsInShoppinCart()
        Assert.assertEquals(0, checkoutGet!!.size)
    }

    @Test
    @Throws(IOException::class)
    fun check_get_products() {
        val mock = mock<VoucherContract.Model>()
        val myList = listOf(fakeObject() , fakeObject() , fakeObject())

        Assert.assertNotNull(fakeObject())
        Mockito.`when`(mock.getProductsInShoppinCart()).thenReturn(myList)
    }

    private fun fakeObject(): Product {
        return Product("VOUCHER", "voucher", 5.0)
    }
}