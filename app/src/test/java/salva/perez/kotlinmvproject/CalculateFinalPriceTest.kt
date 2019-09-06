package salva.perez.kotlinmvproject

import org.assertj.core.api.Assertions
import org.junit.Test
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice

class CalculateFinalPriceTest{

    val calculateVouchers = CalculateFinalPrice()

    @Test
    fun `correct operation 2x1 in voucher`() {
        val result = calculateVouchers.calculateVoucherDiscount(5, 5.0)
        Assertions.assertThat(result).isEqualTo(15.0)
    }

    @Test
    fun `correct operation when no offer 2x1 in voucher`() {
        val result = calculateVouchers.calculateVoucherDiscount(1, 5.0)
        Assertions.assertThat(result).isEqualTo(5.0)
    }

    @Test
    fun `correct operation when more than 3 Tshirst`() {
        val result = calculateVouchers.calculateShirtDiscount(3, 20.0)
        Assertions.assertThat(result).isEqualTo(57.0)
    }

    @Test
    fun `correct price when less than 3 Tshirst`() {
        val result = calculateVouchers.calculateShirtDiscount(2, 20.0)
        Assertions.assertThat(result).isEqualTo(40.0)
    }
}