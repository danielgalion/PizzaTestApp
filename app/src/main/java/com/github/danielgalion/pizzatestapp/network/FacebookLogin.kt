package com.github.danielgalion.pizzatestapp.network

import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.github.danielgalion.pizzatestapp.utils.makeToast

fun handleLoginManagerCallback(callbackManager: CallbackManager) {
    LoginManager.getInstance().registerCallback(callbackManager,
        object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                makeToast("Zalogowano przez FB")
            }

            override fun onCancel() {
                makeToast("Anulowano logowanie przez FB")
            }

            override fun onError(error: FacebookException?) {
                makeToast("Błąd logowania przez FB")
            }
        })
}