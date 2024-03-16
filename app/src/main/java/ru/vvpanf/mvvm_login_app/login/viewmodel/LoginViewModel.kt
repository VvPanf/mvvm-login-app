package ru.vvpanf.mvvm_login_app.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import org.json.JSONTokener
import ru.vvpanf.mvvm_login_app.login.model.User
import ru.vvpanf.mvvm_login_app.login.service.LoginService

class LoginViewModel() : ViewModel() {
    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val user: MutableLiveData<User> = MutableLiveData()

    val isLoadingLive: LiveData<Boolean> = isLoading
    val errorMessageLive: LiveData<String> = errorMessage
    val userLive: MutableLiveData<User> = user

    fun login(username: String, password: String) {
        isLoading.value = true
        LoginService.requestLogin(username, password, object: LoginService.LoginControllerDelegate {
            override fun onSuccess(response: String) {
                Log.d("???", "onSuccess")
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                user.postValue(User(jsonObject.getString("username"), jsonObject.getInt("age")))
                isLoading.postValue(false)
            }

            override fun onFailed() {
                Log.d("???", "onFailed")
                errorMessage.postValue("Error")
                isLoading.postValue(false)
            }
        })
    }

}