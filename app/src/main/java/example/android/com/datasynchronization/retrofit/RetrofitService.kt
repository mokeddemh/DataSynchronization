package example.android.com.datasynchronization.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson



object RetrofitService {

    var gson = GsonBuilder()
            .setLenient()
            .create()

    val endpoint: Endpoint by lazy {
        Retrofit.Builder().baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create(gson)).build()
                .create(Endpoint::class.java)

    }
}