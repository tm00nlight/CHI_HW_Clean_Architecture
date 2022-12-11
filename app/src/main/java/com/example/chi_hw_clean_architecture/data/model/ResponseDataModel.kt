package com.example.chi_hw_clean_architecture.data.model

data class ResponseDataModel(
    val name: String?,
    val realname: String?,
    val team: String?,
    val firstappearance: String?,
    val createdby: String?,
    val publisher: String?,
    val imageurl: String?,
    val bio: String?
) {
    fun toDbModel(): Marvel = Marvel(
        name = name.orEmpty(),
        realname = realname.orEmpty(),
        imageurl = imageurl.orEmpty(),
        bio = bio.orEmpty()
    )
}