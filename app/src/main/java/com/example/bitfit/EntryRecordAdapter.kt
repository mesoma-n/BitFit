package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit1.R

class EntryRecordAdapter (private val exerciseRecords: List<EntryRecord>)
    : RecyclerView.Adapter<EntryRecordAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mWorkoutName: TextView = mView.findViewById<View>(R.id.nameView) as TextView
        val mWorkoutCalories: TextView = mView.findViewById<View>(R.id.calorieView) as TextView
        val mWorkoutTime: TextView = mView.findViewById<View>(R.id.timeView) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exerciseRecords.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val record = exerciseRecords[position]

        holder.mWorkoutName.text = record.exerciseName
        holder.mWorkoutCalories.text = record.exerciseCalorie.toString() + " Cal."
        holder.mWorkoutTime.text = record.exerciseTime.toString() + " min."
    }
}