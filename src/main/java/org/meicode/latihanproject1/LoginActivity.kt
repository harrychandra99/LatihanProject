package org.meicode.latihanproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.meicode.latihanproject1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        finish()
    }
}