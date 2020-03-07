package projects.jaseem.triptimes.domain.repository

import io.reactivex.Single
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.domain.ArticleRemoteSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArticleRepository
@Inject constructor(private val remoteSource: ArticleRemoteSource) {

    fun searchArticles(searchQuery: String, page: Int): Single<SearchResponse> {
        return remoteSource.getArticles(searchQuery, page)
    }

}