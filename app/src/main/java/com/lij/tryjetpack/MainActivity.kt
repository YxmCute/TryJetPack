package com.lij.tryjetpack

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.lij.tryjetpack.lifecycle.LocationHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var mLocationHelper: LocationHelper<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLocationHelper = LocationHelper(this, this.lifecycle, { isSuccess, location ->
            if (isSuccess) {
                location
            }
        }, {})
        lifecycle.addObserver(mLocationHelper)
        mLocationHelper.enable()
        mLocationHelper.setListener({}, {})
        val cc = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nv, cc)
        val job = GlobalScope.launch {
            Log.i("cc", "launch start " + System.currentTimeMillis().toString())

            val f = f1()
            Log.i("cc", "f1执行ok" + System.currentTimeMillis().toString())
            val deferred = GlobalScope.async {
                delay(2000)
                return@async "你好阿"

            }
            Log.i("cc", "await 前" + System.currentTimeMillis().toString())
            val resulu = deferred.await()
            Log.i("cc", "result$resulu" + System.currentTimeMillis().toString())


        }
        Log.i("cc", "start" + System.currentTimeMillis().toString())



        suspend fun getToken(): String {
            delay(1000)
            Log.d("AA", "getToken 开始执行，时间:  ${System.currentTimeMillis()}")
            return "ask"
        }

        suspend fun getResponse(token: String): String {
            delay(3000)
            Log.d("AA", "getResponse 开始执行，时间:  ${System.currentTimeMillis()}")
            return "response"
        }

        fun setText(response: String) {
            Log.d("AA", "setText 执行，时间:  ${System.currentTimeMillis()}")
        }

// 运行代码
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("AA", "协程测试 开始执行，线程：${Thread.currentThread().name}")
            Log.d("AA", "协程 开始执行，时间:  ${System.currentTimeMillis()}")
            val token = getToken()
            val response = getResponse(token)
            setText(response)
        }
        Log.d("AA", "主线程协程后面代码执行，线程：${Thread.currentThread().name}")


        /*
        //https://stackoverflow.com/questions/50577356/android-jetpack-navigation-bottomnavigationview-with-youtube-or-instagram-like
        val cc = findNavController(R.id.nav_host_fragment)
         NavigationUI.setupWithNavController(bottom_nv, cc)*/


    }

    suspend fun f1(): String {
        delay(3000)
        return "3s"
    }

}









