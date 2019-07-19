package example.android.com.datasynchronization.retrofit

import com.google.gson.GsonBuilder
import example.android.com.datasynchronization.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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