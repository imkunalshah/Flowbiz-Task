package com.kunal.flowbiztask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kunal.flowbiztask.data.repositories.QuestionsRepository

class FilterFragmentVMFactory(
    private val repository: QuestionsRepository,
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilterFragmentVM::class.java)) {
            return FilterFragmentVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
//        return AuthViewModel(repository) as T
    }
}