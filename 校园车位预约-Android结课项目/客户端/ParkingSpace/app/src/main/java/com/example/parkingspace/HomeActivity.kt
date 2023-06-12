package com.example.parkingspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private var list = arrayListOf<String>()

    override fun onStart() {
        querySpaceByOpen()
        super.onStart()
    }
    override fun onRestart() {
        querySpaceByOpen()
        super.onRestart()
    }
    override fun onResume() {
        querySpaceByOpen()
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listView = findViewById(R.id.listView)

        querySpaceByOpen()

        // 车位点击事件
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this@HomeActivity, "你选择的是${list.get(position)}", Toast.LENGTH_SHORT).show()
            updateSpace(list.get(position), "1")
        }

    }

    // 查询空闲车位
    private fun querySpaceByOpen() {
        var okHttpclient: OkHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url("http://10.0.2.2:8080/position/queryAllFreePosition")
            .build()
        GlobalScope.launch(Dispatchers.IO) {
            var data = mutableListOf<String>()
            var response = okHttpclient.newCall(request).execute()
            if (response.isSuccessful) {
                var responseData = response.body?.string()
                if (responseData != null) {
                    data = parseJSONWithJSONObject(responseData)
                }

                GlobalScope.launch(Dispatchers.Main) {
                    //Dispatchers.Main切换到UI线程
                    arrayListOf<String>()
                    for (i in 0..data.size - 1) {
                        list.add(data.get(i))
                    }
                    listView.adapter = ArrayAdapter<String>(this@HomeActivity, android.R.layout.simple_list_item_1, data)
                }
            }
        }
    }

    // 更新车位信息
    private fun updateSpace(place: String, status: String) {
        var okHttpclient: OkHttpClient = OkHttpClient.Builder().build()
        var url = "http://10.0.2.2:8080/position/updatePosition"

        val urlBuilder = url.toHttpUrlOrNull()?.newBuilder()
            ?.apply {
                addQueryParameter("place",place)
                addQueryParameter("status",status)
            }

        val request = Request.Builder()
            .url(urlBuilder!!.build())
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            var response = okHttpclient.newCall(request).execute()
            GlobalScope.launch(Dispatchers.Main) {
                var intent = Intent(this@HomeActivity, ReservationActivity::class.java)
                intent.putExtra("place", place)
                startActivity(intent)
            }
        }
    }

    // JSON格式转换
    private fun parseJSONWithJSONObject(jsonData: String): MutableList<String> {
        var data = mutableListOf<String>()
        try {
            var jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                data.add(jsonArray.get(i).toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }
}

