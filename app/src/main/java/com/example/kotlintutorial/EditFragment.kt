package com.example.kotlintutorial

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {

    //Add widgets
    var nameET: EditText? = null
    var ageET: EditText? = null
    var nameageTV2: TextView? = null
    var button: Button? = null

    //variables
    var dataTransport: DataTransport? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //retrieve viewmodel
        dataTransport = activity?.let { ViewModelProvider(it).get(DataTransport::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //link TextViews
        nameET = view.findViewById(R.id.nameET)
        ageET = view.findViewById(R.id.ageET)
        nameageTV2 = view.findViewById(R.id.nameageTV2)
        button = view.findViewById(R.id.button)

        //Set saved values
        nameET?.setText(dataTransport?.name)      //NAO ESTA A DAR UPDATE?????
        when(dataTransport?.age != -1) {
            true -> ageET?.setText(dataTransport?.age.toString())
            false -> ageET?.setText("")
        }
        nameageTV2?.setText(dataTransport?.name_age)


        //Setup Button
        button?.setOnClickListener {

            var userId = dataTransport!!.selectedUser
            //update dataTransport data
            dataTransport?.name = nameET?.text.toString()
            dataTransport?.users?.get(userId)?.name = nameET?.text.toString()

            when(ageET?.text.toString().isEmpty()){
                true -> {
                    dataTransport?.age = -1
                    dataTransport?.users?.get(userId)?.age = -1
                }
                false -> {
                    dataTransport?.age = ageET?.text.toString().toInt()
                    dataTransport?.users?.get(userId)?.age = ageET?.text.toString().toInt()
                }
            }

            when(dataTransport?.age == -1 || dataTransport?.name.equals("")){
                true -> {
                    dataTransport?.name_age = ""
                    dataTransport?.users?.get(userId)?.name_age = ""
                }
                false -> {
                    dataTransport?.name_age = dataTransport?.name + ": " + dataTransport?.age
                    dataTransport?.users?.get(userId)?.name_age = dataTransport?.name + ": " + dataTransport?.age
                }
            }
            //Alterar view
            (activity as MainActivity).switchToFragment("view")
        }



    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         */
        @JvmStatic
        fun newInstance() =
            EditFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}