package com.digivisions.features.home.ui.screens.details


import com.digivisions.features.home.domain.model.character.ComicModel
import com.digivisions.features.home.domain.model.character.EventsModel
import com.digivisions.features.home.domain.model.character.SeriesModel
import com.digivisions.features.home.domain.model.character.StoriesModel

sealed class ComicsStateHolder {
    data object Loading:ComicsStateHolder()
    data class Finish(val data:ArrayList<ComicModel>):ComicsStateHolder()
}
sealed class EventStateHolder {
    data object Loading:EventStateHolder()
    data class Finish(val data:ArrayList<EventsModel>):EventStateHolder()
}

sealed class StoriesStateHolder {
    data object Loading:StoriesStateHolder()
    data class Finish(val data:ArrayList<StoriesModel>):StoriesStateHolder()
}

sealed class SeriesStateHolder {
    data object Loading:SeriesStateHolder()
    data class Finish(val data:ArrayList<SeriesModel>):SeriesStateHolder()
}


