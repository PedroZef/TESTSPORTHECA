package br.com.dio.testsportheca.data.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

    @SerializedName("Object")
    val objectList: List<ObjectResponse>?,

    @SerializedName("Status")
    val status: Int?
)
