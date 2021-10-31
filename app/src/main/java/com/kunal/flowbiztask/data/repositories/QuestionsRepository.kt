package com.kunal.flowbiztask.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kunal.flowbiztask.data.db.AppDatabase
import com.kunal.flowbiztask.data.network.MyApi
import com.kunal.flowbiztask.data.network.SafeApiRequest
import com.kunal.flowbiztask.data.network.models.Question
import com.kunal.flowbiztask.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionsRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    private val questions = MutableLiveData<List<Question>>()

    init {
        questions.observeForever {
            saveQuestionsToDB(it)
        }
    }

    suspend fun getQuestions(): LiveData<List<Question>> {
        return withContext(Dispatchers.IO){
            fetchQuestions()
            db.getQuestionDao().getAllQuestions()
        }
    }

    private suspend fun fetchQuestions(){
        if (isFetchNeeded()){
            val response = apiRequest { api.getQuestions("ZiXCZbWaOwnDgpVT9Hx8IA((","desc","activity","stackoverflow") }
            questions.postValue(response.items)
        }
    }

    private fun isFetchNeeded():Boolean{
        return true
    }

    private fun saveQuestionsToDB(questions:List<Question>){
        Coroutines.io{
            db.getQuestionDao().saveAllQuestions(questions)
        }
    }

    fun fetchQuestionsFromDB() = db.getQuestionDao().getAllQuestions()
}

