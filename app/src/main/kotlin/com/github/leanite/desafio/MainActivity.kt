package com.github.leanite.desafio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed({
            startActivity(Intent(this, Class.forName("com.github.leanite.desafio.features.repositories.RepositoryListActivity")))
        }, 2000)
    }
}
