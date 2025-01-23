package com.digivisions.features.home.domain.model.character

import kotlinx.serialization.Serializable

@Serializable
data class EventsModel(val id:Int, var url:String?=null, val name:String)