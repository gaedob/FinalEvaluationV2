package com.inforcap.exampleapiresttmdb.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inforcap.exampleapiresttmdb.R
import com.inforcap.exampleapiresttmdb.core.Constants
import com.inforcap.exampleapiresttmdb.databinding.ItemMovieBinding
import com.inforcap.exampleapiresttmdb.model.FigureEntity

class AdapterFiureMovie(
    val context: Context,
    var movieList: List<FigureEntity>,
    private val onMovieSelected: (Int) -> Unit
) : RecyclerView.Adapter<AdapterFiureMovie.MovieViewHolder>() {

    private lateinit var binding: ItemMovieBinding
    var onClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])

    }

    inner class MovieViewHolder(binding: ItemMovieBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: FigureEntity){
            binding.run {
                Glide.with(context)
                    .load("${movie.logo}")
                    .apply(RequestOptions().override(Constants.IMG_WIDTH, Constants.IMG_HEIGHT))
                    .error(R.drawable.baseline_error_24)
                    .into(binding.ivImage)

                tvInfo.text = HtmlCompat.fromHtml(
                    "<b>Nombre: </b>" + movie.nombre ,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                ivImage.setOnClickListener {
                    onMovieSelected(movie.id)
                }
            }
        }

        private fun showOverview(title: String, overview: String, id :Int) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(id.toString())
            builder.show()
        }
    }
}