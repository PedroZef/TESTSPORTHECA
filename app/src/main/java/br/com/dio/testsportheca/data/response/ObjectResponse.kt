package br.com.dio.testsportheca.data.response

import com.google.gson.annotations.SerializedName

data class ObjectResponse(
    @SerializedName("Player")
    val player: PlayerResponse?
                           )
