package com.example.dogpedia

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_DOGS = "key_dogs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val nestedScrollView: NestedScrollView = findViewById(R.id.nestedScroll)

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                Log.i(TAG, "Scroll DOWN")
            }
            if (scrollY < oldScrollY) {
                toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                Log.i(TAG, "Scroll UP")
            }
            if (scrollY == 0) {
                toolbar.elevation = 0f
                Log.i(TAG, "TOP SCROLL")
            }
            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
                toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                Log.i(TAG, "BOTTOM SCROLL")
            }
        })

        val dataDogs = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_DOGS, Dogs::class.java)
        } else {
            intent.getParcelableExtra(KEY_DOGS)
        }

        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvOtherName : TextView = findViewById(R.id.tv_other_name)
        val tvOrigin : TextView = findViewById(R.id.tv_origin)
        val tvDetailDescription : TextView = findViewById(R.id.tv_detail_description)
        val tvDetailPhoto : ImageView = findViewById(R.id.tv_detail_photo)

        tvDetailName.text = dataDogs?.name
        tvDetailDescription.text = dataDogs?.description
        tvOtherName.text = dataDogs?.otherName
        tvOrigin.text = dataDogs?.origin
        if (dataDogs != null) {
            tvDetailPhoto.setImageResource(dataDogs.photo)
        }

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val shareButton = findViewById<ImageButton>(R.id.action_share)
        shareButton.setOnClickListener {
            val sharedText = tvDetailName.text.toString() + "\n\n" + tvDetailDescription.text.toString()
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, sharedText)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}