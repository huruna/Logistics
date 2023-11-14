package com.example.logistics.Fragment

import Company
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logistics.Adapter.CorpAdapter
import com.example.logistics.Api.CorpApiService
import com.example.logistics.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class CorpFragment : Fragment() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.127.220:8000/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val corpApiService = retrofit.create(CorpApiService::class.java)

    private lateinit var corpRecyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var corpAdapter: CorpAdapter
    private var listener: CorpAdapter.CorpItemListener? = null

    val dummyCompanies = listOf(
        Company(
            id = 1,
            corp_name = "HMM",
            ceo_name = "김경배",
            corp_addr = "서울특별시 영등포구 여의대로 108 (여의도동, 파크원 타워1)",
            corp_homepage = "www.hmm21.com",
            phone_number = "02-3706-5114",
            est_date = "19760325",
            sales_revenue = "185827.7 억",
            operating_profit = "99515.6 억"
        ),
        Company(
            id = 2,
            corp_name = "Company B",
            ceo_name = "CEO B",
            corp_addr = "Address B",
            corp_homepage = "www.companyB.com",
            phone_number = "987-654-3210",
            est_date = "2023-02-01",
            sales_revenue = "200 million",
            operating_profit = "80 million"
        ),
        Company(
            id = 3,
            corp_name = "Company C",
            ceo_name = "CEO C",
            corp_addr = "Address C",
            corp_homepage = "www.companyC.com",
            phone_number = "987-654-3210",
            est_date = "2023-02-01",
            sales_revenue = "200 million",
            operating_profit = "80 million"
        ),
        Company(
            id = 4,
            corp_name = "Company D",
            ceo_name = "CEO D",
            corp_addr = "Address D",
            corp_homepage = "www.companyC.com",
            phone_number = "987-654-3210",
            est_date = "2023-02-01",
            sales_revenue = "200 million",
            operating_profit = "80 million"
        ),
        Company(
            id = 5,
            corp_name = "Company E",
            ceo_name = "CEO E",
            corp_addr = "Address E",
            corp_homepage = "www.companyC.com",
            phone_number = "987-654-3210",
            est_date = "2023-02-01",
            sales_revenue = "200 million",
            operating_profit = "80 million"
        ),
        // 추가 더미 데이터
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 액티비티가 NewsItemListener 인터페이스를 구현하고 있는지 확인하고,
        // 그렇다면 이를 listener 변수에 저장합니다.
        if (context is CorpAdapter.CorpItemListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement NewsItemListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        // 프래그먼트가 액티비티에서 해제되는 경우, listener를 null로 설정
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_corp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        corpRecyclerView = view.findViewById(R.id.corpRecyclerView)
        searchEditText = view.findViewById(R.id.searchEditText)

        corpAdapter = CorpAdapter(dummyCompanies, object : CorpAdapter.CorpItemListener {
            override fun onCorpItemClicked(company: Company) {
                listener?.onCorpItemClicked(company)
            }
        })
        corpRecyclerView.adapter = corpAdapter
        corpRecyclerView.layoutManager = LinearLayoutManager(context)

        /*
        // API를 통해 회사 목록 가져오기
        val call = companyApiService.getAllCompanies()
        call.enqueue(object : Callback<List<Company>> {
            override fun onResponse(call: Call<List<Company>>, response: Response<List<Company>>) {
                if (response.isSuccessful) {
                    val companies = response.body()
                    if (companies != null) {
                        recyclerView.adapter = CompanyAdapter(companies)
                        //adapter.updateCompanyList(companies)
                        //adapter.notifyDataSetChanged()
                    } else {
                        adapter.updateCompanyList(emptyList())
                    }
                } else {
                    // 응답이 실패한 경우의 처리
                    Log.e("API", "회사목록 API Call Failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Company>>, t: Throwable) {
                // API 호출 실패 처리
                Log.e("API", "API Call Error: ${t.message}")
            }
        })
        */

        val searchButton = view.findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {

            val searchText = searchEditText.text.toString().trim()

            if (searchText.isNotEmpty()) {
                val call = corpApiService.getCompanyInfo(searchText)
                call.enqueue(object : Callback<Company> {
                    override fun onResponse(call: Call<Company>, response: Response<Company>) {
                        if (response.isSuccessful) {
                            val company = response.body()
                            if (company != null) {
                                corpRecyclerView.adapter = CorpAdapter(listOf(company), object : CorpAdapter.CorpItemListener {
                                    override fun onCorpItemClicked(company: Company) {
                                        listener?.onCorpItemClicked(company)
                                    }
                                })
                            } else {
                                corpAdapter.updateCompanyList(emptyList())
                            }
                        } else {
                            // 응답이 실패한 경우의 처리
                            Log.e("API", "검색기능 API Call Failed: ${response.code()}")
                        }
                    }
                    // API 요청 자체가 실패했을 때
                    override fun onFailure(call: Call<Company>, t: Throwable) {
                        Log.e("API", "API Call Failed: ${t.message}")
                    }
                })
            } else {
                corpAdapter.updateCompanyList(emptyList())
            }
        }

    }
}