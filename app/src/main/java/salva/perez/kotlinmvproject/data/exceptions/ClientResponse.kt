package salva.perez.kotlinmvproject.data.network.exceptions

import android.content.Context
import android.widget.Toast

    const val NO_EXIST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val CONFLICT = 409
    const val SERVER= 500
    const val ERROR = 503


    fun clientErrorResponse(code: Int?, context: Context) {
        when (code) {
            NO_EXIST -> noExistError(code, context)
            UNAUTHORIZED -> unauthorizedError(code, context)
            FORBIDDEN -> forbiddenError(code, context)
            NOT_FOUND -> notFoundError(code, context)
            CONFLICT -> conflictError(code, context)
            in SERVER..ERROR -> serverError(code, context)
            else -> numberTooHigh(code, context)
        }
    }

    private fun noExistError(code: Int?, context: Context) {
        Toast.makeText(context, "No exist code: $code", Toast.LENGTH_SHORT).show()
    }

    private fun unauthorizedError(code: Int?, context: Context) {
        Toast.makeText(context, "Unauhorice: $code", Toast.LENGTH_SHORT).show()
    }

    private fun forbiddenError(code: Int?, context: Context) {
        Toast.makeText(context, "Forbidden: $code", Toast.LENGTH_SHORT).show()
    }

    private fun notFoundError(code: Int?, context: Context) {
        Toast.makeText(context, "Not found: $code", Toast.LENGTH_SHORT).show()
    }

    private fun conflictError(code: Int?, context: Context) {
        Toast.makeText(context, "Conflict: $code", Toast.LENGTH_SHORT).show()
    }

    private fun serverError(code: Int?, context: Context) {
        Toast.makeText(context, "Error Server: $code", Toast.LENGTH_SHORT).show()
    }

    private fun numberTooHigh(code: Int?, context: Context) {
        Toast.makeText(context, "NumberTooHigh: $code", Toast.LENGTH_SHORT).show()
    }