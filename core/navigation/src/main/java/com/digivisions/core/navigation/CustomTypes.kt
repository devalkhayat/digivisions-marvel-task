package com.digivisions.core.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavType
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.character.EventsModel
import com.digivisions.features.home.domain.model.character.SeriesModel
import com.digivisions.features.home.domain.model.character.StoriesModel
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



val ComicModelType=object :NavType<List<ComicModel>?>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): List<ComicModel>? {

        return bundle.getSerializable(key) as? List<ComicModel>
    }

    override fun parseValue(value: String): List<ComicModel>? {
           return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: List<ComicModel>?) {
        bundle.putSerializable(key, value as? java.io.Serializable)
    }

    override fun serializeAsValue(value: List<ComicModel>?): String {

        return Uri.encode(Json.encodeToString(value))
    }
}

val SeriesModelType=object :NavType<List<SeriesModel>?>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): List<SeriesModel>? {
        return bundle.getSerializable(key) as? List<SeriesModel>
    }

    override fun parseValue(value: String): List<SeriesModel>? {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: List<SeriesModel>?) {
        bundle.putSerializable(key, value as? java.io.Serializable)
    }

    override fun serializeAsValue(value: List<SeriesModel>?): String {

        return Uri.encode(Json.encodeToString(value))
    }
}

val EventModelType=object :NavType<List<EventsModel>?>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): List<EventsModel>? {
        return bundle.getSerializable(key) as? List<EventsModel>
    }

    override fun parseValue(value: String): List<EventsModel>? {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: List<EventsModel>?) {
        bundle.putSerializable(key, value as? java.io.Serializable)
    }

    override fun serializeAsValue(value: List<EventsModel>?): String {

        return Uri.encode(Json.encodeToString(value))
    }
}

val StoryModelType=object :NavType<List<StoriesModel>?>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): List<StoriesModel>? {
        return bundle.getSerializable(key) as? List<StoriesModel>
    }

    override fun parseValue(value: String): List<StoriesModel>? {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: List<StoriesModel>?) {
        bundle.putSerializable(key, value as? java.io.Serializable)
    }

    override fun serializeAsValue(value: List<StoriesModel>?): String {

        return Uri.encode(Json.encodeToString(value))
    }
}
