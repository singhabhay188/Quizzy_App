package com.example.quizzyapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class Questionactivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var Imageid:ImageView
    private lateinit var submitbutton:Button
    private lateinit var option1:TextView
    private lateinit var option2:TextView
    private lateinit var option3:TextView
    private lateinit var option4:TextView
    private lateinit var progressBar:ProgressBar
    private lateinit var progressBarText:TextView
    private lateinit var questiontext:TextView

    private var cselectedOption=1
    private var score=0
    private var qposition=0
    private lateinit var questionList: List<Question>

    val options = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionactivity)

        Imageid = findViewById(R.id.imageid)
        submitbutton = findViewById(R.id.submitb)
        option1 = findViewById(R.id.option1id)
        option2 = findViewById(R.id.option2id)
        option3 = findViewById(R.id.option3id)
        option4 = findViewById(R.id.option4id)
        progressBar = findViewById(R.id.progressBar)
        progressBarText = findViewById(R.id.progressBartext)
        questiontext = findViewById(R.id.questiontext)

        options.add(option1);    options.add(option2);    options.add(option3);    options.add(option4);

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)

        questionList=Constant2.getQuestions()
        progressBar.max=questionList.size

        //resetting position, score and cselected option back
        qposition = 0
        score=0
        cselectedOption=1

        //initially call to create questions
        createQuestions(qposition,questionList.get(qposition),questionList.size)

        //keep on changing when submit button is clicked
        submitbutton.setOnClickListener {

            onSubmitButton()

            /*
            qposition++;
            if(qposition==size-1)    submitbutton.text="Finish"      //changed the button to finish from next question in last question
            if(qposition==size){
                Toast.makeText(this, "You Won the Game", Toast.LENGTH_SHORT).show()
            }
            else{
                createQuestions(qposition,questionList.get(qposition),size)
            }
            */
        }
    }

    fun createQuestions(position:Int,cquestion: Question,size:Int){
        progressBar.progress = position+1
        progressBarText.text = "${position+1}/$size"

        questiontext.text=cquestion.question
        Imageid.setImageResource(cquestion.image)
        option1.text= cquestion.options[0]
        option2.text= cquestion.options[1]
        option3.text= cquestion.options[2]
        option4.text= cquestion.options[3]

        //set the selected option to cselectedOption
        onOptionSelected()
    }

    //we need to create function that set all the options back to default and only highlight the selected option
    //highlight = change the background + make current text dark
    private fun defaultOptionSelected(){
        for(i in options){
            //to change color i.setTextColor(Color.RED)
            i.typeface=Typeface.DEFAULT
            i.background=ContextCompat.getDrawable(this,R.drawable.border_text)
        }
    }

    private fun onOptionSelected(){
        //first set all to default because previously there might be some selected
        Log.i("Question Activity","Option is selected at $cselectedOption")
        defaultOptionSelected()

        //noe highlight the current selected option
        var selectedOption:TextView = options.get(cselectedOption-1)
        selectedOption.typeface = Typeface.DEFAULT_BOLD
        selectedOption.setTextColor(Color.BLACK)
        selectedOption.background = ContextCompat.getDrawable(this,R.drawable.border_text_selected)
    }

    private fun onSubmitButton(){
        //first find the correct ans
        val correctans = questionList.get(qposition).correctAns

        //color it green
        var selectedOption:TextView = options.get(correctans-1)
        selectedOption.background = ContextCompat.getDrawable(this,R.drawable.correct_text_selected)

        //if selected option is not the correct ans color the wrong ans as red
        if(cselectedOption!=correctans){
            selectedOption = options.get(cselectedOption-1)
            selectedOption.background = ContextCompat.getDrawable(this,R.drawable.incorrect_text_selected)
        }
    }

    override fun onClick(p0: View?){
        when(p0?.id){
            R.id.option1id -> {
                cselectedOption=1
                onOptionSelected()
            }
            R.id.option2id -> {
                cselectedOption=2
                onOptionSelected()
            }
            R.id.option3id -> {
                cselectedOption=3
                onOptionSelected()
            }
            R.id.option4id -> {
                cselectedOption=4
                onOptionSelected()
            }
        }
    }
}