package com.example.logistics.Adapter

import News
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logistics.R
import java.io.Serializable

class NewsAdapter(private var newsList: List<News>, private var listener: NewsItemListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    interface NewsItemListener {
        fun onNewsItemClicked(news: News)
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val newsTopicTextView: TextView = itemView.findViewById(R.id.newsTopicTextView)
        val newsTitleTextView: TextView = itemView.findViewById(R.id.newsTitleTextView)
        val newsDateTextView: TextView = itemView.findViewById(R.id.newsDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        //holder.newsTopicTextView.text = news.topic
        holder.newsTitleTextView.text = news.title
        holder.newsDateTextView.text = news.pubData

        holder.itemView.setOnClickListener {
            // 프래그먼트 전환을 위한 콜백 호출
            listener.onNewsItemClicked(news)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    // 새로운 뉴스 목록으로 업데이트하는 함수
    fun setNewsItems(newsList: List<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }
}
