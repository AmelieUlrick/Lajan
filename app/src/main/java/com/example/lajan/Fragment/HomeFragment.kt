package com.example.lajan.Fragment

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_menu.*


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setSupportActionBar(my_toolbar)
        //activity!!.setSupportActionBar(my_toolbar)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}