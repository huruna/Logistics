package com.example.logistics.Fragment

import News
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logistics.Adapter.NewsAdapter
import com.example.logistics.R

class NewsFragment : Fragment() {

    private lateinit var NewsRecyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private var listener: NewsAdapter.NewsItemListener? = null

    // 더미 뉴스 데이터 생성 (실제 데이터는 API로 통해 가져와야 함)
    val dummyNews = listOf(
        News(
            topic = "환경",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "환경",
            title = "삼성전자, 차별화 제품 개발 위한 미래기술사무국 신설",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "항만",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "항만",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "선박",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "선박",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "물류",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "물류",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "국제",
            title = "메탄올 쓰고, 속도 줄이고…해운·조선 새해 과제는 ‘탈탄소’",
            pubData = "한겨레 2023-01-14",
            link = "https://n.news.naver.com/mnews/article/003/0012007770?sid=101"
        ),
        News(
            topic = "국제",
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NewsRecyclerView = view.findViewById(R.id.newsRecyclerView)
        newsAdapter = NewsAdapter(dummyNews, object : NewsAdapter.NewsItemListener {
            override fun onNewsItemClicked(news: News) {
                listener?.onNewsItemClicked(news)
            }
        })
        NewsRecyclerView.adapter = newsAdapter
        NewsRecyclerView.layoutManager = LinearLayoutManager(context)

        val btnAll = view.findViewById<Button>(R.id.btnAll)
        val btnEnvironment = view.findViewById<Button>(R.id.btnEnvironment)
        val btnPort = view.findViewById<Button>(R.id.btnPort)
        val btnShip = view.findViewById<Button>(R.id.btnShip)
        val btnLogistics = view.findViewById<Button>(R.id.btnLogistics)
        val btnInternational = view.findViewById<Button>(R.id.btnInternational)

        // 버튼 클릭 이벤트 처리
        btnEnvironment.setOnClickListener {
            setButtonColors(
                btnEnvironment,
                listOf(btnPort, btnShip, btnLogistics, btnInternational, btnAll)
            )
            filterNewsByCategory("환경")
        }

        btnPort.setOnClickListener {
            setButtonColors(
                btnPort,
                listOf(btnEnvironment, btnShip, btnLogistics, btnInternational, btnAll)
            )
            filterNewsByCategory("항만")
        }

        btnShip.setOnClickListener {
            setButtonColors(
                btnShip,
                listOf(btnEnvironment, btnPort, btnLogistics, btnInternational, btnAll)
            )
            filterNewsByCategory("선박")
        }

        btnLogistics.setOnClickListener {
            setButtonColors(
                btnLogistics,
                listOf(btnEnvironment, btnPort, btnShip, btnInternational, btnAll)
            )
            filterNewsByCategory("물류")
        }

        btnInternational.setOnClickListener {
            setButtonColors(
                btnInternational,
                listOf(btnEnvironment, btnPort, btnShip, btnLogistics, btnAll)
            )
            filterNewsByCategory("국제")
        }

        btnAll.setOnClickListener {
            setButtonColors(
                btnAll,
                listOf(btnEnvironment, btnPort, btnShip, btnLogistics, btnInternational)
            )
            filterNewsByCategory("전체")
        }

    }


    // 버튼 색 변화
    private fun setButtonColors(selectedButton: Button, otherButtons: List<Button>) {
        selectedButton.setSelected(true)
        for (button in otherButtons) {
            button.setSelected(false)
        }
    }

    // 선택된 분야에 해당하는 뉴스 필터링
    private fun filterNewsByCategory(category: String) {
        val filteredNews = if (category == "전체") {
            dummyNews
        } else {
            dummyNews.filter { it.topic == category }
        }
        newsAdapter.setNewsItems(filteredNews)
    }

}