package com.example.quizapp

class Quiz(var questions: List<Question>) {
    var numOfQuestions = questions.size
    var totalQuestionsAsked = 0
    var totalPoints = 0

    fun hasMoreQuestions(): Boolean {
        return numOfQuestions < totalQuestionsAsked
    }

    fun setUpNextQuestion(): String {
        return if (hasMoreQuestions()) {
            questions.elementAt(totalQuestionsAsked).question
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
        totalQuestionsAsked++
        hasMoreQuestions()
    }

    fun getResult(): String {
        return when(totalPoints) {
            in Int.MIN_VALUE..719 -> "football"
            in 720..879 -> "golf"
            in 880..899 -> "tennis"
            in 900..979 -> "volleyball"
            in 980..999 -> "basketball"
            in 1000..1019 -> "baseball or softball"
            in 1020..1089 -> "cross country"
            in 1090..1259 -> "soccer"
            in 1260..1859 -> "track"
            in 1860..Int.MAX_VALUE -> "swimming"
            else -> {
                ""
            }
        }
    }
}
