package id.devdkz.moviestv.backend.dataconvert

import android.os.Handler
import android.os.Looper
import androidx.paging.PositionalDataSource
import java.util.concurrent.Executor

class ListData<T>(private val list : List<T>) : PositionalDataSource<T>()
{
    override fun loadInitial(params : LoadInitialParams, callback : LoadInitialCallback<T>) =
        callback.onResult(list, 0, list.size)

    override fun loadRange(params : LoadRangeParams, callback : LoadRangeCallback<T>)
    {
        val start = params.startPosition
        val buff = params.startPosition + params.loadSize

        val end = if (buff >= list.size)
            list.size
        else
            buff

        callback.onResult(list.subList(start, end))
    }
}

class UiExecutor : Executor
{
    private val handler = Handler(Looper.getMainLooper())

    override fun execute(command : Runnable)
    {
        handler.post(command)
    }
}