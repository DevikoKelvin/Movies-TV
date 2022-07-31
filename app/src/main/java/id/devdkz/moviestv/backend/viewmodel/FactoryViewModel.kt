package id.devdkz.moviestv.backend.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.devdkz.moviestv.backend.repos.CatalogueRepos
import id.devdkz.moviestv.backend.misc.Inject

class FactoryViewModel private constructor(private val catRepo : CatalogueRepos) :
    ViewModelProvider.NewInstanceFactory()
{
    companion object
    {
        @Volatile
        private var instance : FactoryViewModel? = null

        fun getInstance(context : Context) : FactoryViewModel =
            instance ?: synchronized(this) {
                instance ?: FactoryViewModel(Inject.provide(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass : Class<T>) : T
    {
        return when
        {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(catRepo) as T
            else ->
                throw Throwable("Unlisted ViewModel: " + modelClass.name)
        }
    }
}