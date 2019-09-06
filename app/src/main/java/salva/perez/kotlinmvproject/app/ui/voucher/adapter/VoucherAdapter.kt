package salva.perez.kotlinmvproject.app.ui.voucher.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_voucher_product.view.*
import salva.perez.kotlinmvproject.R
import salva.perez.kotlinmvproject.app.ui.utils.inflate
import salva.perez.kotlinmvproject.domain.usecase.CalculateFinalPrice
import salva.perez.kotlinmvproject.domain.model.Product

class VoucherAdapter(private val voucherList: List<Product>?, private val listener: (Product) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<VoucherAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate(parent.context, R.layout.item_voucher_product, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(voucherList!![position], listener)
    }

    override fun getItemCount(): Int {
        return voucherList!!.size
    }

    class MyViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(voucherItem: Product, listener: (Product) -> Unit) = with(itemView) {
            txVoucherName?.text = voucherItem.name
            txVoucherCode?.text = voucherItem.code
            var currency = "${CalculateFinalPrice().formatCredit(voucherItem.price.toString())} ${resources.getString(R.string.euro)}"
            txVoucherPrice?.text = currency
            itemView.setOnClickListener{listener(voucherItem)}
        }
    }
}