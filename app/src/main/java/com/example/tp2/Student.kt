package com.example.tp2

class Student(val id: Number, val firstname: String, val lastname: String, val sex: String) {
    var present = true


    override fun toString(): String {
        return "$firstname $lastname"
    }
}
