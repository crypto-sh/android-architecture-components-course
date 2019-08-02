package com.laitec.mvvm.ui.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders



abstract class ActParent<T : BaseViewModel, E : ViewDataBinding> : AppCompatActivity(), LifecycleOwner {


    lateinit var viewModel: T

    lateinit var binding : E

    abstract fun getResourcelayoutId() : Int

    abstract fun getClassViewModel() : Class<T>

    abstract fun inject()

    abstract fun getFactory() : ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * dependency injection
         */
        inject()

        /**
         * dataBinding
         */
        binding = DataBindingUtil.setContentView(this, getResourcelayoutId())

        /**
         * viewModel
         */
        viewModel = ViewModelProviders.of(this,getFactory()).get(getClassViewModel())
        lifecycle.addObserver(viewModel)
    }
}