package salva.perez.kotlinmvproject.app.navigator

import android.content.Context
import android.content.Intent
import salva.perez.kotlinmvproject.app.ui.utils.ArgumentsNameConfig.TOTAL_CHECKOUT

class Navigator {

    object Checkout {
        fun openCheckoutActivity(context: Context, clazz: Class<*>, quantity : Double) {
            val intent = Intent(context, clazz)
            intent.putExtra(TOTAL_CHECKOUT, quantity)
            context.startActivity(intent)
        }
    }
}