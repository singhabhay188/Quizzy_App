package com.example.quizzyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultname = findViewById<TextView>(R.id.resultnameid)
        val resultscore = findViewById<TextView>(R.id.resultscoreid)
        val finButton = findViewById<Button>(R.id.finButton)

        val name = intent.getStringExtra(Constant2.PLAYER_NAME)
        val cans = intent.getIntExtra(Constant2.CORRECT_ANS,0)
        val tques = intent.getIntExtra(Constant2.TOTAL_QUESTION,0)

        resultname.text = name
        resultscore.text="Your score is ${cans} out of ${tques}"

        finButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}