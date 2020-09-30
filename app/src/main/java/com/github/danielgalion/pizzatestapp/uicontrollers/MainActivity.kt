package com.github.danielgalion.pizzatestapp.uicontrollers

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.github.danielgalion.pizzatestapp.R
import com.github.danielgalion.pizzatestapp.uicontrollers.recipe.RecipeActivity
import com.github.danielgalion.pizzatestapp.utils.makeToast
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    companion object {
        private const val RC_WRITE = 9001
    }

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleLoginManagerCallback()
        checkPermissions()
        showLogo()
        title = "RecipeMaster"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleLoginManagerCallback() {
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

    private fun checkPermissions() {
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(
                this,
                "Aby zapisywać zdjęcia potrzebne są uprawnienia dostępu do pamięci",
                RC_WRITE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun showLogo() {
        logo_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pizza1))
    }

    fun onRecipeFabClick(view: View) {
        startActivity(Intent(this, RecipeActivity::class.java))
    }

    fun onFbFabClick(view: View) {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
    }
}