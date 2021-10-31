package com.kunal.flowbiztask.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kunal.flowbiztask.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handler = Handler()
        val r = Runnable {
            startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
            finish()
        }
        handler.postDelayed(r,3000)
    }
}