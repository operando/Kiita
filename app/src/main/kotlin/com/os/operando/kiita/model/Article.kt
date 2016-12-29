package com.os.operando.kiita.model

import org.parceler.Parcel

@Parcel
data class Article(val id: String, val title: String, val url: String, val user: User) {
    private constructor() : this("", "", "", User())
}