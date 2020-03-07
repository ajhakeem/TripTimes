package projects.jaseem.triptimes.ui.screens.articlesearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import projects.jaseem.triptimes.state.Resource
import projects.jaseem.triptimes.domain.model.ArticleSearchModel
import projects.jaseem.triptimes.domain.model.toModel
import projects.jaseem.triptimes.domain.repository.ArticleRepository
import projects.jaseem.triptimes.extensions.setError
import projects.jaseem.triptimes.extensions.setLoading
import projects.jaseem.triptimes.extensions.setSuccess
import javax.inject.Inject


class ArticleSearchViewModel
@Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    val articleSearchModel = MutableLiveData<Resource<ArticleSearchModel>>()
    private val disposable = CompositeDisposable()

    fun getArticle(searchTerm: String, page: Int) {
        disposable.add(
            articleRepository.searchArticles(searchTerm, page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    articleSearchModel.setLoading()
                }
                .map { response ->
                    response.toModel(searchTerm, page == 1, page)
                }
                .subscribe({
                    articleSearchModel.setSuccess(it)
                    }, {
                    articleSearchModel.setError(it.message)
                })
        )
    }

}