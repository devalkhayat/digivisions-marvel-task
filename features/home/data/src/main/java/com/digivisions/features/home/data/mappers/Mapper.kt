package com.digivisions.features.home.data.mappers

import com.digivisions.core.common.ModelType
import com.digivisions.core.common.splitUrl
import com.digivisions.core.datasource.remote.model.dto.ItemDTO
import com.digivisions.core.datasource.remote.model.responses.CharactersResponse
import com.digivisions.core.datasource.remote.model.responses.ComicDetailsResponse
import com.digivisions.core.datasource.remote.model.responses.EventDetailsResponse
import com.digivisions.core.datasource.remote.model.responses.SeriesDetailsResponse
import com.digivisions.core.datasource.remote.model.responses.StoryDetailsResponse
import com.digivisions.features.home.domain.model.character.CharacterModel
import com.digivisions.features.home.domain.model.character.CharactersResultModel
import com.digivisions.features.home.domain.model.details.ComicDetailsModel
import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.details.ComicResultModel
import com.digivisions.features.home.domain.model.DataInfoModel
import com.digivisions.features.home.domain.model.character.EventsModel
import com.digivisions.features.home.domain.model.character.SeriesModel
import com.digivisions.features.home.domain.model.character.StoriesModel
import com.digivisions.features.home.domain.model.details.EventDetailsModel
import com.digivisions.features.home.domain.model.details.EventResultModel
import com.digivisions.features.home.domain.model.details.SeriesDetailsModel
import com.digivisions.features.home.domain.model.details.SeriesResultModel
import com.digivisions.features.home.domain.model.details.StoryDetailsModel
import com.digivisions.features.home.domain.model.details.StoryResultModel

fun CharactersResponse.toDomainInstance(): ArrayList<CharacterModel> {

    val dt=this.data

    val charactersList =ArrayList<CharacterModel>()

    dt.results.forEach { x ->

        val comics = ArrayList<ComicModel>()
        val series = ArrayList<SeriesModel>()
        val events = ArrayList<EventsModel>()
        val stories = ArrayList<StoriesModel>()

        x.comics.items.forEach {
            comics.add(it.toDomainInstance(ModelType.Comic) as ComicModel)
        }
        x.series.items.forEach {
            series.add(it.toDomainInstance(ModelType.Series) as SeriesModel)
        }
        x.events.items.forEach {
            events.add(it.toDomainInstance(ModelType.Event) as EventsModel)

        }
        x.stories.items.forEach {
            stories.add(it.toDomainInstance(ModelType.Story) as StoriesModel)

        }

        charactersList.add(
            CharacterModel(
                id = x.id,
                avatar = "${x.thumbnail.path}/landscape_amazing.${x.thumbnail.extension}",
                full_image = "${x.thumbnail.path}/landscape_incredible.${x.thumbnail.extension}",
                name = x.name,
                description = x.description,
                comicList = comics,
                seriesList = series,
               eventList = events,
                storyList = stories
            )
        )
    }



    return  charactersList
}


fun ItemDTO.toDomainInstance(type:ModelType)=when(type){

    ModelType.Comic -> ComicModel(id= this.resourceURI.splitUrl(), name=this.name)
    ModelType.Series -> SeriesModel(id= this.resourceURI.splitUrl(), name=this.name)
    ModelType.Event -> EventsModel(id= this.resourceURI.splitUrl(),name=this.name)
    ModelType.Story -> StoriesModel(id= this.resourceURI.splitUrl(),name=this.name)
}






fun ComicDetailsResponse.toDomainInstance(): ComicResultModel {
    val dt=this.data
    val dataInfoModel=DataInfoModel(offset = dt.offset, limit = dt.limit, total = dt.total, count = dt.count)
    val currentComic=dt.results[0]
    val comicDetails= ComicDetailsModel(avatar = "${currentComic.thumbnail.path}/portrait_xlarge.${currentComic.thumbnail.extension}", full_image = "${currentComic.thumbnail.path}/portrait_uncanny.${currentComic.thumbnail.extension}")
    return  ComicResultModel(dataInfoModel,comicDetails)
}

fun EventDetailsResponse.toDomainInstance(): EventResultModel {
    val dt=this.data
    val dataInfoModel=DataInfoModel(offset = dt.offset, limit = dt.limit, total = dt.total, count = dt.count)
    val currentEvent=dt.results[0]
    val eventDetails= EventDetailsModel(avatar = "${currentEvent.thumbnail.path}/portrait_xlarge.${currentEvent.thumbnail.extension}",full_image = "${currentEvent.thumbnail.path}/portrait_uncanny.${currentEvent.thumbnail.extension}")
    return  EventResultModel(dataInfoModel,eventDetails)
}


fun StoryDetailsResponse.toDomainInstance(): StoryResultModel {
    val dt=this.data
    val dataInfoModel=DataInfoModel(offset = dt.offset, limit = dt.limit, total = dt.total, count = dt.count)
    val currentStory=dt.results[0]
    val storyDetails= StoryDetailsModel(avatar = "${currentStory.thumbnail.path}/portrait_xlarge.${currentStory.thumbnail.extension}",full_image = "${currentStory.thumbnail.path}/portrait_uncanny.${currentStory.thumbnail.extension}")
    return  StoryResultModel(dataInfoModel,storyDetails)
}

fun SeriesDetailsResponse.toDomainInstance(): SeriesResultModel {
    val dt=this.data
    val dataInfoModel=DataInfoModel(offset = dt.offset, limit = dt.limit, total = dt.total, count = dt.count)
    val currentSeries=dt.results[0]
    val seriesDetails= SeriesDetailsModel(avatar = "${currentSeries.thumbnail.path}/portrait_xlarge.${currentSeries.thumbnail.extension}",full_image = "${currentSeries.thumbnail.path}/portrait_uncanny.${currentSeries.thumbnail.extension}")
    return  SeriesResultModel(dataInfoModel,seriesDetails)
}



