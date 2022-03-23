package br.com.dio.testsportheca.data.repository

import br.com.dio.testsportheca.data.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun getPlayer(): Flow<DataResponse>
}