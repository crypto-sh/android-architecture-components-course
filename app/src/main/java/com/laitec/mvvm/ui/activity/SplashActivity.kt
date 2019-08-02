package com.laitec.mvvm.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.laitec.mvvm.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }



    }
}
