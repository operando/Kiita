package com.os.operando.kiita.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.os.operando.kiita.R
import com.os.operando.kiita.adapter.ArticleListAdapter
import com.os.operando.kiita.api.ArticleClient
import com.os.operando.kiita.findView
import com.os.operando.kiita.model.Article
import com.os.operando.kiita.toast
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val rootView: View by findView(R.id.activity_main)
    val search: Button by findView(R.id.search)
    val list: ListView by findView(R.id.list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("tag", "$rootView")

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val client = retrofit.create(ArticleClient::class.java)

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
        }
    }
}
