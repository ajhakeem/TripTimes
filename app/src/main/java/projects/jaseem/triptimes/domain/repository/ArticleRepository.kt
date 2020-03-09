package projects.jaseem.triptimes.domain.repository

import io.reactivex.Single
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.domain.ArticleRemoteSource
import projects.jaseem.triptimes.domain.nytimesservice.NYTimesApiService
import javax.inject.Inject
import javax.inject.Singleton


//Singleton because we don't ever need more than one instance of repository
@Singleton
class ArticleRepository
@Inject constructor(private val nyTimesApiService: NYTimesApiService) {

//    If we are using persistence library (Ex: Room), then retrieve local results here and provide a
//    to retrieve locally

//    If using both remote and local data methods, pass in another boolean isForceUpdate to
//    differentiate between request for new data from remote source and local source.
//    I chose not to persist any data for this project because new articles are constantly
//    published, and a user may need search results from a prior search.


    fun searchArticles(
        searchQuery: String,
        page: Int,
        isForceUpdate: Boolean = true): Single<SearchResponse> {
        return nyTimesApiService.searchArticle(searchQuery, page.toString())
    }

}