package com.inforcap.exampleapiresttmdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inforcap.exampleapiresttmdb.model.FigureDetailEntity
import com.inforcap.exampleapiresttmdb.model.FigureEntity
import com.inforcap.exampleapiresttmdb.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private var _movieList = MutableLiveData<List<FigureEntity>>()
    val movieList: LiveData<List<FigureEntity>> = _movieList

    private val _figureDetail = MutableLiveData<FigureDetailEntity>()
    val figureDetail: LiveData<FigureDetailEntity> = _figureDetail

    fun getAllFigures() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = RetrofitClient.apiService.getAllFigures()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movieList.postValue(it) // Actualiza el LiveData con la lista de películas
                    }
                } else {
                    Log.e("MovieViewModel", "Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Exception: ${e.message}")
            }
        }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getDetail(id)
                if (response.isSuccessful) {
                    response.body()?.let { detail ->
                        _figureDetail.postValue(detail) // Actualiza el LiveData
                    }
                } else {
                    Log.e("MovieViewModel", "Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Exception: ${e.message}")
            }
        }
    }

}