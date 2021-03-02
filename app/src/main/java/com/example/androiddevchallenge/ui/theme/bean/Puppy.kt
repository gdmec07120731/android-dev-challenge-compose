package com.example.androiddevchallenge.ui.theme.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Puppy : Serializable {
    @SerializedName("author")
    var author = ""

    @SerializedName("author_image")
    var authorImage = ""

    @SerializedName("create_time")
    var createTime = ""

    @SerializedName("image")
    var image = ""

    @SerializedName("detail")
    var detail = ""
}