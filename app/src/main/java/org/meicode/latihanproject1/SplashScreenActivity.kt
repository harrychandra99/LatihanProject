package org.meicode.latihanproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.meicode.latihanproject1.databinding.ActivitySplashScreenBinding


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var bindingSplashScreen : ActivitySplashScreenBinding
    val handler = Handler()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplashScreen = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(bindingSplashScreen.root)
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}