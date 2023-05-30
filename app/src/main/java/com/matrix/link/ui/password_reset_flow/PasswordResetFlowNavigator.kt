package com.matrix.link.ui.password_reset_flow

interface PasswordResetFlowNavigator {

    fun navigateRecoverySelectionScreen()

    fun navigateNewCredentialScreen()

    fun navigatePasswordUpdateSuccessfulScreen()

    fun navigatePasswordUpdateFailureScreen()
}