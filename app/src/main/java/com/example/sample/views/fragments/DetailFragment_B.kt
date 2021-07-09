package com.example.sample.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sample.MainActivity
import com.example.sample.R
import com.example.sample.utils.FirebaseLog_Agent
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment_B : Fragment(R.layout.fragment_detail_b) {

    private val ARG_PARAM by navArgs<DetailFragment_BArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseLog_Agent.logEvent(context,"AB_TEST_DETAIL_FRAG_B","Detail_Fragment_B","View Created")


        var movie = ARG_PARAM.currentMovie

        txt_title.text = movie.title
        txt_cast.text = movie.cast.toString()
        txt_year.text = movie.year.toString()
        txt_genres.text = movie.genres.toString()
        txt_rating.text = movie.rating.toString()


        btn_test.text = MainActivity.buttonText

        btn_test.setOnClickListener {
            FirebaseLog_Agent.logEvent(context,"AB_TEST_DETAIL_FRAG_B","Detail_Fragment_B","Button Clicked")
        }


    }


}