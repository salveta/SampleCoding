package salva.perez.kotlinmvproject.app.ui.voucher

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import salva.perez.kotlinmvproject.ChallengeApplication
import salva.perez.kotlinmvproject.app.idlingResource.EspressoTestingIdlResource
import salva.perez.kotlinmvproject.app.idlingResource.SimpleIdlingResource
import salva.perez.kotlinmvproject.data.network.exceptions.clientErrorResponse
import salva.perez.kotlinmvproject.data.network.exceptions.errorResponse
import salva.perez.kotlinmvproject.domain.interactor.AbstractInteractor
import salva.perez.kotlinmvproject.data.network.rest.RetrofitAdapter
import salva.perez.kotlinmvproject.domain.model.Vouchers

class VoucherImpl: AbstractInteractor() {

    private val TAG = "VoucherImpl"
    private var idlingResource: SimpleIdlingResource? = null

    private val voucherApiServe by lazy {
        RetrofitAdapter.create()
    }

    override fun run() {}

    fun getVouchers(callback: VoucherContract.Callback){
        this.idlingResource = idlingResource
        idlingResource?.setIdleState(false)

        EspressoTestingIdlResource.increment()
        disposable = voucherApiServe.getVoucher()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> if (result.isSuccessful) processVouchers(result, callback) else clientErrorResponse(result?.code(), context = ChallengeApplication.applicationContext()) },
                { error -> errorResponse(error, context = ChallengeApplication.applicationContext()) }
            )
    }

    override fun destroy() {
        removeCallbacks()
    }

    override fun removeCallbacks() {
        clearComposite()
    }

    private fun processVouchers(result : Response<Vouchers>, callback: VoucherContract.Callback?){
        callback?.onResponseVouchers(result.body()?.products)

        if (!EspressoTestingIdlResource.idlingResource.isIdleNow) {
            EspressoTestingIdlResource.decrement()
        }
    }
}