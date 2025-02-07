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



val ComicModelType=object :NavType<List<ComicModel>>(isNullableAllowed = true){
    override fun get(bundle: Bundle, key: String): List<ComicModel>? {
        return bundle.getSerializable(key) as List<ComicModel>
    }

    override fun parseValue(value: String): List<ComicModel> {
           return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: List<ComicModel>) {
        bundle.putSerializable(key, value as java.io.Serializable)
    }

    override fun serializeAsValue(value: List<ComicModel>): String {

        return Uri.encode(Json.encodeToString(value))
    }
}

