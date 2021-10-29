package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var quiz: Quiz
    lateinit var trueValueButton: Button
    lateinit var falseValueButton: Button
    lateinit var questionAsked: TextView
    private var answer = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgits()

        // reading the json from the raw folder

        // step 1: open the raw resource as an InputStream
        // R.raw.questions --> R is the Resources class, raw is the folder,
        // questions is the file
        val inputStream = resources.openRawResource(R.raw.questions)
        // step 2: use a buffered reader on the inputstream to read
        // the text into a string
        val jsonText = inputStream.bufferedReader().use{
            // the last line of the use function is returned
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonText")

        // use gson to convert the jsonText into a List<Question>
        // https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
        // check the section called "Parsing between a Collection, List, or Array"
        // log the list of questions and see if your Question objects all appear correct

        val gson = Gson()
        // gson needs to know what kind of list you are converting
        val type = object : TypeToken<List<Question>>(){}.type
        val questions = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n$questions")

        // create Quiz object using the list of questions you just read
        // do any initial setup of the layout to show the first questions.json

        quiz = Quiz(questions)
        questionAsked.text = quiz.questions.elementAt(quiz.totalQuestionsAsked).question
        buttonTextView()
        trueValueButton.setOnClickListener {
            quiz.resultOfAnswer(true)
            if(quiz.hasMoreQuestions()){
                questionAsked.text = quiz.setUpNextQuestion()
                buttonTextView()
            } else {
                trueValueButton.visibility = View.GONE
                falseValueButton.visibility = View.GONE
            }
        }
        falseValueButton.setOnClickListener {
            quiz.resultOfAnswer(false)
            quiz.setUpNextQuestion()
            if(quiz.hasMoreQuestions()){
                questionAsked.text = quiz.setUpNextQuestion()
                buttonTextView()
            } else {
                //trueValueButton.visibility = View.GONE
                //falseValueButton.visibility = View.GONE
            }
        }
    }

    private fun buttonTextView() {
        trueValueButton.text = quiz.questions.elementAt(quiz.totalQuestionsAsked).button1
        falseValueButton.text = quiz.questions.elementAt(quiz.totalQuestionsAsked).button2
        trueValueButton.visibility = View.VISIBLE
        falseValueButton.visibility = View.VISIBLE
    }

    private fun wireWidgits() {
        trueValueButton = findViewById(R.id.button_main_trueValButton)
        falseValueButton = findViewById(R.id.button_main_falseValButton)
        questionAsked = findViewById(R.id.textView_main_questionAsked)
    }
}

