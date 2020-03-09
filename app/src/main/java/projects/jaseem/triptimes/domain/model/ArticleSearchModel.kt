package projects.jaseem.triptimes.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.extensions.string
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


data class ArticleSearchModel (
    val searchResults : List<ArticleResult>? = null,
    var searchTerm: String,
    var pagesShowing: Int = 1,
    var isFirstPage: Boolean = true
)

@Parcelize
data class ArticleResult(
    val headline: String,
    val snippet: String,
    val thumbnailUrl: String? = null,
    val publishedDateString: String? = null,
    val publishedDate: Date? = null,
    var author: String,
    var leadParagraph: String,
    var shareLink: String
): Parcelable

fun SearchResponse.toModel(
    searchTerm: String,
    isFirstPage: Boolean,
    pagesShowing: Int) : ArticleSearchModel {
    val results = mutableListOf<ArticleResult>()

    val sdf = SimpleDateFormat("MM/dd/yyyy")

    for (doc in response.docs) {
        var articleThumbnail = ""

        if (doc.multimedia?.isNotEmpty() == true) {
            doc.multimedia.first().let { multimediaResponse ->
                if (multimediaResponse.cropName.toString().contains("article")) {
                    articleThumbnail = string(R.string.thumbnail_prefix, multimediaResponse.url.toString())
                }
            }
        }

        var date: String

        try {
            date = sdf.format(doc.pub_date)
        } catch (p: ParseException) {
            error(p)
        }

        results.add(
            ArticleResult(
                headline = doc.headline.main ?: string(R.string.placeholder_article_headline),
                snippet = doc.snippet,
                thumbnailUrl = articleThumbnail,
                publishedDate = doc.pub_date,
                publishedDateString = date,
                author = doc.byline.original.toString(),
                leadParagraph = doc.lead_paragraph,
                shareLink = doc.webUrl
        ))
    }

    return ArticleSearchModel(
        searchResults = results,
        searchTerm = searchTerm,
        isFirstPage = isFirstPage,
        pagesShowing = pagesShowing
    )
}