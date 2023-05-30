package com.matrix.link.ui.password_reset_flow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matrix.link.R

class PasswordResetFlowActivity : AppCompatActivity() , PasswordResetFlowNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset_flow)
    }


    override fun navigateRecoverySelectionScreen() {
        val recoverySelectionFragment = RecoverySelectionFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.forgot_password_fragment_container,recoverySelectionFragment,RecoverySelectionFragment.javaClass.name)
            .commit()
    }

    override fun navigateNewCredentialScreen() {
        val newCredentialFragment = NewCredentialFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.forgot_password_fragment_container,newCredentialFragment,NewCredentialFragment.javaClass.name)
            .commit()
    }

    override fun navigatePasswordUpdateSuccessfulScreen() {
        val passwordUpdateSuccessfulFragment = PasswordUpdateSuccessfulFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.forgot_password_fragment_container,passwordUpdateSuccessfulFragment,PasswordUpdateSuccessfulFragment.javaClass.name)
            .commit()
    }

    override fun navigatePasswordUpdateFailureScreen() {
        val passwordUpdateFailureFragment = PasswordUpdateFailureFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.forgot_password_fragment_container,passwordUpdateFailureFragment,PasswordUpdateFailureFragment.javaClass.name)
            .commit()
    }

}