package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val spinner: Spinner by lazy { findViewById(R.id.spinner) }
    val newRecyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }
    val autoComp: AutoCompleteTextView by lazy{ findViewById(R.id.autoCompleteTextView) }

    private lateinit var courStudentList: ArrayList<Student>
    private lateinit var tpStudentList: ArrayList<Student>

    val seance = listOf<String>("Cours", "TP")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, seance)

        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.hasFixedSize()

        courStudentList = StudentRepository.getCourStudents()
        tpStudentList = StudentRepository.getTpStudents()

        val adapter = StudentAdapter(courStudentList)
        newRecyclerView.adapter = adapter

        val autoCompAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,courStudentList)
        autoComp.setAdapter(autoCompAdapter)
        autoComp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val userInput = s.toString().trim()
                val currentAdapter = newRecyclerView.adapter as StudentAdapter
                currentAdapter.filter.filter(userInput)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val spinnerChoice=seance[p2]

                Toast.makeText(this@MainActivity,spinnerChoice,Toast.LENGTH_SHORT).show()
                if (spinnerChoice=="Cours"){
                    val adapterCours = StudentAdapter(courStudentList)
                    newRecyclerView.adapter = adapterCours
                }
                else {
                    val adapterTp = StudentAdapter(tpStudentList)
                    newRecyclerView.adapter = adapterTp
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}