package com.example.datetimedialogdemo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("MMMM, dd, yyyy, hh:mm a", Locale.getDefault())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.textView).setOnClickListener {
            DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
        TimePickerDialog(
            this,
            this,
            calendar.get(Calendar.HOUR),
            calendar.get(Calendar.MINUTE),
            false

        ).show()

    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, p1)
            set(Calendar.MINUTE, p2)
        }
        displayFormattedDate(calendar.timeInMillis)


    }

    private fun displayFormattedDate(timestamp: Long) {
        findViewById<TextView>(R.id.textView).text = formatter.format(timestamp)
        Log.i("Formatting", timestamp.toString())
    }

}