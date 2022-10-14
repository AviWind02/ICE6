package com.example.lab.one.ice6

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactModel(
    val FullName: String,
    val ContactNumber: String,
    val EmailAddress: String)