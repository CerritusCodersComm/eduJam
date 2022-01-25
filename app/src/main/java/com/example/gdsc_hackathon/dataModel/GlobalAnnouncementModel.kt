package com.example.gdsc_hackathon.dataModel

data class GlobalAnnouncementModel(
    var title: String?="",
    var description: String? = "",
    var date: String? = "",
    var url: String? = "",
    var pinned: Int? = 0
)
