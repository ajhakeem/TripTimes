package projects.jaseem.triptimes.ui.screens.articlesearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import projects.jaseem.triptimes.state.Resource
import projects.jaseem.triptimes.data.model.ArticleSearchModel
import projects.jaseem.triptimes.data.model.toModel
import projects.jaseem.triptimes.domain.RestApi
import projects.jaseem.triptimes.domain.extensions.setError
import projects.jaseem.triptimes.domain.extensions.setLoading
import projects.jaseem.triptimes.domain.extensions.setSuccess


class ArticleSearchViewModel
    : ViewModel() {

    val articleSearchModel = MutableLiveData<Resource<ArticleSearchModel>>()
    val disposable = CompositeDisposable()

    fun getArticle(searchTerm: String) {
        disposable.add(
            RestApi.nyTimesClient.searchArticle(searchTerm)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    articleSearchModel.setLoading()
                }
                .map { response ->
                    response.toModel()
                }
                .subscribe({
                    articleSearchModel.setSuccess(it)
                    }, {
                    articleSearchModel.setError(it.message)
                })
        )
    }

}