package com.kunal.flowbiztask.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kunal.flowbiztask.data.network.models.Owner
import com.kunal.flowbiztask.data.network.models.Question
import java.lang.reflect.Type


class DataConverter {
    @TypeConverter
    fun fromQuestionList(questions: List<Question?>?): String? {
        if (questions == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Question?>?>() {}.type
        return gson.toJson(questions, type)
    }

    @TypeConverter
    fun toQuestionList(questions: String?): List<Question>? {
        if (questions == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Question?>?>() {}.type
        return gson.fromJson<List<Question>>(questions, type)
    }

    @TypeConverter
    fun fromOwner(owner: Owner): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<Owner>() {}.type
        return gson.toJson(owner, type)
    }

    @TypeConverter
    fun toOwner(owner: String?): Owner? {
        if (owner == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Owner>() {}.type
        return gson.fromJson<Owner>(owner, type)
    }

}