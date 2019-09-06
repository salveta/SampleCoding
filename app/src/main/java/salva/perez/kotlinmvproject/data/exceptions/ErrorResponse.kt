package salva.perez.kotlinmvproject.data.network.exceptions

import android.content.Context
import android.widget.Toast


fun errorResponse(error: Throwable, context: Context) {
    when (error) {
        is NoNetworkException -> displayNoNetworkError(context)
        is ServerUnreachableException -> displayServerUnreachableError(context)
        is HttpCallFailureException -> displayCallFailedError(context)
        else -> displayGenericError(context)
    }
}

private fun displayNoNetworkError(context: Context) {
    Toast.makeText(context, "No network!", Toast.LENGTH_SHORT).show()
}

private fun displayServerUnreachableError(context: Context) {
    Toast.makeText(context, "Server is unreachable!", Toast.LENGTH_SHORT).show()
}

private fun displayCallFailedError(context: Context) {
    Toast.makeText(context, "Call failed!", Toast.LENGTH_SHORT).show()
}

private fun displayGenericError(context: Context) {
    Toast.makeText(context, "Generic error", Toast.LENGTH_SHORT).show()
}