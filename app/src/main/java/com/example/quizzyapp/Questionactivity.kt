package com.example.quizzyapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.quizzyapp.Constant2.CORRECT_ANS
import com.example.quizzyapp.Constant2.TOTAL_QUESTION

class Questionactivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var imageid:ImageView
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
    private var size=0

    val options = mutableListOf<TextView>()

    var answerSubmitted = false //used to track whether answer submitted or not

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionactivity)

        imageid = findViewById(R.id.imageid)
        submitbutton = findViewById(R.id.submitb)
        option1 = findViewById(R.id.option1id)
        option2 = findViewById(R.id.option2id)
        option3 = findViewById(R.id.option3id)
        option4 = findViewById(R.id.option4id)
        progressBar = findViewById(R.id.progressBar)
        progressBarText = findViewById(R.id.progressBartext)
        questiontext = findViewById(R.id.questiontext)

        options.add(option1);    options.add(option2);    options.add(option3);    options.add(option4)

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        submitbutton.setOnClickListener(this)

        questionList=Constant2.getQuestions()
        size=questionList.size
        progressBar.max=size
        score=0

        //resetting position, score and cselected option back
        qposition = 0
        score=0
        cselectedOption=-1

        //initially call to create questions
        createQuestions()

    }

    private fun createQuestions(){
        val cquestion=questionList.get(qposition)
        progressBar.progress = qposition+1
        progressBarText.text = "${qposition+1}/$size"

        questiontext.text=cquestion.question
        imageid.setImageResource(cquestion.image)
        option1.text= cquestion.options[0]
        option2.text= cquestion.options[1]
        option3.text= cquestion.options[2]
        option4.text= cquestion.options[3]

        //initially nothing is selected,unselect all and set answer to -1,no answer submitted and button text back to submit
        defaultOptionSelected()
        cselectedOption=-1
        answerSubmitted=false
        submitbutton.text="Submit"
    }

    //we need to create function that set all the options back to default and only highlight the selected option
    private fun defaultOptionSelected(){
        for(i in options){
            i.setTextColor(Color.GRAY)
            i.typeface=Typeface.DEFAULT
            i.background=ContextCompat.getDrawable(this,R.drawable.border_text)
        }
    }
    //highlight = change the background + make current text bold and black
    private fun onOptionSelected(){
        //first set all to default because previously there might be a option selected
        defaultOptionSelected()

        //noe highlight the current selected option
        val selectedOption:TextView = options[cselectedOption-1]
        selectedOption.typeface = Typeface.DEFAULT_BOLD
        selectedOption.setTextColor(Color.BLACK)
        selectedOption.background = ContextCompat.getDrawable(this,R.drawable.border_text_selected)
    }

    private fun onSubmitButton(){
        //if no option is selected show toast to ask user to select an option
        if (cselectedOption == -1) {
            Toast.makeText(this, "Select an option to continue", Toast.LENGTH_SHORT).show()
            return
        }
        //if an option is selcted,find the correct option
        val correctans = questionList[qposition].correctAns

        //color it green
        var selectedOption: TextView = options[correctans - 1]
        selectedOption.background =
            ContextCompat.getDrawable(this, R.drawable.correct_text_selected)

        //if selected option is not the correct ans color the wrong ans as red
        if (cselectedOption != correctans) {
            selectedOption = options[cselectedOption - 1]
            selectedOption.background =
                ContextCompat.getDrawable(this, R.drawable.incorrect_text_selected)
        }
        else    score++

        //after that change the button text to next question or finish and mark answer as submitted
        if(qposition==size-1) submitbutton.text = "Finish"
        else                               submitbutton.text = "Next Question"
        answerSubmitted=true
    }

    override fun onClick(p0: View?){
        when(p0?.id){
            R.id.option1id -> {
                if(!answerSubmitted) {
                    cselectedOption = 1
                    onOptionSelected()
                }
            }
            R.id.option2id -> {
                if(!answerSubmitted) {
                    cselectedOption = 2
                    onOptionSelected()
                }
            }
            R.id.option3id -> {
                if(!answerSubmitted) {
                    cselectedOption = 3
                    onOptionSelected()
                }
            }
            R.id.option4id -> {
                if(!answerSubmitted) {
                    cselectedOption = 4
                    onOptionSelected()
                }
            }
            R.id.submitb -> {
                //if answer not submitted
                if(!answerSubmitted){
                    onSubmitButton()
                    return
                }
                //if answer submitted change the question or end the game
                    //changing the question
                if(qposition<size-1){
                    qposition++
                    createQuestions()
                }
                    //ending the game
                else{
                    val player_name = intent.getStringExtra(Constant2.PLAYER_NAME)
                    val intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra(Constant2.PLAYER_NAME,player_name)
                    intent.putExtra(Constant2.TOTAL_QUESTION,questionList.size)
                    intent.putExtra(Constant2.CORRECT_ANS,score)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}