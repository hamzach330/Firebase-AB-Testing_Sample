package com.example.sample.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.MainActivity
import com.example.sample.R
import com.example.sample.utils.FirebaseLog_Agent
import com.example.sample.views.model.Movie
import com.example.sample.views.model.Movies
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.IOException


class MainFragment : Fragment(R.layout.fragment_main) {

    var recyclerView: RecyclerView? = null
    var button: Button? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseLog_Agent.logEvent(context,"AB_TEST_MAIN_FRAG","Main_Fragment","View Created")

        recyclerView = view.findViewById(R.id.rc_movies)
        var adapter = ClickableRecyclerViewAdapter(getData()!!)
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()

        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.onItemClick = { item,position ->
            // do something with your item
            val options = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }

            val flowType = MainActivity.defaultFlowType!!

            if (flowType.equals("FlowA") || flowType.equals("null")) {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment2(item)
                findNavController().navigate(action, options)
            } else {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragmentB(item)
                findNavController().navigate(action, options)
            }


        }

        btn_test?.text = MainActivity.buttonText
        Log.d("TAG", "onViewCreated: ")

        btn_test.setOnClickListener {
            FirebaseLog_Agent.logEvent(context,"AB_TEST_MAIN_FRAG","Main_Fragment","Button Clicked")
        }


    }

    fun getData(): ArrayList<Movie>? {
        var list: ArrayList<Movie>? = null
        try {
            val json = getJsonDataFromAsset(requireContext(), "movies.json")
            val gson = Gson()
            val fromJson = gson.fromJson(json, Movies::class.java)
            list = fromJson.movies
            Log.d("MAinActivity", "json here")

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}