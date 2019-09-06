package salva.perez.kotlinmvproject.data.network.exceptions


open class NetworkException(error: Throwable): RuntimeException(error)
class NoNetworkException(error: Throwable): NetworkException(error)
class ServerUnreachableException(error: Throwable): NetworkException(error)
class HttpCallFailureException(error: Throwable): NetworkException(error)