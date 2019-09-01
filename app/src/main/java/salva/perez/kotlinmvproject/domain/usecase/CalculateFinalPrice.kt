package salva.perez.kotlinmvproject.domain.usecase

import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import java.text.DecimalFormat

class CalculateFinalPrice {

    companion object {
        const val TSHIRT_DISCOUNT_CODE = "TSHIRT"
        const val VOUCHER_DISCOUNT_CODE = "VOUCHER"
        const val MUG = "MUG"
        const val DISCOUNT_TSHIRT = 19.0
    }

    private val sum: Int = 1
    private var priceShirt : Double = 0.0
    private var priceVoucher : Double = 0.0
    private var priceMug : Double = 0.0


    fun getTotalSumProductCheckOut(): Double{
        priceShirt = 0.0
        priceVoucher = 0.0
        priceMug = 0.0
        return  getDiscountTshirt() + getDiscountVoucher() + getTotalMug()
    }

    fun getDiscountTshirt() : Double{
        var tshirt: Int = 0
        for(checkDiscount in VoucherRepository.vouchers){
            if(checkDiscount.code.contentEquals(TSHIRT_DISCOUNT_CODE)){
                tshirt = tshirt.plus(sum)
                priceShirt = checkDiscount.price
            }
        }

        return calculateShirtDiscount(tshirt, priceShirt)
    }

    private fun calculateShirtDiscount(tshirt: Int, priceShirt: Double): Double{
        return when (tshirt) {
            1, 2 -> tshirt.times(priceShirt)
            else -> tshirt.times(DISCOUNT_TSHIRT)
        }
    }

    fun getDiscountVoucher(): Double{
        var voucher: Int = 0
        for(checkDiscount in VoucherRepository.vouchers){
            if(checkDiscount.code.contentEquals(VOUCHER_DISCOUNT_CODE)){
                voucher = voucher.plus(sum)
                priceVoucher = checkDiscount.price
            }
        }

        return when (voucher) {
            1 -> voucher.times(priceVoucher)
            else -> calculateVoucherDiscount(voucher, priceVoucher)
        }
    }

    private fun calculateVoucherDiscount(voucher : Int, priceVoucher: Double): Double{
        if(voucher.rem(2) == 0){
            return voucher * priceVoucher / 2
        }

        return ((voucher - 1) * priceVoucher) / 2 + priceVoucher
    }

    fun getTotalMug(): Double{
        var mug: Int = 0

        for(checkDiscount in VoucherRepository.vouchers){
            if(checkDiscount.code.contentEquals(MUG)){
                mug = mug.plus(sum)
                priceMug = checkDiscount.price
            }
        }
        return mug.times(priceMug)
    }

    fun formatCredit(credit: String): String {
        val format = DecimalFormat("###,###.##")

        return format.format(credit.toDouble())
    }
}