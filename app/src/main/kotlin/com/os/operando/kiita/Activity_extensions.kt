package com.os.operando.kiita

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View
import android.widget.Toast

fun <T : View> Activity.findView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
