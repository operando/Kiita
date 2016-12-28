package com.os.operando.kiita

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

fun <T : View> Activity.findView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}