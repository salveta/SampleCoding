package salva.perez.kotlinmvproject

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import salva.perez.kotlinmvproject.app.ui.checkout.CheckoutActivity
import org.junit.Before
import salva.perez.kotlinmvproject.assertions.RecyclerViewMatcher
import salva.perez.kotlinmvproject.domain.model.Checkout


class CheckoutActivityTest{

    private val VNAME = "Voucher"
    private val VPRICE = "5 €"

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<CheckoutActivity>(CheckoutActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
        fun setUp() {
            val intent = Intent()
            intent.putExtra("total.checkout", 3.5)
            activityRule.launchActivity(intent)
        }

    @Test
    fun show_vouchers_data_list(){
        activityRule.activity.runOnUiThread {
            activityRule.activity.showCheckoutProducts(dummyVouchersData())
        }

        onView(withRecyclerView(R.id.rvCheckout).atPosition(0)).check(matches(isDisplayed()))

        onView(withRecyclerView(R.id.rvCheckout).atPositionOnView(0, R.id.txProductNameCheckout))
            .check(matches(isDisplayed())).check(matches(withText(VNAME)))
        onView(withRecyclerView(R.id.rvCheckout).atPositionOnView(0, R.id.txTotalPriceCheckout))
            .check(matches(isDisplayed())).check(matches(withText(VPRICE)))
    }

    @Test
    fun click_in_credit_card_button() {
        onView(withId(R.id.btnCreditCard)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCreditCard)).perform(click())
    }

    @Test
    fun click_in_paypal_button(){
        onView(withId(R.id.btnPaypal)).check(matches(isDisplayed()))
        onView(withId(R.id.btnPaypal)).perform(click())
    }

    @Test
    fun check_price_intent_is_received(){
        onView(withId(R.id.txTotalPayment)).check(matches(withText("3,5 €")))
    }

    private fun dummyVouchersData() : List<Checkout> {
        val vouchers: MutableList<Checkout> = mutableListOf<Checkout>()
        val checkout = Checkout(name = "Voucher", code = "VOUCHER", totalPrice = 5.0, units = 2)
        val checkout2 = Checkout(name = "T-Shirt", code = "TSHIRT", totalPrice = 5.0, units = 2)

        vouchers.add(checkout)
        vouchers.add(checkout2)

        return vouchers
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}