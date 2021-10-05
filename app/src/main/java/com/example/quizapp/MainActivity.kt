package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // reading the json from the raw folder

        // step 1: open the raw resource as an InputStream
        // R.raw.questions --> R is the Resources class, raw is the folder,
        // questions is the file
        val inputStream = resources.openRawResource(R.raw.questions)
        // step 2: use a buffered reader on the inputstreaam to read
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
        Log.d(TAG, "onCreate: \n${questions.toString()}")

        // create Quiz object using the list of questions you just read
        // do any initial setup of the layout to show the first questions.json

        quiz = Quiz(questions)
/*
        //any quiz related actions -- scorekeeping, checking if
        // answers are right or wrong, keeping track of which questions
        // we are on, if there are more questions remaining are all duties
        // of the quiz class

        //MainActivity is in charge of the UI and passing information
        // to and from the Quiz and class
        answer1Button.setOnClickListener{
            // tell the quiz was was clicked on and let the quiz determine
            // if the answer was right or wrong
            // could use answer1Button.text to see what the answer was selected

            //
        }
        
 */
    }
}

