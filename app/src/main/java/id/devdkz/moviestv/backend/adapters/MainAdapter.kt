package id.devdkz.moviestv.backend.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.devdkz.moviestv.BuildConfig
import id.devdkz.moviestv.R
import id.devdkz.moviestv.backend.data.MainData
import id.devdkz.moviestv.databinding.ListViewBinding

class MainAdapter : PagingDataAdapter<MainData, MainAdapter.RecyclerViewHolder>(diffCallback)
{
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object
    {
        private val diffCallback = object : DiffUtil.ItemCallback<MainData>(){
            override fun areItemsTheSame(oldItem : MainData, newItem : MainData) : Boolean
            = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem : MainData, newItem : MainData) : Boolean
            = oldItem == newItem
        }
    }

    inner class RecyclerViewHolder(v : View) : RecyclerView.ViewHolder(v)
    {
        private val binds = ListViewBinding.bind(v)
        fun binds(data : MainData)
        {
            binds.apply {
                Glide
                    .with(itemView.context)
                    .load(BuildConfig.IMG_URL + data.img)
                    .into(imgShow)
                titleTxt.text = data.title
                synopsisTxt.text = data.syn
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MainData)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerViewHolder
    {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_view,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder : RecyclerViewHolder, x : Int)
    {
        val data = getItem(x)
        if (data != null)
        {
            holder.binds(data)
            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }
}