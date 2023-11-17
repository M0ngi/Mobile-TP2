package com.example.tp2

object StudentRepository{
    private val courStudentList: ArrayList<Student> = arrayListOf(
        Student(1, "name1","cour","F"),
        Student(2, "name2","cour ","F"),
        Student(3, "name3","cour","M"),
        Student(4, "name4","cour","M"),
        Student(5, "name5","cour","F"),
        Student(6, "name6","cour","F"),
        Student(7, "name7","cour","F"),
    )

    private val tpStudentList: ArrayList<Student> = arrayListOf(
        Student(8, "name1","tp","F"),
        Student(9, "name6","tp","F"),
        Student(10, "name2","tp ","F"),
        Student(11, "name4","tp","M"),
        Student(12, "name5","tp","F"),
        Student(13, "name3","tp","M"),
        Student(14, "name7","tp","F"),
    )

    fun getTpStudents(): ArrayList<Student>{
        return this.tpStudentList
    }

    fun getCourStudents(): ArrayList<Student>{
        return this.courStudentList
    }

    fun findById(id: Number): Student?{
        val res = this.tpStudentList.find { student -> student.id == id }
        if(res != null) return res
        return this.courStudentList.find { student -> student.id == id }
    }

    fun setStudentPresence(id: Number, isChecked: Boolean){
        val student = this.findById(id)
        student?.present = isChecked
    }
}