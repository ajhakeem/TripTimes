package projects.jaseem.triptimes.ui.screens.articledetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_article_detail.*
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.domain.model.ArticleResult
import projects.jaseem.triptimes.extensions.drawable

class ArticleDetailActivity : AppCompatActivity() {

    companion object {

        private const val INTENT_NAME: String = "CLICKED_ARTICLE"

        fun newIntent(context: Context, article: ArticleResult): Intent {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(INTENT_NAME, article)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        intent.getParcelableExtra<ArticleResult>("clickedArticle")?.let {
            updateDetailView(it)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""
    }

    private fun updateDetailView(article: ArticleResult) {
        tvHeadline.text = article.headline

        if (article.thumbnailUrl != null) {
            Glide.with(this)
                .load(article.thumbnailUrl)
                .into(ivCoverImage)
        } else {
            ivCoverImage.setImageDrawable(drawable(R.drawable.ic_ny_times_logo))
        }

        tvPublishedDate.text = article.publishedDateString

        tvLeadParagraph.text = article.leadParagraph
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            overridePendingTransition(R.anim.slide_l_to_r, R.anim.slide_r_to_l)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_l_to_r, R.anim.slide_r_to_l)
    }

}