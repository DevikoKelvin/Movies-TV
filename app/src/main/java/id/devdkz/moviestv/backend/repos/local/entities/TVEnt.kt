package id.devdkz.moviestv.backend.repos.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tv_table")
@Parcelize
data class TVEnt(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "image")
    val img : String,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "rating")
    val rating : Double,
    @ColumnInfo(name = "release_date")
    val release : String,
    @ColumnInfo(name = "synopsis")
    val syn : String
) : Parcelable