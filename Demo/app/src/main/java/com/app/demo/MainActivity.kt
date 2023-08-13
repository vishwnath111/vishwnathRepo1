package com.app.demo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.app.demo.adapter.CustomAdapter
import com.app.demo.databinding.ActivityMainBinding
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.models.Data
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dropDownList: List<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserList()

        val images = listOf(R.drawable.banner_profile,R.drawable.banner_job_a,R.drawable.banner_job_b)
        val adapter = ViewPagerAdapter(images)
        binding.viewPager.adapter= adapter

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                // changeColor()
                override fun onPageScrollStateChanged(state: Int) {
                }
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    changeColor()
                }
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            }
        )
    }

     //for change color
    fun changeColor(){
        when(binding.viewPager.currentItem){
            0 ->{
                binding.iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.white))
                binding.iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.circleDotColor))
                binding.iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.circleDotColor))
            }
            1 ->{
                binding.iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.circleDotColor))
                binding.iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.white))
                binding.iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.circleDotColor))
            }
            2 ->{
                binding.iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.circleDotColor))
                binding.iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.circleDotColor))
                binding.iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.white))
            }
        }
    }


    fun getUserList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful()) {
                    var json = Gson().toJson(response.body())
                    if (response.body()?.data?.size!! <= 0) {
                        Toast.makeText(this@MainActivity, "No Data ", Toast.LENGTH_LONG).show()
                    }else{
                        dropDownList  = response.body()?.data!!
                        binding.rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                        val adapter = CustomAdapter(dropDownList as ArrayList<Data>)
                        binding.rvMain.adapter = adapter
                       }
                  }else{
                    Toast.makeText(this@MainActivity, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                 }
               }catch (Ex: Exception){
                Log.e("Error",Ex.localizedMessage)
            }
    } }
}