package id.devdkz.moviestv.backend.dataconvert

import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.repos.local.entities.MovieEnt
import id.devdkz.moviestv.backend.repos.local.entities.TVEnt
import id.devdkz.moviestv.backend.data.MainData

object Converter
{
    fun movieEntityToMainData(listDB : List<MovieEnt>) : ArrayList<MainData>
    {
        val list = ArrayList<MainData>()
        for (element in listDB)
        {
            list.add(
                MainData(
                    element.id,
                    element.img,
                    element.title,
                    element.rating,
                    element.release,
                    element.syn
                )
            )
        }

        return list
    }

    fun mainDataToMovieEntity(listDB : List<MainData>) : ArrayList<MovieEnt>
    {
        val list = ArrayList<MovieEnt>()
        for (element in listDB)
        {
            list.add(
                MovieEnt(
                    element.id,
                    element.img,
                    element.title,
                    element.rating,
                    element.release,
                    element.syn
                )
            )
        }

        return list
    }

    fun tvEntityToMainData(listDB : List<TVEnt>) : ArrayList<MainData>
    {
        val list = ArrayList<MainData>()
        for (element in listDB)
        {
            list.add(
                MainData(
                    element.id,
                    element.img,
                    element.title,
                    element.rating,
                    element.release,
                    element.syn
                )
            )
        }

        return list
    }

    fun mainDataToTvEntity(listDB : List<MainData>) : ArrayList<TVEnt>
    {
        val list = ArrayList<TVEnt>()
        for (element in listDB)
        {
            list.add(
                TVEnt(
                    element.id,
                    element.img,
                    element.title,
                    element.rating,
                    element.release,
                    element.syn
                )
            )
        }

        return list
    }

    fun bookmarkEntityToMainData(listDB : List<BookmarkEnt>) : ArrayList<MainData>
    {
        val list = ArrayList<MainData>()
        for (element in listDB)
        {
            list.add(
                MainData(
                    element.id,
                    element.img,
                    element.title,
                    element.rating,
                    element.release,
                    element.syn
                )
            )
        }

        return list
    }

    fun MovieEnt.movieDetailToMainData() = MainData(
        id = id,
        img = img,
        title = title,
        rating = rating,
        release = release,
        syn = syn
    )

    fun TVEnt.tvDetailToMainData() = MainData(
        id = id,
        img = img,
        title = title,
        rating = rating,
        release = release,
        syn = syn
    )

    fun MainData.mainDataToBookmarkEntity(cat : String) = BookmarkEnt(
        id = id,
        img = img,
        title = title,
        rating = rating,
        release = release,
        syn = syn,
        cat
    )
}