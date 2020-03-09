package projects.jaseem.triptimes.data.response.searchresponse

import com.google.gson.annotations.SerializedName
import java.util.*


data class SearchResponse(
    val status: String,
    val copyright: String,
    @SerializedName("response")
    val response: ResponseResponse
) {

    class ResponseResponse(
        @SerializedName("docs")
        val docs: List<DocsResponse>
    )

    class DocsResponse(
        @SerializedName("snippet")
        val snippet: String,
        val lead_paragraph: String,
        @SerializedName("multimedia")
        val multimedia: List<MultimediaResponse>? = null,
        @SerializedName("headline")
        val headline: HeadlineResponse,
        @SerializedName("pub_date")
        val pub_date: Date,
        @SerializedName("byline")
        val byline: BylineResponse,
        @SerializedName("web_url")
        val webUrl: String
    )

    class MultimediaResponse(
        @SerializedName("crop_name")
        val cropName: String? = null,
        @SerializedName("url")
        val url: String? = null
    )

    class HeadlineResponse(
        @SerializedName("main")
        val main: String? = null
    )

    class BylineResponse(
        @SerializedName("original")
        val original: String? = null
    )

}

