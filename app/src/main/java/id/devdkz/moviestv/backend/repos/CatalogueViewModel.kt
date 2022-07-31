package id.devdkz.moviestv.backend.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.data.MainData

interface CatalogueViewModel
{
    fun getList(cat : String) : LiveData<PagedList<MainData>>

    fun getDetail(cat : String, id : Int) : LiveData<MainData>

    // Bookmark
    fun getListBookmark(cat : String) : LiveData<PagedList<MainData>>

    fun checkBookmark(id : Int) : Int

    fun insertToBookmark(bookmark : BookmarkEnt)

    fun getBookmarkDetail(id : Int, cat : String) : LiveData<BookmarkEnt>

    fun deleteFromBookmark(bookmark : BookmarkEnt)
}