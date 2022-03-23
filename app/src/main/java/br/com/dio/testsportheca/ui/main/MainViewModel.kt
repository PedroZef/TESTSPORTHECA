package br.com.dio.testsportheca.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.testsportheca.data.repository.PlayerRepository
import br.com.dio.testsportheca.data.response.DataResponse
import br.com.dio.testsportheca.data.response.PlayerResponse
import br.com.dio.testsportheca.utils.Failed
import br.com.dio.testsportheca.utils.Loading
import br.com.dio.testsportheca.utils.Resource
import br.com.dio.testsportheca.utils.Success
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel (private val repository: PlayerRepository): ViewModel() {

    private val resultLiveData = MutableStateFlow<Resource<DataResponse>>(Loading())
    val _resultLiveData: StateFlow<Resource<DataResponse>>
        get() = resultLiveData

    init {
        getPlayer()
    }

    fun getPlayer() {
        viewModelScope.launch {
            repository.getPlayer()
                .onStart {
                    resultLiveData.value = Loading()
                }
                .catch {
                    resultLiveData.value = Failed()
                }
                .collect {
                    resultLiveData.value = Success(it)
                }
        }
    }
}
