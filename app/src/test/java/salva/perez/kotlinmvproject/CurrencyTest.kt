package salva.perez.kotlinmvproject

import org.assertj.core.api.Assertions
import org.junit.Test
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice

class CurrencyTest {

    @Test
    fun `correct remove currency zero decimal`() {
        val result = CalculateFinalPrice().formatCredit("2.0")
        Assertions.assertThat(result).isEqualToIgnoringCase("2")
    }

    @Test
    fun `correct currency price formatting with decimal`() {
        val result = CalculateFinalPrice().formatCredit("7.5")
        Assertions.assertThat(result).isEqualToIgnoringCase("7,5")
    }
}