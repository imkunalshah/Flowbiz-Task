package com.kunal.flowbiztask.data.network.responses

import com.kunal.flowbiztask.data.network.models.Question

data class QuestionsResponse(
    val items:List<Question>?
)