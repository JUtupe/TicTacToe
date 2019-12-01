package pl.jutupe.better.implementations.voice

import okhttp3.MediaType
import okhttp3.RequestBody
import pl.jutupe.better.core.Field
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object WitAiService {
    //just a client token, safe for public usage :)
    private const val authorization: String = "Bearer Q6SUX7MSEZABCXO2UDR5DA3G4LZHSZL5"

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.wit.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service: WitAiRepository =
            retrofit.create(WitAiRepository::class.java)

    fun getCoordinatesForWav(wavFile: File): Pair<Int, Int> {
        val filePart = RequestBody.create(MediaType.parse("application/octet-stream"), wavFile)
        val call = service.getPosition(authorization, filePart)
        val response = call.execute()

        println("message $response")

        val entities = response.body()?.entities

        println(response.body()?.text)

        return Pair(
                entities?.column?.get(0)?.value?.minus(1) ?: Field.INVALID_FIELD_INDEX,
                entities?.row?.get(0)?.value?.minus(1) ?: Field.INVALID_FIELD_INDEX
        )
    }
}