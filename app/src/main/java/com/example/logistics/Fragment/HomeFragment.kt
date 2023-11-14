package com.example.logistics.Fragment

import News
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logistics.Adapter.HomeAdapter
import com.example.logistics.Adapter.NewsAdapter
import com.example.logistics.R

class HomeFragment : Fragment() {

    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private var listener: NewsAdapter.NewsItemListener? = null

    // 가상의 뉴스 목록 생성
    val dummyNews = listOf(
        News(
            topic = "환경",
            title = "스마트 해상물류 유니-콘테스트 참가 기업 선정",
            pubData = "울산신문 2023-06-11",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "환경",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "환경",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 액티비티가 NewsItemListener 인터페이스를 구현하고 있는지 확인하고,
        // 그렇다면 이를 listener 변수에 저장합니다.
        if (context is NewsAdapter.NewsItemListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement NewsItemListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        // 프래그먼트가 액티비티에서 해제되는 경우, listener를 null로 설정합니다.
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 초기화 및 설정
        homeRecyclerView = view.findViewById(R.id.homeRecyclerView)
        homeAdapter = HomeAdapter(dummyNews, object : NewsAdapter.NewsItemListener {
            override fun onNewsItemClicked(news: News) {
                listener?.onNewsItemClicked(news)
            }
        })
        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}
