package id.devdkz.moviestv.backend.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.devdkz.moviestv.backend.data.MainData
import id.devdkz.moviestv.backend.dataconvert.Converter.movieDetailToMainData
import id.devdkz.moviestv.backend.dataconvert.Converter.tvDetailToMainData
import id.devdkz.moviestv.backend.misc.Dummy
import id.devdkz.moviestv.backend.misc.LiveDataTest
import id.devdkz.moviestv.backend.repos.CatalogueRepos
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest
{
    @get : Rule
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var catRepo : CatalogueRepos

    @Mock
    private lateinit var obs : Observer<PagedList<MainData>>

    @Mock
    private lateinit var obsDet : Observer<MainData>

    @Mock
    private lateinit var obsBook : Observer<BookmarkEnt>

    @Mock
    private lateinit var data : PagedList<MainData>

    private lateinit var vm : MainViewModel
    private val movieDet = Dummy.addMovDummy()[0].movieDetailToMainData()
    private val movID = movieDet.id
    private val tvDet = Dummy.addTVDummy()[0].tvDetailToMainData()
    private val tvID = tvDet.id
    private val bookmark = Dummy.addBookDummy()
    private val movBookDet = Dummy.getMovDetBook()
    private val tvBookDet = Dummy.getTVDetBook()

    @Before
    fun setup()
    {
        vm = MainViewModel(catRepo)
    }

    @Test
    fun getMovListTest()
    {
        val movie = data
        `when`(movie.size)
            .thenReturn(1)
        val movList = MutableLiveData<PagedList<MainData>>()
        movList.value = movie

        `when`(catRepo.getList("movie"))
            .thenReturn(movList)
        val list = vm.getList("movie").value
        verify(catRepo).getList("movie")
        assertNotNull(list)
        assertEquals(1, list?.size)

        vm.getList("movie").observeForever(obs)
        verify(obs).onChanged(movie)
    }

    @Test
    fun getTVListTest()
    {
        val tv = data
        `when`(tv.size)
            .thenReturn(1)
        val tvList = MutableLiveData<PagedList<MainData>>()
        tvList.value = tv

        `when`(catRepo.getList("tv"))
            .thenReturn(tvList)
        val list = vm.getList("tv").value
        verify(catRepo).getList("tv")
        assertNotNull(list)
        assertEquals(1, list?.size)

        vm.getList("tv").observeForever(obs)
        verify(obs).onChanged(tv)
    }

    @Test
    fun getMovDetTest()
    {
        val id = Dummy.addMovDummy()[0].id
        val movie = MutableLiveData<MainData>()
        movie.value = movieDet

        `when`(catRepo.getDetail("movie", id))
            .thenReturn(movie)

        val mov = vm.getDetailData("movie", movID).value as MainData

        assertNotNull(mov)
        assertEquals(movieDet.id, mov.id)
        assertEquals(movieDet.img, mov.img)
        assertEquals(movieDet.title, mov.title)
        assertEquals(movieDet.rating, mov.rating, 8.6)
        assertEquals(movieDet.release, mov.release)
        assertEquals(movieDet.syn, mov.syn)

        vm.getDetailData("movie", movID).observeForever(obsDet)
        verify(obsDet).onChanged(movieDet)
    }

    @Test
    fun getTVDetTest()
    {
        val id = Dummy.addTVDummy()[0].id
        val tv = MutableLiveData<MainData>()
        tv.value = tvDet

        `when`(catRepo.getDetail("tv", id))
            .thenReturn(tv)

        val tvs = vm.getDetailData("tv", tvID).value as MainData

        assertNotNull(tvs)
        assertEquals(tvDet.id, tvs.id)
        assertEquals(tvDet.img, tvs.img)
        assertEquals(tvDet.title, tvs.title)
        assertEquals(tvDet.rating, tvs.rating, 8.1)
        assertEquals(tvDet.release, tvs.release)
        assertEquals(tvDet.syn, tvs.syn)

        vm.getDetailData("tv", tvID).observeForever(obsDet)
        verify(obsDet).onChanged(tvDet)
    }

    @Test
    fun getBookmarkMovTest()
    {
        val movie = data
        `when`(movie.size)
            .thenReturn(1)
        val movList = MutableLiveData<PagedList<MainData>>()
        movList.value = movie

        `when`(catRepo.getList("movie"))
            .thenReturn(movList)
        val list = vm.getList("movie").value
        verify(catRepo).getList("movie")
        assertNotNull(list)
        assertEquals(1, list?.size)

        vm.getList("movie").observeForever(obs)
        verify(obs).onChanged(movie)
    }

    @Test
    fun getBookmarkTVTest()
    {
        val tv = data
        `when`(tv.size)
            .thenReturn(1)
        val tvList = MutableLiveData<PagedList<MainData>>()
        tvList.value = tv

        `when`(catRepo.getList("tv"))
            .thenReturn(tvList)
        val list = vm.getList("tv").value
        verify(catRepo).getList("tv")
        assertNotNull(list)
        assertEquals(1, list?.size)

        vm.getList("tv").observeForever(obs)
        verify(obs).onChanged(tv)
    }

    @Test
    fun getDetMovBookmarkTest()
    {
        val movID = bookmark[0].id
        val movie = MutableLiveData<BookmarkEnt>()
        movie.value = Dummy.getMovDetBook()
        `when`(catRepo.getBookmarkDetail(movID, "movie"))
            .thenReturn(movie)

        val movDet = LiveDataTest.getVal(
            catRepo.getBookmarkDetail(movID, "movie")
        )
        verify(catRepo).getBookmarkDetail(movID, "movie")
        assertNotNull(movDet)
        assertEquals(movID, movDet.id)

        vm.getBookmarkDetail(movID, "movie").observeForever(obsBook)
        verify(obsBook).onChanged(movBookDet)
    }

    @Test
    fun getDetTVBookmarkTest()
    {
        val tvID = bookmark[1].id
        val tv = MutableLiveData<BookmarkEnt>()
        tv.value = Dummy.getTVDetBook()
        `when`(catRepo.getBookmarkDetail(tvID, "tv"))
            .thenReturn(tv)

        val tvDet = LiveDataTest.getVal(
            catRepo.getBookmarkDetail(tvID, "tv")
        )
        verify(catRepo).getBookmarkDetail(tvID, "tv")
        assertNotNull(tvDet)
        assertEquals(tvID, tvDet.id)

        vm.getBookmarkDetail(tvID, "tv").observeForever(obsBook)
        verify(obsBook).onChanged(tvBookDet)
    }
}