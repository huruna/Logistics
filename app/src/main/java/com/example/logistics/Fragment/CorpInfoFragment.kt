import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.logistics.R

class CorpInfoFragment : Fragment() {

    private lateinit var company: Company

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_corp_info, container, false)

        // MainActivity에서 전달한 intent에서 기업 정보 가져오기
        company = arguments?.getSerializable("company") as Company

        // 액션바 설정
        activity?.actionBar?.apply {
            title = "기업 정보"
            setDisplayHomeAsUpEnabled(true) // 뒤로 가기 버튼 활성화
        }

        // 기업 정보를 각 TextView에 설정
        val companyNameTextView: TextView = view.findViewById(R.id.companyNameTextView)
        val companyTypeTextView: TextView = view.findViewById(R.id.companyTypeTextView)
        val ceoNameTextView: TextView = view.findViewById(R.id.ceoNameTextView)
        val companyCategoryTextView: TextView = view.findViewById(R.id.companyCategoryTextView)
        val companyHomePgTextView: TextView = view.findViewById(R.id.companyHomePgTextView)
        val salesRevenueTextView: TextView = view.findViewById(R.id.salesRevenueTextView)
        val operatingProfitTextView: TextView = view.findViewById(R.id.operatingProfitTextView)

        companyNameTextView.text = company.corp_name
        companyTypeTextView.text = "주식상장법인              "
        ceoNameTextView.text = "${company.ceo_name}"
        companyCategoryTextView.text = "카테고리 데이터 없음"
        companyHomePgTextView.text = "https://${company.corp_homepage}"
        companyHomePgTextView.setOnClickListener {
            val homepageUrl = "https://"+company.corp_homepage
            openWebPage(homepageUrl)
        }
        salesRevenueTextView.text = "${company.sales_revenue}"
        operatingProfitTextView.text = "${company.operating_profit}"

        return view
    }

    private fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    companion object {
        fun newInstance(company: Company): CorpInfoFragment {
            val fragment = CorpInfoFragment()
            val args = Bundle().apply { putSerializable("company", company) }
            fragment.arguments = args
            return fragment
        }
    }

}
