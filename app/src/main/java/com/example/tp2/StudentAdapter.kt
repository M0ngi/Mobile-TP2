package com.example.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class StudentAdapter (private val studentsList: ArrayList<Student> ): RecyclerView.Adapter<StudentAdapter.MyViewHolder>(), Filterable{

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleImage: ImageView =itemView.findViewById(R.id.titleImage)
        val firstname: TextView =itemView.findViewById(R.id.firstname)
        val lastname:TextView=itemView.findViewById(R.id.lastname)
    }

    var dataFilterList = ArrayList<Student>()
        init {
            dataFilterList = studentsList
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= studentsList[position]
        if(currentItem.sex=="F"){
            holder.titleImage.setImageResource( R.drawable._83779090_403772071625799_4803073113472143732_n)
        }else{
            holder.titleImage.setImageResource( R.drawable._83925710_403772214959118_63605485773657857_n)
        }

        holder.firstname.text=currentItem.firstname
        holder.lastname.text=currentItem.lastname
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = studentsList
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in studentsList) {
                        if (student.toString().lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }
        }
    }
}