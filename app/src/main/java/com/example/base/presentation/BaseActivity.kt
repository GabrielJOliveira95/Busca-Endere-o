package com.example.base.presentation

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    fun showToastLong(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun showToastShort(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}