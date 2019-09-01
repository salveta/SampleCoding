package salva.perez.kotlinmvproject.di

import org.koin.dsl.module
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutContract
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutPresenter
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherContract
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherPresenter
import salva.perez.kotlinmvproject.data.repository.CheckoutRepository
import salva.perez.kotlinmvproject.data.repository.VoucherRepository
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutImpl
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherImpl

val applicationModule = module(override = true) {
    factory<VoucherContract.Presenter> { (view: VoucherContract.View) -> VoucherPresenter(view, get(), get()) }
    factory{ VoucherImpl() }

    factory<CheckoutContract.Presenter> {(view: CheckoutContract.View) -> CheckoutPresenter(view, get(), get(), get())}
    factory{ CheckoutImpl() }

    single{ VoucherRepository }
    single{ CheckoutRepository }
}
