package salva.perez.kotlinmvproject.data.network.rest

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import salva.perez.kotlinmvproject.data.network.api.Api
import salva.perez.kotlinmvproject.domain.model.Vouchers
import java.util.concurrent.TimeUnit

interface RetrofitAdapter {

    @GET(Api.ENDPOINT.VOUCHER_ENDPOIN)
    fun getVoucher(): Observable<Response<Vouchers>>

    companion object {
        fun create(): RetrofitAdapter {

            val retrofit = Retrofit.Builder()
                .baseUrl(Api().BASE_URL)
                .client(provideOkHTTPClient())
                .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                    )
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(RetrofitAdapter::class.java)
        }

        fun provideOkHTTPClient(): OkHttpClient {

            val httpClient = OkHttpClient.Builder()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)

            httpClient.networkInterceptors().add(Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")

                chain.proceed(requestBuilder.build())
            })

            httpClient.addInterceptor(logging)

            return httpClient.build()
        }
    }
}