package org.meicode.latihanproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.meicode.latihanproject1.databinding.ActivityDescriptionMovieBinding

class DescriptionMovie : AppCompatActivity() {
    private lateinit var binding:ActivityDescriptionMovieBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image: String?  = intent.getStringExtra("imageUrl").toString()
        val name: String?   = intent.getStringExtra("name").toString()
        val desc: String?   = intent.getStringExtra("desc").toString()

        binding.apply {
            tvDescDescriptionMovie.text = desc
            tvNameDescriptionMovie.text = name
        }
        Glide.with(this).load(image).into(binding.ivDescriptionMovie)
    }
}