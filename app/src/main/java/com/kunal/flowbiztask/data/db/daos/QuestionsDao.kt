package com.kunal.flowbiztask.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kunal.flowbiztask.data.network.models.Question

@Dao
interface QuestionsDao {

    @Query("SELECT * FROM " + "questions")
    fun getAllQuestions(): LiveData<List<Question>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAllQuestions(questions: List<Question>)
}