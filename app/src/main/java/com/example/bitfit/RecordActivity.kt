package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bitfit1.R
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecordActivity : AppCompatActivity() {
    private lateinit var exerciseNameInputView: EditText
    private lateinit var exerciseCaloriesInputView: EditText
    private lateinit var exerciseTimeInputView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry)

        exerciseNameInputView = findViewById(R.id.name)
        exerciseCaloriesInputView = findViewById(R.id.calories)
        exerciseTimeInputView = findViewById(R.id.time)

        val saveRecordBtn = findViewById<Button>(R.id.submit)
        var exerciseRecord: EntryRecord? = null

        saveRecordBtn.setOnClickListener {
            exerciseRecord = EntryRecord(
                exerciseNameInputView.text.toString(),
                exerciseCaloriesInputView.text.toString().toInt(),
                exerciseTimeInputView.text.toString().toDouble())

            exerciseRecord?.let { obj ->
                lifecycleScope.launch(IO) {
                    (application as ExerciseApp).db.exerciseDao().insert(
                        RecordEntity(
                            exerciseName = obj.exerciseName,
                            exerciseCalorie = obj.exerciseCalorie,
                            exerciseTime = obj.exerciseTime
                        )
                    )
                    Log.v("done", "written")
                }
            }
            // launch main activity
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

    }
}