package salva.perez.kotlinmvproject.app.ui.voucher

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherContract
import salva.perez.kotlinmvproject.domain.interactor.AbstractInteractor
import salva.perez.kotlinmvproject.domain.model.Product
import salva.perez.kotlinmvproject.data.network.rest.RetrofitAdapter

class VoucherImpl: AbstractInteractor() {

    private val TAG = "VoucherImpl"

    private val voucherApiServe by lazy {
        RetrofitAdapter.create()
    }

    override fun run() {
    }

    fun getVouchers(callback: VoucherContract.Callback){
        disposable = voucherApiServe.getVoucher()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> processVouchers(result.body()?.products, callback)},
                { error -> error(error.message)}
            )
    }

    override fun destroy() {
        removeCallbacks()
    }

    override fun removeCallbacks() {
        clearComposite()
    }

    private fun processVouchers(vouchers : List<Product>?, callback: VoucherContract.Callback?){
        callback?.onResponseVouchers(vouchers)
    }

    private fun error(message : String?){
        Log.e(TAG, message)
    }
}