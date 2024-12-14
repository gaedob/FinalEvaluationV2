package com.inforcap.exampleapiresttmdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.inforcap.exampleapiresttmdb.R
import com.inforcap.exampleapiresttmdb.databinding.ActivityDetailsBinding
import com.inforcap.exampleapiresttmdb.model.FigureDetailEntity


class DetailsFigureActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras?.getBundle("BUNDLE")
        val detail = bundle?.getParcelable<FigureDetailEntity>("DETAIL")

        detail?.run {
            Glide.with(applicationContext)
                .load(logo)
                .fitCenter()
                .error(R.drawable.baseline_error_24)
                .into(binding.imageViewStore)

            binding.textViewStoreName.text = nombre
            binding.textViewStorePelicula.text = origen
            binding.textViewStoreHistory.text = descripcion
            binding.textViewStorePrice.text = precio.toString()

        }
        // Habilitar el botón de "volver" en la barra de acción
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Activa el botón "Up"
            setDisplayShowHomeEnabled(true) // Habilita el icono del botón
            title = "Detalles de Figura" // Cambia el título si lo necesitas
        }

    }


}