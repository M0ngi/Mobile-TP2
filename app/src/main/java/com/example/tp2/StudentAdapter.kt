package com.example.tp2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class StudentAdapter (private val studentsList: ArrayList<Student> ): RecyclerView.Adapter<StudentAdapter.MyViewHolder>(), Filterable{

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleImage: ImageView = itemView.findViewById(R.id.titleImage)
        val firstname: TextView = itemView.findViewById(R.id.firstname)
        val lastname: TextView = itemView.findViewById(R.id.lastname)
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val presenceSwitch: Switch = itemView.findViewById(R.id.presenceSwitch)
    }

    var dataFilterList = ArrayList<Student>()
        init {
            // We conserve the original list for filtering
            dataFilterList.addAll(studentsList)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataFilterList[position]
        if(currentItem.sex=="F"){
            holder.titleImage.setImageResource( R.drawable._83779090_403772071625799_4803073113472143732_n)
        }else{
            holder.titleImage.setImageResource( R.drawable._83925710_403772214959118_63605485773657857_n)
        }

        holder.firstname.text=currentItem.firstname
        holder.lastname.text=currentItem.lastname
        holder.presenceSwitch.setOnCheckedChangeListener { _, isChecked ->
            StudentRepository.setStudentPresence(currentItem.id, isChecked)
        }
        holder.presenceSwitch.isChecked = currentItem.present
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val dataFilterList = if(charSearch.isEmpty()){
                    studentsList.toList()
                } else{
                    studentsList.filter { student -> student.toString().lowercase(Locale.ROOT)
                        .contains(charSearch.lowercase(Locale.ROOT))
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                val data = results?.values as List<Student>
                dataFilterList.clear()
                dataFilterList.addAll(data)

                notifyDataSetChanged()
            }
        }
    }
}