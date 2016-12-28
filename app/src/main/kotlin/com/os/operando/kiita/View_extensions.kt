package com.os.operando.kiita

import android.support.annotation.IdRes
import android.view.View

fun <T : View> View.findView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}