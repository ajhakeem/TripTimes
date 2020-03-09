package projects.jaseem.triptimes.ui.screens.articledetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_article_detail.*
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.domain.model.ArticleResult
import projects.jaseem.triptimes.extensions.drawable

class ArticleDetailActivity : AppCompatActivity() {

    companion object {
        const val CLICKED_ARTICLE: String = "CLICKED_ARTICLE"
//        const val SHARE_INTENT_EXTRA: String = "SHARE_INTENT_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        intent.getParcelableExtra<ArticleResult>(CLICKED_ARTICLE)?.let {
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
        tvAuthor.text = article.author

        tvLeadParagraph.text = article.leadParagraph
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.slide_l_to_r, R.anim.slide_r_to_l)
                return true
            }
            R.id.shareButton -> {
                intent.getParcelableExtra<ArticleResult>(CLICKED_ARTICLE)?.let {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, it.shareLink)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(intent, "Share link using ")
                    shareIntent.resolveActivity(packageManager)?.let {
                        startActivity(shareIntent)
                    }
                }

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_l_to_r, R.anim.slide_r_to_l)
    }

}