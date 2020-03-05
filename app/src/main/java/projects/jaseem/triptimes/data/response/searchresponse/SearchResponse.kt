package projects.jaseem.triptimes.data.response.searchresponse

import com.google.gson.annotations.SerializedName


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
        val web_url: String,
        @SerializedName("snippet")
        val snippet: String,
        val lead_paragraph: String,
        val source: String,
        @SerializedName("multimedia")
        val multimedia: List<MultimediaResponse>? = null,
        @SerializedName("headline")
        val headline: HeadlineResponse,
        val pub_date: String
    )

    class MultimediaResponse(
        @SerializedName("crop_name")
        val crop_name: String? = null,
        @SerializedName("url")
        val url: String? = null
    )

    class HeadlineResponse(
        val main: String? = null,
        val print_headline: String? = null
    )

}

