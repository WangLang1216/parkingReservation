package com.example.parkingspace


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var etp_account: EditText
    private lateinit var etp_password: EditText
    private lateinit var btn_login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etp_account = findViewById(R.id.etp_account)
        etp_password = findViewById(R.id.etp_password)
        btn_login = findViewById(R.id.btn_login)

        // 点击登录
        btn_login.setOnClickListener {
            getLogin(etp_account.text.toString(), etp_password.text.toString())
        }

    }

    // JSON格式转换
    private fun parseJSONWithJSONObject(jsonData: String): String {
        var code = "0"
        try {
            //根据获取的数据类型，来创建对应的数据结构进行数据保存
            var jsonObject = JSONObject(jsonData)
            code = jsonObject.getString("code")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return code
    }

    // 登录
    private fun getLogin(account: String, password: String) {
        var url = "http://10.0.2.2:8080/user/queryUser"
        var okHttpclient: OkHttpClient = OkHttpClient.Builder().build()
        val requestBody = FormBody.Builder()
            .add("account", account)
            .add("password", password)
            .build()

        //创建request请求对象
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            var code = "0"
            var response = okHttpclient.newCall(request).execute()
            if (response.isSuccessful) {
                var responseData = response.body?.string()
                if (responseData != null) {
                    code = parseJSONWithJSONObject(responseData)
                }
                GlobalScope.launch(Dispatchers.Main) {
                    //Dispatchers.Main切换到UI线程
                    if (code == "1") {
                        var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "账号或密码错误！", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}