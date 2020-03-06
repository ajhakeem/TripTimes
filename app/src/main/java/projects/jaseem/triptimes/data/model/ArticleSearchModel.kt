package projects.jaseem.triptimes.data.model

import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.domain.extensions.drawable
import java.text.SimpleDateFormat
import java.util.*


data class ArticleSearchModel (
    val searchResults : List<SearchResult>? = null
)

data class SearchResult(
    val headline: String,
    val snippet: String,
    val thumbnailUrl: String? = null,
    val publishedDate: String? = null
)

fun SearchResponse.toModel() : ArticleSearchModel {
    val results = mutableListOf<SearchResult>()

    val sdf = SimpleDateFormat("MM/dd/yyyy")

    for (doc in response.docs) {
        var articleThumbnail = ""
        doc.multimedia?.first().let { multimediaResponse ->
            if (multimediaResponse?.crop_name.toString().contains("article")) {
                articleThumbnail = "https://www.nytimes.com/" + multimediaResponse?.url.toString()
            }
        }

//        val date = sdf.format(doc.pub_date)
//        val publishedDateString = "Published on " + date

        results.add(
            SearchResult(
                headline = doc.headline.main ?: "Article headline",
                snippet = doc.snippet,
                thumbnailUrl = articleThumbnail,
                publishedDate = ""
        ))
    }

    return ArticleSearchModel(
        searchResults = results
    )
}