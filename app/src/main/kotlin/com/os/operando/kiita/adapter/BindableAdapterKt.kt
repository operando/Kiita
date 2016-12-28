package com.os.operando.kiita.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

abstract class BindableAdapterKt<T>(context: Context?, episodeList: List<T>) : ArrayAdapter<T>(context, -1, episodeList) {

    private val inflater by lazy {
        LayoutInflater.from(context)
    };

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        val newView = view ?: newView(inflater, position, parent)
        newView ?: throw IllegalStateException("newView result must not be null.")
        bindView(getItem(position), position, newView)
        return newView
    }

    abstract fun newView(inflater: LayoutInflater, position: Int, container: ViewGroup?): View?

    abstract fun bindView(item: T, position: Int, view: View)
}