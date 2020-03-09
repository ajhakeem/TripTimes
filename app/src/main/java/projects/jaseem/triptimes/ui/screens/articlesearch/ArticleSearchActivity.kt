package projects.jaseem.triptimes.ui.screens.articlesearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_article_search.*
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.domain.model.ArticleResult
import projects.jaseem.triptimes.domain.model.ArticleSearchModel
import projects.jaseem.triptimes.extensions.string
import projects.jaseem.triptimes.state.Resource
import projects.jaseem.triptimes.state.ResourceState
import projects.jaseem.triptimes.ui.screens.articledetail.ArticleDetailActivity
import javax.inject.Inject


class ArticleSearchActivity :
    AppCompatActivity(),
    OnBottomReachedListener,
    OnArticleClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ArticleSearchViewModel
    private lateinit var searchAdapter: ArticleSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_search)

        rvArticles.layoutManager = LinearLayoutManager(this)
        searchAdapter = ArticleSearchAdapter(this, listOf(), this, this)
        rvArticles.adapter = searchAdapter

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArticleSearchViewModel::class.java)

        viewModel.articleSearchModel.observe(this, Observer {
            updateList(it)
        })

        setQueryListener()
    }

    override fun onResume() {
        super.onResume()
        svSearch.clearFocus()
    }

    private fun setQueryListener() {
        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getArticle(it, 1)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun updateList(resource: Resource<ArticleSearchModel>?) {
        resource?.let { res ->
            when (res.state) {
                ResourceState.LOADING -> {
                    pbLoading.visibility = View.VISIBLE
                    layoutEmptyState.visibility = View.GONE
                }
                ResourceState.SUCCESS -> {
                    pbLoading.visibility = View.GONE
                    gSearchResults.visibility = View.VISIBLE
                    res.data?.searchResults?.let {
                        tvShowingResultsFor.text =
                            string(R.string.showing_results_for, res.data.searchTerm)
                        tvShowingResultsFor.visibility = View.VISIBLE
                        if (res.data.isFirstPage) {
                            searchAdapter.updateItems(it)
                        } else {
                            searchAdapter.addItems(it)
                        }
                    }
                }
                ResourceState.ERROR -> {
                    pbLoading.visibility = View.GONE
//                    Update error
                }
            }
        }
    }

    override fun onBottomReached(pos: Int) {
        viewModel.articleSearchModel.value?.data?.let { model ->
            viewModel.getArticle(model.searchTerm, model.pagesShowing + 1)
        }
    }

    override fun onArticleClicked(article: ArticleResult) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra(ArticleDetailActivity.CLICKED_ARTICLE, article)

        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }
}