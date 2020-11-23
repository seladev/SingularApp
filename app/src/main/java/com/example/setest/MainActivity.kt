package com.example.setest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.singular.sdk.*;

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSingularSDK()

        notify_sdk.setOnClickListener {
            Toast.makeText(this, "Notify", Toast.LENGTH_LONG).show()

            Singular.event("“BtnClick", "Salit")

        }
    }

    private fun initSingularSDK() {
        val config = SingularConfig(
                API_KEY,
                SECRET)
                //.withCustomUserId("salitb@gmail.com")

        config.withSingularLink(intent) { params ->
            Log.d(TAG, "initSingularSDK: withSingularLink params = $params")
            val deepLink = params.deeplink
            val passthrough = params.passthrough
            val isDeferred = params.isDeferred

            Singular.event("isFromDeepLink", "link $deepLink passthrough = $passthrough isDeferred = $isDeferred")
            runOnUiThread {
                web_view.loadUrl(deepLink)
            }

        }
        Singular.init(this, config)
    }


}