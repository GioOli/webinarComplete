package com.example.kotlintutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import java.util.logging.Logger
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    //Define Variables
    val viewFrag: Fragment = ViewFragment.newInstance()
    val editFrag: Fragment = EditFragment.newInstance()
    var fragContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Start with viewFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, viewFrag)
            .commit()


    }

    //Function to switch to specific fragment
    fun switchToFragment(frag: String){

        //switch statement using (when)
        when(frag){
            "view" ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, viewFrag)
                    .commit()
            }
            "edit" ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, editFrag)
                    .commit()
            }
            else ->{
                Log.d(null, "Nonexistent Fragment")
            }
        }
    }



}