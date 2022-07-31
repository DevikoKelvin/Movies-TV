package id.devdkz.moviestv.backend.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import id.devdkz.moviestv.backend.misc.Dummy
import id.devdkz.moviestv.backend.misc.LiveDataTest
import id.devdkz.moviestv.backend.repos.cloud.CloudSource
import id.devdkz.moviestv.backend.repos.local.LocalSource
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class CatalogueReposTest
{
    @get : Rule
    var instantExecutor = InstantTaskExecutorRule()

    private val mov = Dummy.addMovDummy()
    private val tv = Dummy.addTVDummy()
    private val movDet = Dummy.getDetMovDummy()
    private val tvDet = Dummy.getDetTVDummy()
    private val bookmark = Dummy.addBookDummy()

    private val cloud = Mockito.mock(CloudSource::class.java)
    private val local = Mockito.mock(LocalSource::class.java)
    private val catRepo = FakeReposCatalogue(cloud, local)

    @Test
    fun checkBookmarkTest()
    {
        // Checking Movie, is it Bookmarked or not.
        val movID = mov[0].id
        `when`(local.getDetailMovie(movID))
            .thenReturn(movDet)

        val movCounter = catRepo.checkBookmark(movDet.id)

        assertEquals(0, movCounter)

        // Checking TV Show, is it bookmarked or not.
        val tvID = tv[0].id
        `when`(local.getDetailTv(tvID))
            .thenReturn(tvDet)

        val tvCounter = catRepo.checkBookmark(tvDet.id)

        assertEquals(0, tvCounter)
    }

    @Test
    fun getListTest()
    {
        // Get Movies List
        `when`(local.getListMovies())
            .thenReturn(mov)

        val movie = LiveDataTest.getVal(catRepo.getList("movie"))
        verify(local).getListMovies()
        assertNotNull(movie)
        assertEquals(mov.size, movie.size)


        // Get TV Shows List
        `when`(local.getListTv())
            .thenReturn(tv)

        val tvs = LiveDataTest.getVal(catRepo.getList("tv"))
        verify(local).getListTv()
        assertNotNull(tvs)
        assertEquals(tv.size, tvs.size)
    }

    @Test
    fun getDetailTest()
    {
        // Get Detailed Movie
        val movID = mov[0].id
        `when`(local.getDetailMovie(movID))
            .thenReturn(movDet)

        val movie = LiveDataTest.getVal(
            catRepo.getDetail("movie", movID)
        )
        verify(local).getDetailMovie(movID)
        assertNotNull(movie)
        assertEquals(movID, movie.id)


        // Get Detailed TV Show
        val tvID = tv[0].id
        `when`(local.getDetailTv(tvID))
            .thenReturn(tvDet)

        val tv = LiveDataTest.getVal(
            catRepo.getDetail("tv", tvID)
        )
        verify(local).getDetailTv(tvID)
        assertNotNull(tv)
        assertEquals(tvID, tv.id)
    }

    @Test
    fun getBookmarkTest()
    {
        // Get Movies Bookmark List
        `when`(local.getListBookmark("movie"))
            .thenReturn(bookmark)

        val movBookmark = LiveDataTest.getVal(
            catRepo.getListBookmark("movie")
        )
        verify(local).getListBookmark("movie")
        assertNotNull(movBookmark)
        assertEquals(bookmark.size, movBookmark.size)


        // Get TV Shows Bookmark List
        `when`(local.getListBookmark("tv"))
            .thenReturn(bookmark)

        val tvBookmark = LiveDataTest.getVal(
            catRepo.getListBookmark("tv")
        )
        verify(local).getListBookmark("tv")
        assertNotNull(tvBookmark)
        assertEquals(bookmark.size, tvBookmark.size)
    }

    @Test
    fun getBookmarkDetailTest()
    {
        // Get Detailed Movie Bookmark
        val movID = bookmark[0].id
        val movie = MutableLiveData<BookmarkEnt>()
        movie.value = Dummy.getMovDetBook()
        `when`(local.getBookmarkDetail(movID, "movie"))
            .thenReturn(movie)

        val movDet = LiveDataTest.getVal(
            catRepo.getBookmarkDetail(movID, "movie")
        )
        verify(local).getBookmarkDetail(movID, "movie")
        assertNotNull(movDet)
        assertEquals(movID, movDet.id)


        // Get Detailed TV Show Bookmark
        val tvID = bookmark[1].id
        val tv = MutableLiveData<BookmarkEnt>()
        tv.value = Dummy.getTVDetBook()
        `when`(local.getBookmarkDetail(tvID, "tv"))
            .thenReturn(tv)

        val tvDet = LiveDataTest.getVal(
            catRepo.getBookmarkDetail(tvID, "tv")
        )
        verify(local).getBookmarkDetail(tvID, "tv")
        assertNotNull(tvDet)
        assertEquals(tvID, tvDet.id)
    }
}