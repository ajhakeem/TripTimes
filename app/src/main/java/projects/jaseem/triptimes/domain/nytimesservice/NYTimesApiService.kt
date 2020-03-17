package projects.jaseem.triptimes.domain.nytimesservice

import io.reactivex.Single
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NYTimesApiService {

    companion object {
        const val searchBaseUrl = "search/v2/"
        const val API_KEY = "CjUOKdhOgnjaBunGKNJt5OAuU2TNJcQd"
    }

    @GET(searchBaseUrl + "articlesearch.json")
    fun searchArticle(
        @Query("q") searchTerm: String,
        @Query("page") page: String,
        @Query("api-key") apiKey: String = API_KEY): Single<SearchResponse>
}