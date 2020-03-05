package projects.jaseem.triptimes.ui.screens.articlesearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_article_search.*
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.data.model.ArticleSearchModel
import projects.jaseem.triptimes.state.Resource
import projects.jaseem.triptimes.state.ResourceState
import java.util.*
import kotlin.concurrent.timerTask


class ArticleSearchActivity : AppCompatActivity() {

    lateinit var viewModel: ArticleSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_search)

        viewModel = ViewModelProvider(this).get(ArticleSearchViewModel::class.java)

        viewModel.articleSearchModel.observe(this, Observer {
            updateList(it)
        })

        setQueryListener()
    }

    private fun setQueryListener() {
        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getArticle(it)
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
//                    Show spinner
                }
                ResourceState.SUCCESS -> {
//                    Update rv
                }
                ResourceState.ERROR -> {
//                    Update error
                }
            }
        }
    }

}