package ru.vvpanf.mvvm_login_app.login.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object LoginService {
    private val scope = CoroutineScope(Dispatchers.IO)

    interface LoginControllerDelegate {
        fun onSuccess(response: String)
        fun onFailed()
    }

    fun requestLogin(username: String, password: String, delegate: LoginControllerDelegate) {
        scope.launch {
            delay(3000L)
            delegate.onSuccess("{username: 'bob', age: 25}")
//            delegate.onFailed()
        }
    }
}