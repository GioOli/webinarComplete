package com.example.kotlintutorial

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewFragment : Fragment() {

    //private var param1: String? = null

    //widget
    var spinner: Spinner? = null
    var nameTV: TextView? = null
    var ageTV: TextView? = null
    var name_ageTV: TextView? = null
    var button: Button? = null

    //Add variables
    var users: ArrayList<User> = ArrayList()
    var selectedUser = 0
    var dataTransport: DataTransport? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }


        //Verify and create User objects in case not created yet
        verifyAndCreateUsers()

        //retrieve viewmodel
        dataTransport = activity?.let { ViewModelProvider(it).get(DataTransport::class.java) }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ViewFragment().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Link Views
        nameTV = view.findViewById(R.id.nameContentTV)
        ageTV = view.findViewById(R.id.ageContentTV)
        name_ageTV = view.findViewById(R.id.nameAgeContentTV)
        button = view.findViewById(R.id.updateButton)

        //Set Button listener
        button?.setOnClickListener {
            //Set current User data
            dataTransport?.selectedUser = this.selectedUser
            dataTransport?.name = users[selectedUser].name
            dataTransport?.age = users[selectedUser].age
            dataTransport?.name_age = users[selectedUser].name_age

            (activity as MainActivity).switchToFragment("edit")
        }


        //To update what user is selected, configure spin
        spinner = view.findViewById(R.id.spinner2)
        spinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                //Set current selected user
                selectedUser = p2
                dataTransport?.selectedUser = p2
                //Show user Name and age of selected User
                when(users[p2].name.equals("")){
                    true -> nameTV?.setText("-")
                    else -> nameTV?.setText(users[p2].name)
                }
                when(users[p2].age.equals(-1)){
                    true -> ageTV?.setText("-")
                    else -> ageTV?.setText(users[p2].age.toString())
                }
                when(users[p2].name.equals("") || users[p2].age.equals(-1)){
                    true -> name_ageTV?.setText("-")
                    else -> name_ageTV?.setText(users[p2].name + ": "+ users[p2].age)
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        //Update Selected User
        updateAndSelectUser()


    }


    fun verifyAndCreateUsers(){

        if (users.isNullOrEmpty()){
            users.add(User())
            users.add(User())
            users.add(User())
        }
    }

    //Update selected User from dataTransport
    fun updateAndSelectUser(){

        selectedUser = dataTransport?.selectedUser!!
        users[selectedUser] = dataTransport?.users?.get(selectedUser)!!
        spinner?.setSelection(selectedUser)
    }

}