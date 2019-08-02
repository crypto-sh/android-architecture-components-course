package com.laitec.mvvm


import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.laitec.mvvm.ui.activity.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController


/**
 * Example local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    lateinit var activity : ActivityController<MainActivity>

    @Before
    fun load(){
        activity = Robolectric.buildActivity(MainActivity::class.java).create()
    }
    @Test
    fun tesLoading(){
        val act     = activity.start().get()
        val progress= act.findViewById<ProgressBar>(R.id.prg)
        assertNotNull(progress)
        assertEquals(progress.visibility,View.VISIBLE)
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}