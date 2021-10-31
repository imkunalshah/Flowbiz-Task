package com.kunal.flowbiztask.data.network.models

data class Owner (
    var reputation:Long,
    var user_id:Long,
    var user_type:String,
    var accept_rate:Long,
    var profile_image:String,
    var display_name:String,
    var link:String
)