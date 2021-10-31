package com.kunal.flowbiztask.data.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONObject

@Entity(tableName = "questions")
data class Question (
    var owner: Owner,
    var is_answered:Boolean,
    var view_count:Long,
    var answer_count:Long,
    var score:Long,
    var last_activity_date:Long,
    var creation_date:Long,
    @PrimaryKey(autoGenerate = false)
    var question_id:Long,
    var content_license:String,
    var link:String,
    var title:String
){}
