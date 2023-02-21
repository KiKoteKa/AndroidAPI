package zubkov.vadim.pruebasandroiddiseo.core.network

import com.google.android.datatransport.runtime.dagger.Provides
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object RetrofitHelper {
    //NoteBook IP
    private const val IP_Note="http://192.168.153.122:8080"
    private const val IP_NoteHome="http://192.168.1.144:8080"

    //PC IP
    private const val IP_Comp="http://192.168.1.143:8080"
    //LocalHost IP
    private const val IP_LocalHost="http://10.0.2.2:8080"
    //Token
    private var AuthToken: String? = null

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { token ->
            token.proceed(token.request().newBuilder().also {
                it.addHeader("Authorization", "Bearer $AuthToken")
            }.build())
        }
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(IP_Note)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}