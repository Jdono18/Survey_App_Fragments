package com.example.survey_app_fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.survey_fragment_container)

        if (currentFragment == null) {
            val fragment = SurveyFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.survey_fragment_container, fragment)
                .commit()
        }

    }
}