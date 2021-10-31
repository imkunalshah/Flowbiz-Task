package com.kunal.flowbiztask.data.network
import com.kunal.flowbiztask.data.network.responses.QuestionsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    @GET("questions")
    suspend fun getQuestions(@Query("key") key:String, @Query("order") order:String,@Query("sort") sort:String,@Query("site") site:String):Response<QuestionsResponse>

    companion object{
        operator fun invoke(
            interceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}

