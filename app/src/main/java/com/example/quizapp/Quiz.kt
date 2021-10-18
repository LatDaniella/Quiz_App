package com.example.quizapp

class Quiz(var questions: List<Question>) {
    var numOfQuestions = questions.size
    var totalQuestionsAsked = 0
    var totalPoints = 0

    fun hasMoreQuestions(): Boolean {
        return numOfQuestions < totalQuestionsAsked
    }

    fun setUpNextQuestion(): String {
        if(hasMoreQuestions()) {
            return questions.elementAt(totalQuestionsAsked).question
        } else {
            getResult()
        }
    }

    fun resultOfAnswer(choice : Boolean) {
        totalPoints += if (choice){
            questions.elementAt(totalQuestionsAsked).trueValue
        } else {
            questions.elementAt(totalQuestionsAsked).falseValue
        }
        hasMoreQuestions()
    }

    fun getResult(): String {
        return when(totalPoints) {
            in Int.MIN_VALUE..719 -> "football"
            in 720..879 -> "golf"
            in 880..899 -> "tennis"
            in 900..979 -> "volleyball"
            in 980..879 -> "basketball"
            in 1000..999 -> "baseball or softball"
            in 1020..1019 -> "cross country"
            in 1090..1089 -> "soccer"
            in 1260..879 -> "track"
            in 1860..Int.MAX_VALUE -> "swimming"



        }
        /*
        if (totalPoints <= 560 || totalPoints < 720){
            return "Football"
        } else if (totalPoints <= 720 || totalPoints < 880)

         */
    }

    //hasMoreQuestions
    //setUpNextQuestion
    //getResult

    fun checkAnswer(answer: Boolean){
        //check is the answer in the param
        //matches the answer of the currect questions.json
        //if it does
        //if(answer == questions[currIndex].answer)
        //  score++
        //if not
        //  score--

        /*

         */
    }
}
