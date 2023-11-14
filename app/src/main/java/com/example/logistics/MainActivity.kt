package com.example.logistics

import Company
import News
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.logistics.Adapter.CorpAdapter
import com.example.logistics.Adapter.NewsAdapter
import com.example.logistics.Fragment.CorpFragment
import com.example.logistics.Fragment.HomeFragment
import com.example.logistics.Fragment.NewsFragment
import com.example.logistics.Fragment.NewsInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), NewsAdapter.NewsItemListener, CorpAdapter.CorpItemListener {

    private val homeFragment = HomeFragment()
    private val newsFragment = NewsFragment()
    //private val recruitFragment = RecruitFragment()
    private val corpFragment = CorpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뒤로 가기 버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 네비게이션 바 아이템 선택 리스너 설정
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navigation_home  // Home 아이템을 초기 선택된 아이템으로 설정

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(homeFragment)
                    // 뒤로 가기 버튼 비활성화
                    //supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    true
                }
                R.id.navigation_news -> {
                    replaceFragment(newsFragment)
                    // 뒤로 가기 버튼 활성화
                    //supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    true
                }
                R.id.navigation_chat -> {
                    replaceFragment(corpFragment)
                    // 뒤로 가기 버튼 활성화
                    //supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    true
                }
                // 다른 아이템에 대한 동작 정의...
                else -> false
            }
        }

        // 처음에는 homeFragment를 표시
        replaceFragment(homeFragment)
    }

    // 뒤로 가기 버튼이 선택되었을 때의 동작 정의
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        // 백스택에 프래그먼트가 하나 이상 있으면 백스택에서 프래그먼트를 팝
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportFragmentManager.popBackStack()
        } else {
            // 백스택에 프래그먼트가 없으면 앱을 종료
            super.onBackPressed()
        }
    }

    // 프래그먼트 교체 메서드
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()

        // HomeFragment가 활성화될 때 뒤로 가기 버튼을 비활성화
        if (fragment is HomeFragment) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onNewsItemClicked(news: News) {
        val newFragment = NewsInfoFragment.newInstance(news)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, newFragment).addToBackStack(null).commit()
    }

    override fun onCorpItemClicked(company: Company) {
        val newFragment = CorpInfoFragment.newInstance(company)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, newFragment).addToBackStack(null).commit()
    }

}
