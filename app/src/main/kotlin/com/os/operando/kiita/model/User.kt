package com.os.operando.kiita.model

import org.parceler.Parcel

@Parcel
data class User(val id: String, val name: String, val profileImageUrl: String) {
    constructor() : this("", "", "")
}