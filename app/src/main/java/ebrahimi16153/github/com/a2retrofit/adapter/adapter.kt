package ebrahimi16153.github.com.a2retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ebrahimi16153.github.com.a2retrofit.databinding.MovieListItemBinding
import ebrahimi16153.github.com.a2retrofit.model.ResponseMoviesList


class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViews(diff.currentList[position])
    }

    override fun getItemCount() =diff.currentList.size


    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setViews(item: ResponseMoviesList.Data) {
            binding.movieName.text = item.title
            binding.poster.load(item.poster)
        }


    }

    private val diffCalBack = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
        override fun areItemsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem == newItem
        }

    }

    val diff = AsyncListDiffer(this,diffCalBack)


}