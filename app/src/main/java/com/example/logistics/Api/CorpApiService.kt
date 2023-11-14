package com.example.logistics.Api

import Company
import retrofit2.http.GET
import retrofit2.http.Path

interface CorpApiService {
    @GET("get_corp/{corp_name}")
    fun getCompanyInfo(@Path("corp_name") corpName: String): retrofit2.Call<Company>

    @GET("get_corp_name_list")
    fun getAllCompanies(): retrofit2.Call<List<Company>>
}