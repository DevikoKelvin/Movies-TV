package id.devdkz.moviestv.backend.repos.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.repos.local.entities.MovieEnt
import id.devdkz.moviestv.backend.repos.local.entities.TVEnt

@Dao
interface DBQuery
{
    @Query("SELECT * FROM movie_table")
    fun getListMovies(): List<MovieEnt>

    @Query("SELECT * FROM tv_table")
    fun getListTv(): List<TVEnt>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = MovieEnt::class
    )
    fun insertMovies(movies: ArrayList<MovieEnt>)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = TVEnt::class
    )
    fun insertTv(tv: ArrayList<TVEnt>)

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getDetailMovieById(id: Int): MovieEnt

    @Query("SELECT * FROM tv_table WHERE id = :id")
    fun getDetailTvById(id: Int): TVEnt


    // Bookmark
    @Query("SELECT * FROM bookmark WHERE category = :cat")
    fun getListBookmark(cat: String): List<BookmarkEnt>

    @Query("SELECT count(*) FROM bookmark WHERE id = :id")
    fun checkBookmark(id : Int) : Int

    @Query("SELECT * FROM bookmark WHERE id = :id AND category = :cat")
    fun getBookmarkDetail(id: Int, cat: String): LiveData<BookmarkEnt>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = BookmarkEnt::class
    )
    fun insertToBookmark(bookmark: BookmarkEnt)

    @Delete
    fun deleteFromBookmark(bookmark: BookmarkEnt)
}