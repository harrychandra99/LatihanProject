package org.meicode.latihanproject1.DataModel

import org.meicode.latihanproject1.DataModel.RetrofitService

class MainRepository constructor(private val retrofitService : RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}