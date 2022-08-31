package com.ork.cryptocurrency.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ork.cryptocurrency.R
import com.ork.cryptocurrency.adapter.RecyclerViewAdapter
import com.ork.cryptocurrency.databinding.ActivityMainBinding
import com.ork.cryptocurrency.model.CryptoModel
import com.ork.cryptocurrency.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener{

    private val BASE_URL = "https://api.nomics.com/v1/currencies/"
    lateinit var binding: ActivityMainBinding

    private var cryptoModels: ArrayList<CryptoModel>? = null

    private var recyclerViewAdapter:RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecyclerView

//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = recyclerViewAdapter

        val layoutManager :RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadData()
    }

    fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
              if (response.isSuccessful){
                  response.body()?.let {
                      cryptoModels = ArrayList(it)


                      cryptoModels?.let {
                          recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                          binding.recyclerView.adapter = recyclerViewAdapter
                      }


                      for (cryptoModel:CryptoModel in cryptoModels!!){
                          println(cryptoModel.currency)
                          println(cryptoModel.price)

                      }



                  }
              }else{
                  println("Error")
              }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
               t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {

        Toast.makeText(this, "Clicked: ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }
}