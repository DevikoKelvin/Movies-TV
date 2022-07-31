package id.devdkz.moviestv.backend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.devdkz.moviestv.backend.repos.CatalogueRepos
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.data.MainData

@Suppress("UNCHECKED_CAST")
class MainViewModel(private val catRepo : CatalogueRepos) : ViewModel()
{
    fun getList(cat: String): LiveData<PagedList<MainData>> = catRepo.getList(cat)

    fun getDetailData(cat: String, id: Int): LiveData<MainData> = catRepo.getDetail(cat, id)


    fun getListBookmark(cat: String) = catRepo.getListBookmark(cat)

    suspend fun checkBookmark(id : Int) = catRepo.checkBookmark(id)

    fun getBookmarkDetail(id: Int, cat: String): LiveData<BookmarkEnt>
    = catRepo.getBookmarkDetail(id, cat)

    fun insertToBookmark(bookmark: BookmarkEnt) = catRepo.insertToBookmark(bookmark)

    fun deleteFromBookmark(bookmark : BookmarkEnt) = catRepo.deleteFromBookmark(bookmark)
}