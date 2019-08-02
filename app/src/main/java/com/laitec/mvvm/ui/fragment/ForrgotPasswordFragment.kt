package com.laitec.mvvm.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.laitec.mvvm.R



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ForrgotPasswordFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ForrgotPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ForrgotPasswordFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forrgot_password, container, false)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ForrgotPasswordFragment.
         */
        @JvmStatic
        fun newInstance() = ForrgotPasswordFragment()
    }
}
