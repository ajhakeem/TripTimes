package projects.jaseem.triptimes.domain.usecase

import io.reactivex.Single
import projects.jaseem.triptimes.data.response.searchresponse.SearchResponse
import projects.jaseem.triptimes.domain.model.ArticleSearchModel
import projects.jaseem.triptimes.domain.model.toModel
import projects.jaseem.triptimes.domain.repository.ArticleRepository
import javax.inject.Inject


class SearchArticlesUseCase
@Inject constructor(private val articleRepository: ArticleRepository) {

    fun execute(query: String, page: Int, isForceUpdate: Boolean): Single<ArticleSearchModel> {
        return articleRepository.searchArticles(query, page, isForceUpdate).map {
            it.toModel(query, page == 1, page)
        }
    }

}