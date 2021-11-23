package com.example.kotlintutorial

import android.util.Log
import androidx.lifecycle.ViewModel

data class DataTransport(var selectedUser: Int = 0,
                         var name: String = "",
                         var age: Int = -1,
                         var name_age: String = "") : ViewModel() {

    var users: ArrayList<User> = ArrayList()

    //Constructor
    init {
        //Verify if user array is empty, if so, populate
        if(users.isNullOrEmpty()){
            users.add(User())
            users.add(User())
            users.add(User())
        }
    }

    //function to update User
    fun updateUser(){
        users[selectedUser].name = this.name
        users[selectedUser].age = this.age
        users[selectedUser].name_age = this.name + ": " + this.age
    }


}