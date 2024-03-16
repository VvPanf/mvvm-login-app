package ru.vvpanf.mvvm_login_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import ru.vvpanf.mvvm_login_app.databinding.ActivityMainBinding
import ru.vvpanf.mvvm_login_app.login.view.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        findViewById<Button>(R.id.button_start).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}