package projects.jaseem.triptimes.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import projects.jaseem.triptimes.ui.screens.articledetail.ArticleDetailActivity
import projects.jaseem.triptimes.ui.screens.articlesearch.ArticleSearchActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeArticleSearchActivity(): ArticleSearchActivity

    @ContributesAndroidInjector
    internal abstract fun contributeArticleDetailActivity(): ArticleDetailActivity

}