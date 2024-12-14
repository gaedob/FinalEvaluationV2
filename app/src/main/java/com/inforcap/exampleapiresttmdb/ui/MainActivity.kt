package com.inforcap.exampleapiresttmdb.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.inforcap.exampleapiresttmdb.core.Constants
import com.inforcap.exampleapiresttmdb.databinding.ActivityMainBinding
import com.inforcap.exampleapiresttmdb.model.FigureDetailEntity
import com.inforcap.exampleapiresttmdb.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapterMovie: AdapterFiureMovie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        initRecyclerView()

        binding.tvCategory.text = Constants.CATEGORY_ALLMOVIES
        viewModel.getAllFigures()

        viewModel.movieList.observe(this) {
            adapterMovie.movieList = it
            adapterMovie.notifyDataSetChanged()
        }


    }



    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager

        adapterMovie = AdapterFiureMovie(this, arrayListOf()) { movieId ->
            Log.d("MainActivity", "Movie selected with ID: $movieId")
            fetchMovieDetails(movieId) // Llamada al método que interactúa con el microservicio
        }
        binding.rvMovies.adapter = adapterMovie
    }

    private fun fetchMovieDetails(movieId: Int) {

        viewModel.getDetail(movieId)

        viewModel.figureDetail.observe(this) { detail ->
            if (detail != null) {
                Log.d("MainActivity", "Detail received: $detail")

                navigateToDetailActivity(detail)
            } else {
                Log.e("MainActivity", "Failed to fetch details")
            }
        }

    }
    private fun navigateToDetailActivity(detail: FigureDetailEntity) {
        val intent = Intent(this, DetailsFigureActivity::class.java).apply {


            putExtra("BUNDLE", Bundle().apply {
                if (detail != null) {
                    putParcelable("DETAIL",detail)
                }
            })


        }
        startActivity(intent)
    }




}