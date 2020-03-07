package projects.jaseem.triptimes.domain

import io.reactivex.Single
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.domain.nytimesservice.NYTimesApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArticleRemoteSource
@Inject constructor(private val nyTimesApiService: NYTimesApiService) {

    fun getArticles(searchQuery: String, page: Int): Single<SearchResponse> =
        nyTimesApiService.searchArticle(searchQuery, page.toString())

}