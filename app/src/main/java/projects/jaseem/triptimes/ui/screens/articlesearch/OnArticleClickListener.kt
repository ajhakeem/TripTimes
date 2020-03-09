package projects.jaseem.triptimes.ui.screens.articlesearch

import projects.jaseem.triptimes.domain.model.ArticleResult


interface OnArticleClickListener {

    fun onArticleClicked(article: ArticleResult)

}