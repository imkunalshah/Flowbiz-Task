package com.kunal.flowbiztask.util

interface EventListener {
    fun onSuccess()
    fun onFailure(message: String)
    fun onStarted()
}