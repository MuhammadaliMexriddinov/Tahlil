package uz.catsi.tahlil.data.remote.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface TestApi {

    @Multipart
    @Headers("Accept: application/json")
    @POST
    suspend fun test(
        @Url url: String,
        @Part("alpha") alpha: RequestBody?,
        @Part("beta") beta: RequestBody?,
        @Part("n") n: RequestBody?,
        @Part("i0") i0: RequestBody?,
        @Part("r0") r0: RequestBody?,
        @Part("time") time: RequestBody?
    ): ResponseBody?
}