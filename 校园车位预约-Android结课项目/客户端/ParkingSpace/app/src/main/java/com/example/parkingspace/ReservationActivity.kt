package com.example.parkingspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

class ReservationActivity : AppCompatActivity() {
    private lateinit var txt_space: TextView
    private lateinit var txt_time: TextView
    private lateinit var btn_connect: Button
    private lateinit var btn_cancellation: Button

    private var handler = MyHander()
    private var time = 900
    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        txt_space = findViewById(R.id.txt_space)
        txt_time = findViewById(R.id.txt_time)
        btn_connect = findViewById(R.id.btn_connect)
        btn_cancellation = findViewById(R.id.btn_cancellation)

        // 接收传过来的值
        txt_space.text = intent.getStringExtra("place")

        // 倒计时
        Thread{
            handler.sendEmptyMessage(1*101)
        }.start()

        // 预约完成
        btn_connect.setOnClickListener {
            flag = 1
            txt_space.setText("车位完成！")
            Toast.makeText(this@ReservationActivity, "成功！", Toast.LENGTH_LONG).show()
        }

        // 取消预约
        btn_cancellation.setOnClickListener {
            updateSpace(txt_space.text.toString(), "0")
            var intent = Intent(this@ReservationActivity, HomeActivity::class.java)
            startActivity(intent)
        }


    }

    // 更新车位
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
        }
    }

    // 线程之间的通信
    inner class MyHander : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1*101) {
                if (time - 1 > 0 && flag == 0) {
                    txt_time.setText((--time).toString() + "s")
                    sendEmptyMessageDelayed(1*101, 1000)
                }
                // 超时
                else if (time <= 0 && flag == 0) {
                    updateSpace(txt_space.text.toString(), "0")
                    var intent = Intent(this@ReservationActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
                else if (flag == 1) txt_time.setText(time.toString() + "s")
            }
        }
    }
}