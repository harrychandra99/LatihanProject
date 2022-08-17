package org.meicode.latihanproject1.RecyclerViewMain

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.meicode.latihanproject1.DataModel.Movie
import org.meicode.latihanproject1.DescriptionMovie
import org.meicode.latihanproject1.databinding.AdapterMovieBinding

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    private lateinit var binding : AdapterMovieBinding
    var movies = mutableListOf<Movie>()

    fun setMovieList(movies:List<Movie>){
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder : MainViewHolder, position : Int) {
        val movie = movies[position]
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageView)
        binding.imageView.setOnClickListener { view : View ->
            val intentCategoryExtra = Intent(holder.itemView.context, DescriptionMovie::class.java)
            intentCategoryExtra.putExtra("category",movie.category)
            intentCategoryExtra.putExtra("imageUrl",movie.imageUrl)
            intentCategoryExtra.putExtra("name",movie.name)
            intentCategoryExtra.putExtra("desc",movie.desc)
            holder.itemView.context.startActivity(intentCategoryExtra)
        }
    }

    override fun getItemCount() : Int {
        return movies.size
    }
}

