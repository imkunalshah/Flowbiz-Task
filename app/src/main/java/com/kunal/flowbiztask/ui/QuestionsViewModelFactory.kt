package com.kunal.flowbiztask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kunal.flowbiztask.data.repositories.QuestionsRepository

class QuestionsViewModelFactory(
    private val repository: QuestionsRepository,
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            return QuestionsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
//        return AuthViewModel(repository) as T
    }
}