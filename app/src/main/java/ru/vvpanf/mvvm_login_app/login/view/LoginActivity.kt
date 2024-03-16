package ru.vvpanf.mvvm_login_app.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.vvpanf.mvvm_login_app.R
import ru.vvpanf.mvvm_login_app.databinding.ActivityLoginBinding
import ru.vvpanf.mvvm_login_app.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var frameProgress: FrameLayout

//    private lateinit var loginViewModel: LoginViewModel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
//        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContentView(mBinding.root)

        frameProgress = findViewById(R.id.frame_progress)
        editTextUsername = findViewById(R.id.edit_username)
        editTextPassword = findViewById(R.id.edit_password)
        buttonLogin = findViewById<Button?>(R.id.button_login).apply {
            setOnClickListener {
                editTextUsername.onEditorAction(EditorInfo.IME_ACTION_DONE)
                editTextPassword.onEditorAction(EditorInfo.IME_ACTION_DONE)
                loginViewModel.login(editTextUsername.text.toString(), editTextPassword.text.toString())
            }
        }

        loginViewModel.let {vm ->
            vm.isLoadingLive.observe(this) { isLoading ->
                frameProgress.visibility = if (isLoading) View.VISIBLE else View.GONE
                Log.e("???", frameProgress.visibility.toString())
            }

            vm.userLive.observe(this) { user ->
                makeToast("Username: ${user.username}, Password: ${user.age}")
            }

            vm.errorMessageLive.observe(this) { error ->
                makeToast(error)
            }
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}