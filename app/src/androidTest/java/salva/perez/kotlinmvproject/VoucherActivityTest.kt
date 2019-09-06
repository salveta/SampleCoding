package salva.perez.kotlinmvproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.rule.ActivityTestRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import salva.perez.kotlinmvproject.app.idlingResource.EspressoTestingIdlResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not
import salva.perez.kotlinmvproject.assertions.CustomAssertions.Companion.hasItemCount
import salva.perez.kotlinmvproject.app.ui.voucher.VoucherActivity
import salva.perez.kotlinmvproject.assertions.RecyclerViewMatcher


class VoucherActivityTest {

    private var mIdlingResource: IdlingResource? = null
    private val VOUCHER_NAME = "Voucher"
    private val VOUCHER_CODE = "VOUCHER"
    private val VOUCHER_PRICE = "5 €"


    @Rule
    @JvmField
    var activityRule = ActivityTestRule<VoucherActivity>(VoucherActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoTestingIdlResource.idlingResource)
        mIdlingResource = activityRule.activity.getIdlingResource()
    }

    @Test
    fun check_shopping_is_not_visible() {
        onView(withId(R.id.imIcoShopping)).check(matches(not(isDisplayed())))
    }

    @Test
    fun check_recycler_is_visible_and_not_text() {
        onView(withId(R.id.rvVouchers)).check(matches(isDisplayed()))
        onView(withId(R.id.txVoucherUnavailable)).check(matches(not(isDisplayed())))
    }

    @Test
    fun click_in_element_and_check_voucher_elements() {
        onView(withRecyclerView(R.id.rvVouchers).atPosition(0)).check(matches(isDisplayed()))

        onView(withRecyclerView(R.id.rvVouchers).atPositionOnView(0, R.id.txVoucherName))
            .check(matches(isDisplayed())).check(matches(withText(VOUCHER_NAME)))
        onView(withRecyclerView(R.id.rvVouchers).atPositionOnView(0, R.id.txVoucherCode))
            .check(matches(isDisplayed())).check(matches(withText(VOUCHER_CODE)))
        onView(withRecyclerView(R.id.rvVouchers).atPositionOnView(0, R.id.txVoucherPrice))
            .check(matches(isDisplayed())).check(matches(withText(VOUCHER_PRICE)))

        onView(withId(R.id.rvVouchers)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun voucher_is_populate_in_recyclerview_has_adapter_and_must_be_three() {
        onView(withId(R.id.rvVouchers)).check(hasItemCount(3))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlResource.idlingResource)
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}
