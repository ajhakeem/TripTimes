package projects.jaseem.triptimes.ui.screens.articlesearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import projects.jaseem.triptimes.state.Resource
import projects.jaseem.triptimes.domain.model.ArticleSearchModel
import projects.jaseem.triptimes.domain.usecase.SearchArticlesUseCase
import projects.jaseem.triptimes.extensions.setError
import projects.jaseem.triptimes.extensions.setLoading
import projects.jaseem.triptimes.extensions.setSuccess
import javax.inject.Inject


class ArticleSearchViewModel
@Inject constructor(
    private val searchArticlesUseCase: SearchArticlesUseCase
) : ViewModel() {

    val articleSearchModelLiveData = MutableLiveData<Resource<ArticleSearchModel>>()
    private val disposable = CompositeDisposable()

    fun getArticle(searchTerm: String, page: Int, isForceUpdate: Boolean) {
        disposable.add(
            searchArticlesUseCase.execute(searchTerm, page, isForceUpdate)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    articleSearchModelLiveData.setLoading()
                }
                .subscribe({
                    articleSearchModelLiveData.setSuccess(it)
                    }, {
                    // Parse error here for codes and determine message
                    // Log/send error if needed
                    articleSearchModelLiveData.setError(it.message)
                })
        )
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }

}