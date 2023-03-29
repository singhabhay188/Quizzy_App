package com.example.quizzyapp

data class Question(
    val id:Int,
    val question:String,
    val image:Int,
    val options:Array<String>,
    val correctAns:Int
)
