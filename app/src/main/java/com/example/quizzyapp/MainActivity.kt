package com.example.quizzyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val nameet = findViewById<EditText>(R.id.namet)
        val startb = findViewById<Button>(R.id.startb)
        val drop_menu = findViewById<Spinner>(R.id.drop_menu)
        var name:String=""
        var selectedTopic = 0


        drop_menu.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(Adapter: AdapterView<*>?, view: View?, index: Int, id: Long) {
                selectedTopic = index
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        
        startb.setOnClickListener {
            Log.i("MainActivity","Selected option is $selectedTopic")
            name = nameet.text.toString()
            if(name.isEmpty()){
                Toast.makeText(this, "Enter you name to Continue", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this,Questionactivity::class.java)
            intent.putExtra(PLAYER_NAME,name)
            intent.putExtra(SELECTED_TOPIC,selectedTopic)
            startActivity(intent)
            finish()    //because we don't need to return back to current activity
        }
        
    }
}