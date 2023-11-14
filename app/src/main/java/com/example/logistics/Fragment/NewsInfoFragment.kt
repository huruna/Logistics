package com.example.logistics.Fragment

import News
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.logistics.R

class NewsInfoFragment : Fragment() {
    private var news: News? = null  // News 객체를 프래그먼트의 필드로 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        news = arguments?.getSerializable("news") as News?  // Bundle에서 News 객체 가져오기
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_info, container, false)

        // 뉴스를 각 TextView에 설정
        val newsTitleTextView: TextView = view.findViewById(R.id.newsTitleTextView)
        val newsDateTextView: TextView = view.findViewById(R.id.newsDateTextView)
        val newsLinkTextView: TextView = view.findViewById(R.id.newsLinkTextView)

        news?.let {newsItem ->
            newsTitleTextView.text = newsItem.title
            newsDateTextView.text = newsItem.pubData
            newsLinkTextView.text = newsItem.link
            newsLinkTextView.setOnClickListener {
                openWebPage(newsItem.link)
            }
        }

        return view
    }

    fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    companion object {
        fun newInstance(news: News): NewsInfoFragment {
            val fragment = NewsInfoFragment()
            val args = Bundle().apply { putSerializable("news", news) }
            fragment.arguments = args
            return fragment
        }
    }
}
