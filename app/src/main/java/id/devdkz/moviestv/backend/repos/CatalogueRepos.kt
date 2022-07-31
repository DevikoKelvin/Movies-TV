package id.devdkz.moviestv.backend.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import id.devdkz.moviestv.backend.repos.cloud.CloudSource
import id.devdkz.moviestv.backend.repos.local.LocalSource
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.data.MainData
import id.devdkz.moviestv.backend.dataconvert.Converter.bookmarkEntityToMainData
import id.devdkz.moviestv.backend.dataconvert.Converter.mainDataToMovieEntity
import id.devdkz.moviestv.backend.dataconvert.Converter.mainDataToTvEntity
import id.devdkz.moviestv.backend.dataconvert.Converter.movieDetailToMainData
import id.devdkz.moviestv.backend.dataconvert.Converter.movieEntityToMainData
import id.devdkz.moviestv.backend.dataconvert.Converter.tvDetailToMainData
import id.devdkz.moviestv.backend.dataconvert.Converter.tvEntityToMainData
import id.devdkz.moviestv.backend.dataconvert.ListData
import id.devdkz.moviestv.backend.dataconvert.UiExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class CatalogueRepos(private val cloud : CloudSource, private val local : LocalSource)
    : CatalogueViewModel
{
    private val config = PagedList.Config.Builder()
        .setPageSize(4)
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(4)
        .build()

    companion object
    {
        @Volatile
        private var instance : CatalogueRepos? = null

        fun getInstance(cloud : CloudSource, local : LocalSource) : CatalogueRepos =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepos(cloud, local)
            }
    }

    override fun getList(cat : String) : LiveData<PagedList<MainData>>
    {
        val liveData = MutableLiveData<PagedList<MainData>>()

        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                cloud.getList(cat, object : CloudSource.LoadPopularCallback
                {
                    override fun onPopularReceived(response : ArrayList<MainData>)
                    {
                        if (cat == "movie")
                        {
                            Log.d("Mov Response", response.toString())
                            local.insertMovies(mainDataToMovieEntity(response))
                        }
                        else
                        {
                            Log.d("TV Response", response.toString())
                            local.insertTv(mainDataToTvEntity(response))
                        }
                    }
                })
            }
            catch (e : Exception) { }

            val list = if (cat == "movie")
            {
                movieEntityToMainData(local.getListMovies())
            }
            else
            {
                tvEntityToMainData(local.getListTv())
            }

            val pagedList = PagedList.Builder(ListData(list), config)
                .setNotifyExecutor(UiExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
            liveData.postValue(pagedList)
        }

        return liveData
    }

    override fun getDetail(cat : String, id : Int) : LiveData<MainData>
    {
        val data = MutableLiveData<MainData>()

        CoroutineScope(Dispatchers.IO).launch {
            if (cat == "movie")
            {
                data.postValue(local.getDetailMovie(id).movieDetailToMainData())
            }
            else if (cat == "tv")
            {
                data.postValue(local.getDetailTv(id).tvDetailToMainData())
            }
        }

        return data
    }

    override fun getListBookmark(cat : String) : LiveData<PagedList<MainData>>
    {
        val pagedData = MutableLiveData<PagedList<MainData>>()

        CoroutineScope(Dispatchers.IO).launch {
            val list = bookmarkEntityToMainData(local.getListBookmark(cat))
            val pagedList = PagedList.Builder(ListData(list), config)
                .setNotifyExecutor(UiExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
            pagedData.postValue(pagedList)
        }

        return pagedData
    }

    override fun checkBookmark(id : Int) = local.checkBookmark(id)

    override fun insertToBookmark(bookmark : BookmarkEnt)
    {
        CoroutineScope(Dispatchers.IO).launch {
            local.insertToBookmark(bookmark)
        }
    }

    override fun getBookmarkDetail(id : Int, cat : String) : LiveData<BookmarkEnt> =
        local.getBookmarkDetail(id, cat)

    override fun deleteFromBookmark(bookmark : BookmarkEnt)
    {
        CoroutineScope(Dispatchers.IO).launch {
            local.deleteFromBookmark(bookmark)
        }
    }
}