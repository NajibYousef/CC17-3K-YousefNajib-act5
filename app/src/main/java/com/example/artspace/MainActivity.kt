package com.example.artspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

data class Artwork(
    val imageResourceId: Int,
    val title: String,
    val artist: String,
    val year: Int,
    val location: String
)

class MainActivity : AppCompatActivity() {

    private val artworks = listOf(
        Artwork(R.drawable.mona_lisa, "Mona Lisa", "Leonardo da Vinci", 1503, "Louvre Museum, Paris"),
        Artwork(R.drawable.david_sculpture, "David", "Michelangelo", 1504, "Galleria dell'Accademia, Florence"),
        Artwork(R.drawable.face, "Aphrodite of Melos", "Alexandros of Antioch", 150-100, "Louvre Museum, Paris"),
        Artwork(R.drawable.arab, "The Lectern", "Rudolf Ernst", 1920, "Musee des Beaux-Art, Nantes, France"),
        Artwork(R.drawable.paintaing, "Baguio", "Isidro Ancheta", 1936, "Baguio City, Philippines")
    )

    private lateinit var artworkImage: ImageView
    private lateinit var artworkTitle: TextView
    private lateinit var artworkArtist: TextView
    private lateinit var artworkLocation: TextView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        artworkImage = findViewById(R.id.artworkImage)
        artworkTitle = findViewById(R.id.artworkTitle)
        artworkArtist = findViewById(R.id.artworkArtist)
        artworkLocation = findViewById(R.id.artworkLocation)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)

        updateArtwork()

        previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
            updateArtwork()
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % artworks.size
            updateArtwork()
        }
    }

    private fun updateArtwork() {
        val artwork = artworks[currentIndex]
        artworkImage.setImageResource(artwork.imageResourceId)
        artworkTitle.text = artwork.title
        artworkArtist.text = getString(R.string.artist_year, artwork.artist, artwork.year)
        artworkLocation.text = artwork.location
    }
}