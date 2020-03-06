package projects.jaseem.triptimes.ui.screens.articlesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article.view.*
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.data.model.SearchResult
import projects.jaseem.triptimes.domain.extensions.drawable


class ArticleSearchAdapter(
    private val articleList: List<SearchResult>,
    private val context: Context
) : RecyclerView.Adapter<ArticleSearchAdapter.ArticleViewHolder>() {

    var items: List<SearchResult> = this.articleList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<SearchResult>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = items[position]
        holder.apply {
            tvTitle.text = article.headline
//            tvSnippet.text = article.snippet
//            tvPublishedDate.text = article.publishedDate

            if (article.thumbnailUrl != null) {
                Glide.with(context)
                    .load(article.thumbnailUrl)
                    .into(ivThumbnail)
            } else {
                ivThumbnail.setImageDrawable(drawable(R.drawable.ic_ny_times_logo))
            }
        }
    }

    class ArticleViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
//        val tvSnippet: TextView = view.tvSnippet
        val tvPublishedDate: TextView = view.tvPublishedDate
        val ivThumbnail: AppCompatImageView = view.ivThumbnail
    }

}