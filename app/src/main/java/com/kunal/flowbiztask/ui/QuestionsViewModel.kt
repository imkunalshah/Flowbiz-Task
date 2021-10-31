package com.kunal.flowbiztask.ui

import androidx.lifecycle.ViewModel
import com.kunal.flowbiztask.data.network.models.Question
import com.kunal.flowbiztask.data.repositories.QuestionsRepository
import com.kunal.flowbiztask.util.ApiException
import com.kunal.flowbiztask.util.Coroutines
import com.kunal.flowbiztask.util.EventListener
import com.kunal.flowbiztask.util.NoInternetException
import java.lang.Exception
import java.util.ArrayList

class QuestionsViewModel(
    private val repository: QuestionsRepository
    ) :ViewModel() {
    lateinit var questions: ArrayList<Question>
    var eventListener: EventListener? = null
    fun fetch(){
        questions = arrayListOf()
        Coroutines.main {
            try {
                eventListener?.onStarted()
                questions.clear()
                val response = repository.getQuestions()
                response.let {
                    it.observeForever { it1->
                        if (it1.isNotEmpty()){
                            for (element in it1)
                            {
                                questions.add(element)
                            }
                            eventListener?.onSuccess()
                        }
                        else{
                            questions.clear()
                            eventListener?.onFailure("Not Found")
                            return@observeForever
                        }
                    }
                    return@main
                }
            }catch (e: ApiException){
                try {
                    val response = repository.fetchQuestionsFromDB()
                    response.let {
                        it.observeForever { it1->
                            if (it1.isNotEmpty()){
                                for (element in it1)
                                {
                                    questions.add(element)
                                }
                                eventListener?.onSuccess()
                            }
                            else{
                                questions.clear()
                                eventListener?.onFailure("Not Found")
                                return@observeForever
                            }
                        }
                        return@main
                    }
                }catch (e1:Exception){
                    eventListener?.onFailure(e.message!!)
                }

            }catch (e: NoInternetException){
                try {
                    val response = repository.fetchQuestionsFromDB()
                    response.let {
                        it.observeForever { it1->
                            if (it1.isNotEmpty()){
                                for (element in it1)
                                {
                                    questions.add(element)
                                }
                                eventListener?.onSuccess()
                            }
                            else{
                                questions.clear()
                                eventListener?.onFailure("Not Found")
                                return@observeForever
                            }
                        }
                        return@main
                    }
                }catch (e1:Exception){
                    eventListener?.onFailure(e.message!!)
                }
            }
        }

    }
}