package com.example.chi_hw_clean_architecture.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chi_hw_clean_architecture.presentation.model.MarvelPresentationModel

@Entity
data class Marvel(
    @PrimaryKey val name: String,
    val realname: String,
    val imageurl: String,
    val bio: String
) {
    fun toUiModel(): MarvelPresentationModel = MarvelPresentationModel(
        name = name,
        realname = realname,
        imageurl = imageurl
    )
}
