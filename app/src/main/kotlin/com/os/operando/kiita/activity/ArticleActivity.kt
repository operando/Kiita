package com.os.operando.kiita.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.os.operando.kiita.R
import com.os.operando.kiita.model.Article
import org.parceler.Parcels

class ArticleActivity : AppCompatActivity() {


    companion object {
        private const val EXTRA_ARTICLE = "article"

        fun createIntent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java)
                        .putExtra(EXTRA_ARTICLE, Parcels.wrap(article))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val a: Article = Parcels.unwrap(intent.getParcelableExtra(EXTRA_ARTICLE))

        val webView = findViewById(R.id.web) as WebView
        webView.settings.javaScriptEnabled = true

        webView.loadUrl(a.url)
    }
}