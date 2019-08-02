package com.laitec.mvvm.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laitec.mvvm.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

//        val navHost = NavHostFragment.create(R.navigation.authentication)
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.frm_container,navHost)
//            .setPrimaryNavigationFragment(navHost)
//            .commit()


    }
}
