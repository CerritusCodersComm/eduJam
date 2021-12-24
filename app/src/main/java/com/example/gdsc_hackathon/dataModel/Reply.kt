package com.example.gdsc_hackathon.dataModel

data class Reply(var questionId : String = "", var reply : String = "", var username : String = "", var date : String = "") {

    fun getQuesId(): String? {
        return questionId
    }

    fun getReplyText(): String? {
        return reply
    }


    fun getUser(): String? {
        return username
    }

    fun getCurrentDate(): String? {
        return date
    }
}
