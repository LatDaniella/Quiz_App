package com.example.quizapp

class Quiz(var questions: List<Question>) {
    var numOfQuestions = questions.size
    var totalQuestionsAsked = 0
    var totalPoints = 0

    fun hasMoreQuestions(): Boolean {
        return numOfQuestions > totalQuestionsAsked
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
            in Int.MIN_VALUE..719 -> "You should join football"
            in 720..879 -> "You should join golf"
            in 880..899 -> "You should join tennis"
            in 900..979 -> "You should join volleyball"
            in 980..999 -> "You should join basketball"
            in 1000..1019 -> "You should join baseball or softball"
            in 1020..1299 -> "You should join cross country"
            in 1300..1599 -> "You should join soccer"
            in 1600..1859 -> "You should join track"
            in 1860..Int.MAX_VALUE -> "You should join swimming"
            else -> {
                ""
            }
        }
    }
}
