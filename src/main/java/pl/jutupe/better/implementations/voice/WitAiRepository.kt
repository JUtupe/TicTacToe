package pl.jutupe.better.implementations.voice

import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface WitAiRepository {
    @Headers("Content-Type: audio/wav")
    @POST("speech")
    fun getPosition(
            @Header("Authorization") authorization: String,
            @Body wavFile: RequestBody
    ): Call<WitResponse>
}

data class WitResponse (
        @SerializedName("_text") val text: String,
        @SerializedName("msg_id") val messageId: String,
        @SerializedName("entities") val entities: WitEntities
)

data class WitEntities (
        @SerializedName("row") val row: List<Position>?,
        @SerializedName("column") val column: List<Position>?
) {
    data class Position (
            @SerializedName("value") val value: Int
    )
}