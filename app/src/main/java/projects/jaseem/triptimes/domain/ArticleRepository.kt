package projects.jaseem.triptimes.domain

import io.reactivex.Single
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArticleRepository
@Inject constructor(private val remoteSource: ArticleRemoteSource) {

    fun searchArticles(searchQuery: String): Single<SearchResponse> {
        return remoteSource.getArticles(searchQuery)
    }

}