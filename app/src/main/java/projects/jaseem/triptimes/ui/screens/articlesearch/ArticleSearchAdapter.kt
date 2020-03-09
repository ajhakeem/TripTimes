package projects.jaseem.triptimes.ui.screens.articlesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article.view.*
import projects.jaseem.triptimes.R
import projects.jaseem.triptimes.domain.model.ArticleResult
import projects.jaseem.triptimes.extensions.drawable


class ArticleSearchAdapter(
    private val context: Context,
    private val articleList: List<ArticleResult>,
    private val onBottomReachedListener: OnBottomReachedListener,
    private val articleClickListener: OnArticleClickListener
) : RecyclerView.Adapter<ArticleSearchAdapter.ArticleViewHolder>() {

    var items: MutableList<ArticleResult> = this.articleList.toMutableList()
    var bottomReachedListener = this.onBottomReachedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<ArticleResult>) {
        newItems.sortedBy { it.publishedDate }
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(itemsToAdd: List<ArticleResult>) {
        items.addAll(items.size, itemsToAdd)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if (position > 1 && position == items.size - 1) {
            bottomReachedListener.onBottomReached(position)
        }

        val article = items[position]
        holder.apply {
            tvTitle.text = article.headline
            tvPublishedDate.text = article.publishedDateString

            if (article.thumbnailUrl != null) {
                Glide.with(context)
                    .load(article.thumbnailUrl)
                    .into(ivThumbnail)
            } else {
                ivThumbnail.setImageDrawable(drawable(R.drawable.ic_ny_times_logo))
            }

            clArticle.setOnClickListener {
                articleClickListener.onArticleClicked(article)
            }
        }
    }

    class ArticleViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val tvPublishedDate: TextView = view.tvPublishedDate
        val ivThumbnail: AppCompatImageView = view.ivThumbnail
        val clArticle: ConstraintLayout = view.clArticle
    }

}