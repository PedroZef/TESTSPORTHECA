package br.com.dio.testsportheca.data.service

import br.com.dio.testsportheca.data.response.DataResponse
import retrofit2.http.GET

interface PlayersService {

    @GET("/teste/teste.json")
    suspend fun getPlayers(): DataResponse
}