package com.os.operando.kiita.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.os.operando.kiita.*
import com.os.operando.kiita.adapter.ArticleListAdapter
import com.os.operando.kiita.api.ArticleClient
import com.os.operando.kiita.model.Article
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val subscriptions = CompositeSubscription()

    val rootView: View by findView(R.id.activity_main)
    val search: Button by findView(R.id.search)
    val list: ListView by findView(R.id.list)

    @Inject
    lateinit var client: ArticleClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as KiitaApplication).component.inject(this)

        Log.d("tag", "$rootView")

        search.setOnClickListener {
            client.search("user:operandoOS")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d("tag", it.toString())

                        list.adapter = ArticleListAdapter(this, it)
                        list.setOnItemClickListener { adapterView, view, position, l ->
                            val a = adapterView.getItemAtPosition(position) as Article
                            ArticleActivity.createIntent(this, a).let {
                                startActivity(it)
                            }
                        }
                    }, {
                        it.message?.let { toast(it) }
                    })
                    .addTo(subscriptions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.unsubscribe()
    }
}
