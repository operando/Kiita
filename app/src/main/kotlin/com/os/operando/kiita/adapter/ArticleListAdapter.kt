package com.os.operando.kiita.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.os.operando.kiita.R
import com.os.operando.kiita.model.Article
import com.squareup.picasso.Picasso

class ArticleListAdapter(context: Context, episodeList: List<Article>) : BindableAdapterKt<Article>(context, episodeList) {

    override fun newView(inflater: LayoutInflater, position: Int, container: ViewGroup?): View? {
        val v = inflater.inflate(R.layout.row_article_item, null)
        v.tag = ViewHolder(v)
        return v
    }

    override fun bindView(item: Article, position: Int, view: View) {
        val vh = view.tag as? ViewHolder
        vh?.let {
            Picasso.with(context)
                    .load(item.user.profileImageUrl)
                    .into(vh.image)
            vh.title.text = item.title
            vh.name.text = item.user.name
        }
    }

    class ViewHolder(view: View) {
        val image = view.findViewById(R.id.image) as ImageView
        val title = view.findViewById(R.id.title) as TextView
        val name = view.findViewById(R.id.user_name) as TextView
    }
}