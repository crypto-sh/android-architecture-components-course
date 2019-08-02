package com.laitec.mvvm

import android.content.Context
import android.content.Intent
import android.widget.Button
import androidx.test.core.app.ApplicationProvider
import com.laitec.mvvm.ui.activity.MainActivity
import com.laitec.mvvm.ui.activity.SplashActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows


@RunWith(RobolectricTestRunner::class)
class SplashActivityTestRobo {

    lateinit var activity : SplashActivity

    @Before
    fun load(){
        activity = Robolectric.buildActivity(SplashActivity::class.java).create().get()
    }

    @Test
    fun runMainActivity(){
        val login = activity.findViewById<Button>(R.id.buttonLogin)

        login.performClick()

        val expectedIntent      = Intent(activity,MainActivity::class.java)

        val actualIntent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity

        assertEquals(expectedIntent::class,actualIntent::class)
    }

}