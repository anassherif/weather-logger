package com.anas.weatherlogger.utils.extensions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anas.weatherlogger.utils.constants.Constants.BUNDLE_DATA


fun <T> AppCompatActivity.startActivityWithBundle(cls: Class<T>, bundle: Bundle) {
    val intent = Intent(this, cls)
    intent.apply {
        putExtra(BUNDLE_DATA, bundle)
    }
    startActivity(intent)
}

fun AppCompatActivity.getBundleData(): Bundle? {
    return this.intent.getBundleExtra(BUNDLE_DATA)
}