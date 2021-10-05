package com.example.quizapp

class Quiz(var questions: List<Question>) {
    var score = 0
    fun checkAnswer(answer: Boolean){
        //check is the answer in the param
        //matches the answer of the currect questions.json
        //if it does
        score++
        //if not
        score--
    }
}