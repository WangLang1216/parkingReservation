package com.example.parkingspace

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okio.IOException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var img_log: ImageView
    private lateinit var btn_send: Button
    private lateinit var txt_email: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img_log = findViewById(R.id.img_log)
        btn_send = findViewById(R.id.btn_send)
        txt_email= findViewById(R.id.txt_email)

        var url = "http://10.0.2.2:8080/user/sendEmail"

//        // okhttp get 请求添加参数
//        val urlBuilder = url.toHttpUrlOrNull()?.newBuilder()
//            ?.apply {
//                addQueryParameter("email","2605735186@qq.com")
//            }
//
//        var okHttpclient: OkHttpClient = OkHttpClient.Builder().build()
//        var request = Request.Builder()
//            .url(urlBuilder!!.build())
//            .build()//get请求
//
//
//
//        //使用协程访问网络
//        var job = GlobalScope.launch(Dispatchers.IO) {
//            //Dispatchers.IO代表的是网络访问的子线程
//            delay(2000)
//            var response = okHttpclient.newCall(request).execute()
//            if (response.isSuccessful) {
//                var bytes = response.body!!.bytes()//!!不允许body为空
//                var bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//                GlobalScope.launch(Dispatchers.Main) {
//                    //Dispatchers.Main切换到UI线程
//                    img_log.setImageBitmap(bitmap)
//                }
//            }
//        }
//
//        btn_send.setOnClickListener {
//            try {
//                job.cancel()
//            } catch (e: Exception) {
//                e.message?.let { it1 -> Log.i("MainActivity", it1) }
//            }
//        }

        btn_send.setOnClickListener {
            getAsync(url)
        }
    }

    /**
     * get 只带参数
     * @param hashMap 参数列表
     */
    fun getAsync(url: String) {
        var okHttpclient: OkHttpClient = OkHttpClient.Builder().build()
        // okhttp get 请求添加参数
        val urlBuilder = url.toHttpUrlOrNull()?.newBuilder()
            ?.apply {
                addQueryParameter("email","2605735186@qq.com")
            }

        val request = Request.Builder()
            .url(urlBuilder!!.build())
            .build()

        GlobalScope.launch(Dispatchers.IO) {
//            delay(2000)
            var response = okHttpclient.newCall(request).execute()
            print("request = " + request)
            if (response.isSuccessful) {
                var bytes = response.body!!.toString()//!!不允许body为空
//                var bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                GlobalScope.launch(Dispatchers.Main) {
                    //Dispatchers.Main切换到UI线程

                    txt_email.setText(bytes)
                }
            }
        }
    }

}