package com.digivisions.core.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavType
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.character.EventsModel
import com.digivisions.features.home.domain.model.character.SeriesModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


val CharacterModelType=object : NavType<CharacterModel>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): CharacterModel? {
        return Json.decodeFromString(bundle.getString(key)?: return null)
    }

    override fun parseValue(value: String): CharacterModel {
       var x= Json.decodeFromString<CharacterModel>(Uri.decode(value))
        Log.d("neo9090", "serializeAsValue: ${value}")
        return  Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: CharacterModel) {
        bundle.putString(key,Json.encodeToString(value))
    }

    override fun serializeAsValue(value: CharacterModel): String {

        return Uri.encode(Json.encodeToString(value))
    }

}



val ComicModelType=object :NavType<ComicModel>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): ComicModel? {
        return Json.decodeFromString(bundle.getString(key)?: return null)
    }

    override fun parseValue(value: String): ComicModel {
        var x= Json.decodeFromString<ComicModel>(Uri.decode(value))
        Log.d("neo9090", "serializeAsValue: ${value}")
        return  Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: ComicModel) {
        bundle.putString(key,Json.encodeToString(value))
    }

    override fun serializeAsValue(value: ComicModel): String {

        return Uri.encode(Json.encodeToString(value))
    }

}

val SeriesModelType=object :NavType<SeriesModel>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): SeriesModel? {
        return Json.decodeFromString(bundle.getString(key)?: return null)
    }

    override fun parseValue(value: String): SeriesModel {
        var x= Json.decodeFromString<SeriesModel>(Uri.decode(value))
        Log.d("neo9090", "serializeAsValue: ${value}")
        return  Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: SeriesModel) {
        bundle.putString(key,Json.encodeToString(value))
    }

    override fun serializeAsValue(value: SeriesModel): String {

        return Uri.encode(Json.encodeToString(value))
    }


}

val EventsModelType=object :NavType<EventsModel>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): EventsModel? {
        return Json.decodeFromString(bundle.getString(key)?: return null)
    }

    override fun parseValue(value: String): EventsModel {
        var x= Json.decodeFromString<EventsModel>(Uri.decode(value))
        Log.d("neo9090", "serializeAsValue: ${value}")
        return  Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: EventsModel) {
        bundle.putString(key,Json.encodeToString(value))
    }

    override fun serializeAsValue(value: EventsModel): String {

        return Uri.encode(Json.encodeToString(value))
    }

}