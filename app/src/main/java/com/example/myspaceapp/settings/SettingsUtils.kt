package com.example.myspaceapp.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.myspaceapp.MainActivity
import com.example.myspaceapp.R
import com.example.myspaceapp.services.FirebaseService
import com.google.firebase.auth.FirebaseAuth

private lateinit var firebaseService: FirebaseService
private var user = FirebaseAuth.getInstance()
private val email = user.currentUser?.email

@SuppressLint("StaticFieldLeak")
private val context = MainActivity().applicationContext

fun changePasswordRequest(): Boolean {
    val email = user.currentUser?.email
    if (email != null) {
        user.sendPasswordResetEmail(email).addOnCompleteListener {
            //if (task.isSuccessful) {
            Toast.makeText(
                context,
                R.string.reset_password_email,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    return firebaseService.changePassword(email.toString())
}

@SuppressLint("IntentReset")
fun sendMailToSupportTeam(message: String) {
    message.trim()
    if (message.isEmpty()) {
        Toast.makeText(context, R.string.plain_text, Toast.LENGTH_SHORT).show()
    } else {
        user.currentUser?.email?.split(",".toRegex())?.toTypedArray()
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_CC, R.string.support_email)
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.subject_message_support_team)
        intent.putExtra(Intent.EXTRA_TEXT, message)
    }
    //return firebaseService.changePassword(email.toString())
}