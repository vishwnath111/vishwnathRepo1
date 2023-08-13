package com.app.demo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.models.Data
import com.google.android.gms.common.api.Response
import retrofit2.Call
import retrofit2.Callback

class HomeViewModel:ViewModel() {
    fun getPopularMovies() {
      var movieLiveData = MutableLiveData<List<Data>>()
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        apiInterface.api.getPopularMovies("69d66957eebff9666ea46bd464773cf0").enqueue(object  :
            Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()!!.results
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Data>> {
        return movieLiveData
    }

}