package salva.perez.kotlinmvproject.app.ui.toolbars


import android.view.View
import kotlinx.android.synthetic.main.voucher_toolbar.*
import org.koin.core.KoinComponent
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherActivity
import salva.perez.kotlinmvproject.app.base.BaseActivity

abstract class ToolbarVoucherActivity : BaseActivity(), KoinComponent {

    private var count: Int = 0
    private val sum: Int = 1

    protected fun configureActionBar(activity: VoucherActivity) {
        setToolbar(activity)
    }

    private fun setToolbar(activity: VoucherActivity) {
        setSupportActionBar(toolbar!!)
        rlShoppingToolbar?.setOnClickListener { checkShoppingCart(activity) }
    }

    protected fun showShoppingCartInToolbar(){
        rlShoppingToolbar?.visibility = View.VISIBLE

        count = count.plus(sum)
        txShoppingTotalNumberToolbar?.text = count.toString()
    }

    private fun checkShoppingCart(activity: VoucherActivity){
        activity.checkShoppingCart()
    }

    fun hideShoppingCart(voucher : Boolean){
        if(voucher) count = 0
        rlShoppingToolbar?.visibility = if(voucher) View.INVISIBLE else View.VISIBLE
    }
}