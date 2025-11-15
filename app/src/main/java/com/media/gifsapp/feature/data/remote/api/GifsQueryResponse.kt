/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.remote.api

import com.google.gson.annotations.SerializedName

data class GifsQueryResponse(
    @SerializedName("data")
    val data: ArrayList<Data> = arrayListOf(),
    @SerializedName("meta")
    val meta: Meta? = Meta(),
    @SerializedName("pagination")
    val pagination: Pagination? = Pagination()
)

data class Data(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("content_url")
    val contentUrl: String? = null,
    @SerializedName("is_sticker")
    val isSticker: Int? = null,
    @SerializedName("import_datetime")
    val importDatetime: String? = null,
    @SerializedName("trending_datetime")
    val trendingDatetime: String? = null,
    @SerializedName("alt_text")
    val altText: String? = null,
    @SerializedName("is_low_contrast")
    val isLowContrast: Boolean? = null,
    @SerializedName("images")
    val images: Images? = Images(),
)

data class Images(
    @SerializedName("original")
    val original: Original? = Original(),
    @SerializedName("fixed_height_small")
    val fixedHeightSmall: FixedHeightSmall? = FixedHeightSmall()
)

data class FixedHeightSmall(
    @SerializedName("height")
    val height: String? = null,
    @SerializedName("width")
    val width: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("webp")
    val webp: String? = null
)

data class Original(
    @SerializedName("height")
    val height: String? = null,
    @SerializedName("width")
    val width: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("webp")
    val webp: String? = null,
    @SerializedName("hash")
    val hash: String? = null
)

data class Meta(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("msg")
    val msg: String? = null,
    @SerializedName("response_id")
    val responseId: String? = null
)

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null
)