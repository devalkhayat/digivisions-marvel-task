package com.digivisions.features.home.domain.model.character

import kotlinx.serialization.Serializable

@Serializable
data class StoriesModel(val id:Int, var url:String?=null,var url_large:String?=null, val name:String)