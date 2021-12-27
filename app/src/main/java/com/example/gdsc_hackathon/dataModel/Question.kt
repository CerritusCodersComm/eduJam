package com.example.gdsc_hackathon.dataModel

data class Question(var question: String = "", var username: String = "", var currentDate: String = "") {

    fun setQuestionId(questionId: String?) {
        this.question = question
    }

    fun getQuestionId(): String? {
        return question
    }

    fun getQuestionText(): String? {
        return question
    }


    fun getUser(): String? {
        return username
    }

    fun getDate(): String? {
        return currentDate
    }
}