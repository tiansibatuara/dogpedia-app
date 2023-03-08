package com.example.dogpedia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AboutActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val aboutName = getString(R.string.aboutName)
        val aboutEmail = getString(R.string.aboutEmail)
        val shareButton = findViewById<ImageButton>(R.id.action_share)
        shareButton.setOnClickListener {
            val sharedText = aboutName + "\n" + aboutEmail
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, sharedText)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}