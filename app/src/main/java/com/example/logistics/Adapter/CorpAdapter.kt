package com.example.logistics.Adapter

import Company
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logistics.R
import java.io.Serializable

class CorpAdapter(private val companies: List<Company>, private var listener: CorpItemListener) :
    RecyclerView.Adapter<CorpAdapter.CompanyViewHolder>() {

    interface CorpItemListener {
        fun onCorpItemClicked(company: Company)
    }

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val companyNameTextView: TextView = itemView.findViewById(R.id.companyNameTextView)
        val companyTypeTextView: TextView = itemView.findViewById(R.id.companyTypeTextView)
        val companyCategoryTextView: TextView = itemView.findViewById(R.id.companyCategoryTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_corps, parent, false)
        return CompanyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val company = companies[position]
        holder.companyNameTextView.text = company.corp_name
        holder.companyTypeTextView.text = "주식상장법인"
        holder.companyCategoryTextView.text = "카테고리 데이터 아직 없음"

        holder.itemView.setOnClickListener {
            // 프래그먼트 전환을 위한 콜백 호출
            listener.onCorpItemClicked(company)
        }
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    private var filteredList: MutableList<Company> = mutableListOf()

    fun updateCompanyList(filteredCompanies: List<Company>) {
        filteredList.clear()
        filteredList.addAll(filteredCompanies)
        notifyDataSetChanged()
    }
}
