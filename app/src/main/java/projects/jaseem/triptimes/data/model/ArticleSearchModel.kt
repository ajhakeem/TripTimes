package projects.jaseem.triptimes.data.model

import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.domain.extensions.drawable


data class ArticleSearchModel (
    val searchResults : List<SearchResult>? = null
)

data class SearchResult(
    val headline: String,
    val snippet: String,
    val thumbnailUrl: String? = null
)

fun SearchResponse.toModel() : ArticleSearchModel {
    val results = mutableListOf<SearchResult>()

    for (doc in response.docs) {
        var articleThumbnail = ""
        doc.multimedia?.first().let { multimediaResponse ->
            if (multimediaResponse?.crop_name.toString().contains("article")) {
                articleThumbnail = "https://www.nytimes.com/" + multimediaResponse?.url.toString()
            }
        }

        results.add(
            SearchResult(
                headline = doc.headline.print_headline ?: "Article headline",
                snippet = doc.snippet,
                thumbnailUrl = articleThumbnail
        ))
    }

    return ArticleSearchModel(
        searchResults = results
    )
}