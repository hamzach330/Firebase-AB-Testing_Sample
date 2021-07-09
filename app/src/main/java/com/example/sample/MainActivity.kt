package com.example.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.sample.utils.RemoteConfigUtils
import com.example.sample.views.model.Movie
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    lateinit var moviesList: ArrayList<Movie>

    companion object {
        var defaultFlowType: String = "FlowA"
        var buttonText: String = "Local-Button"
        var buttonColor: String ="Local-Button-Color"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val extras = intent.extras
        if (extras != null) {
            defaultFlowType = extras.getString("FlowType").toString()
            // and get whatever type user account id is
        }


        FirebaseInstallations.getInstance().getToken(/* forceRefresh */ true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Installations", "Installation auth token: " + task.result?.token)
                } else {
                    Log.e("Installations", "Unable to get Installation auth token")
                }
            }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            } // Get new FCM registration token
            val token = task.result
            Log.e("FCM Token ::", token)


        })

        val rc = RemoteConfigUtils.init()
        val str = RemoteConfigUtils.getSampleText()

        buttonText = RemoteConfigUtils.getNextButtonText()
        buttonColor = RemoteConfigUtils.getNextButtonColor()




    }


}





















