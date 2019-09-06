package salva.perez.kotlinmvproject.app.ui.checkout.adapter

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_checkout.view.*
import salva.perez.kotlinmvproject.R
import salva.perez.kotlinmvproject.app.ui.utils.inflate
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice.Companion.MUG
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice.Companion.TSHIRT_DISCOUNT_CODE
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice.Companion.VOUCHER_DISCOUNT_CODE
import salva.perez.kotlinmvproject.domain.model.Checkout

class CheckoutAdapter(private val voucherList: List<Checkout>?, private val listener: (Checkout) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<CheckoutAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate(parent.context, R.layout.item_checkout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(voucherList!![position], listener)
    }

    override fun getItemCount(): Int {
        return voucherList!!.size
    }

    class MyViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(checkoutItem: Checkout, listener: (Checkout) -> Unit) = with(itemView) {
            txProductNameCheckout?.text = checkoutItem.name
            var currency = "${CalculateFinalPrice().formatCredit(checkoutItem.totalPrice.toString())} ${resources.getString(R.string.euro)}"
            txTotalPriceCheckout?.text = currency

            if(checkoutItem.code.contentEquals(VOUCHER_DISCOUNT_CODE)){
                when (checkoutItem.units) {
                    1 -> concatenatedNoOffer(txUnits!!, resources, checkoutItem)
                    else -> concatenatedOfferr(txUnits!!, resources, checkoutItem, resources.getString(R.string.offer_voucher))
                }
            }

            if(checkoutItem.code.contentEquals(TSHIRT_DISCOUNT_CODE)){
                when (checkoutItem.units) {
                    1,2 -> concatenatedNoOffer(txUnits!!, resources, checkoutItem)
                    else -> concatenatedOfferr(txUnits!!, resources, checkoutItem, resources.getString(R.string.offer_tshirt))
                }
            }

            if(checkoutItem.code.contentEquals(MUG)){
                val concatenatedOffer = "${resources.getString(R.string.units)} ${checkoutItem.units}"
                txUnits!!.text = concatenatedOffer
            }
        }

        private fun concatenatedOfferr(txUnits: TextView?, resources: Resources, checkoutItem: Checkout, offerString: String){
            var concatenatedOffer = "${resources.getString(R.string.units)} ${checkoutItem.units} $offerString"
            txUnits!!.text = concatenatedOffer
        }

        private fun concatenatedNoOffer(txUnits: TextView?, resources: Resources, checkoutItem: Checkout){
            var concatenatedNoOffer = "${resources.getString(R.string.units)} ${checkoutItem.units}"
            txUnits!!.text = concatenatedNoOffer
        }
    }
}