package com.example.dogpedia

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvDogs: RecyclerView
    private val list = ArrayList<Dogs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDogs = findViewById(R.id.rv_dogs)
        rvDogs.setHasFixedSize(true)

        list.addAll(getListDogs())
        showRecyclerList()

        val aboutButton = findViewById<ImageButton>(R.id.about_page)
        aboutButton.setOnClickListener {
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val recyclerView: RecyclerView = findViewById(R.id.rv_dogs)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visible = recyclerView.computeVerticalScrollOffset() > 0
                if (visible) {
                    toolbar.elevation = resources.getDimension(R.dimen.toolbar_elevation)
                } else {
                    toolbar.elevation = 0f
                }
            }
        })
    }

    private fun getListDogs(): ArrayList<Dogs> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataOtherName = resources.getStringArray(R.array.data_otherName)
        val dataOrigin = resources.getStringArray(R.array.data_origin)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDogs = ArrayList<Dogs>()
        for (i in dataName.indices) {
            val dogs = Dogs(dataName[i], dataDescription[i], dataOtherName[i], dataOrigin[i], dataPhoto.getResourceId(i, -1))
            listDogs.add(dogs)
        }
        return listDogs
    }

    private fun showRecyclerList() {
        rvDogs.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListDogsAdapter(list)
        rvDogs.adapter = listHeroAdapter
    }
}