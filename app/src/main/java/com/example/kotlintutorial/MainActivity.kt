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
    var viewFrag: Fragment? = null
    var editFrag: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewFrag = ViewFragment.newInstance()

        //Start with viewFragment
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, viewFrag!!)
            .commit()


    }

    //Function to switch to specific fragment
    fun switchToFragment(frag: String){

        //switch statement using (when)
        when(frag){
            "view" ->{
                viewFrag = ViewFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, viewFrag!!)
                    .commit()
            }
            "edit" ->{
                editFrag = EditFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, editFrag!!)
                    .commit()
            }
            else ->{
                Log.d(null, "Nonexistent Fragment")
            }
        }
    }



}