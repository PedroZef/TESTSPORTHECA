package br.com.dio.testsportheca.data.repository

import android.util.Log
import br.com.dio.testsportheca.data.service.PlayersService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow


class PlayerRepositoryImpl(private val apiPlayer: PlayersService) : PlayerRepository  {

    override suspend fun getPlayer() = flow {
        coroutineScope {
            try {
                val response = apiPlayer.getPlayers()
                emit(response)
            } catch (e: Exception){
                Log.d("ERROR", e.message.toString())
            }
        }
    }
}