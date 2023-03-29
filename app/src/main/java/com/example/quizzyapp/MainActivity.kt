package com.example.quizzyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val nameet = findViewById<EditText>(R.id.namet)
        val startb = findViewById<Button>(R.id.startb)
        var name:String=""
        
        startb.setOnClickListener { 
            if(nameet.text.toString().isEmpty()){
                Toast.makeText(this, "Enter you name to Continue", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            name = nameet.text.toString()
            val intent = Intent(this,Questionactivity::class.java)
            startActivity(intent)
            finish()    //because we don't need to return back to current activity
        }
        
    }
}