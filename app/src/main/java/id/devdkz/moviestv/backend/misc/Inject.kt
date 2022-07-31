package id.devdkz.moviestv.backend.misc

import android.content.Context
import id.devdkz.moviestv.backend.repos.CatalogueRepos
import id.devdkz.moviestv.backend.repos.cloud.CloudSource
import id.devdkz.moviestv.backend.repos.local.LocalSource
import id.devdkz.moviestv.backend.repos.local.database.MainDB

object Inject
{
    fun provide(con : Context) : CatalogueRepos
    {
        val database = MainDB.getInstance(con)
        val remoteDataSource = CloudSource.getInstance()
        val local = LocalSource.getInstance(database.dbQuery())
        return CatalogueRepos.getInstance(remoteDataSource, local)
    }
}