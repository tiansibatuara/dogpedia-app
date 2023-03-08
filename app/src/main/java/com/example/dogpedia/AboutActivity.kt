package com.example.dogpedia

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView

class AboutActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val nestedScrollView: NestedScrollView = findViewById(R.id.nestedScroll)

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                Log.i(ContentValues.TAG, "Scroll DOWN")
            }
            if (scrollY < oldScrollY) {
                toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                Log.i(ContentValues.TAG, "Scroll UP")
            }
            if (scrollY == 0) {
                toolbar.elevation = 0f
                Log.i(ContentValues.TAG, "TOP SCROLL")
            }
            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
                toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                Log.i(ContentValues.TAG, "BOTTOM SCROLL")
            }
        })

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