package com.example.notesapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id          : Int,
    val title       : String,
    val description : String
): Parcelable