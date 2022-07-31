package id.devdkz.moviestv.backend.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainData(
    val id : Int,
    val img : String,
    val title : String,
    val rating : Double,
    val release : String,
    val syn : String
) : Parcelable
