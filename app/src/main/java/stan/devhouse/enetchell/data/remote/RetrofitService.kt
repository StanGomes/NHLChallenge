package stan.devhouse.enetchell.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    /**
     * Only one instance of RetrofitService will be created
     */

    val apiService : ApiService by lazy {
        val baseUrl = "https://statsapi.web.nhl.com/api/v1/"
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return@lazy retrofit.create(ApiService::class.java)
    }


}