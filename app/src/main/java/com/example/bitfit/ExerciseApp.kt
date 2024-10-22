package com.example.bitfit

import android.app.Application

class ExerciseApp: Application() {
    val db by lazy {AppDatabase.getInstance(this)}
}