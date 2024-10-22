package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit1.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var exerciseRecordsRecyclerView: RecyclerView
    private val exerciseRecords = mutableListOf<EntryRecord>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        exerciseRecordsRecyclerView = findViewById(R.id.exerciseRv)
        val recordsAdapter = EntryRecordAdapter(exerciseRecords)

        lifecycleScope.launch {
            (application as ExerciseApp).db.exerciseDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    EntryRecord(
                        entity.exerciseName,
                        entity.exerciseCalorie,
                        entity.exerciseTime
                    )
                }.also { mappedList ->
                    exerciseRecords.clear()
                    exerciseRecords.addAll(mappedList)
                    recordsAdapter.notifyDataSetChanged()
                    Log.v("done", "added")
                    Log.v("test", exerciseRecords.toString())
                }
            }
        }

        exerciseRecordsRecyclerView.adapter = recordsAdapter
        exerciseRecordsRecyclerView.layoutManager = LinearLayoutManager(this)

        val addSessionBtn = findViewById<Button>(R.id.button)

        addSessionBtn.setOnClickListener {
            // launch the detail activity
            val intent = Intent(this, RecordActivity::class.java)
            this.startActivity(intent)
        }
    }
}