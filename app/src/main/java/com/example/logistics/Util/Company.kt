import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.Serializable

data class Company(
    val id: Int,
    val corp_name: String,
    val ceo_name: String,
    val corp_addr: String,
    val corp_homepage: String,
    val phone_number: String,
    val est_date: String,
    val sales_revenue: String,
    val operating_profit: String
) : Serializable

suspend fun fetchCompanyInfo(corpName: String): Company? {
    val baseUrl = "http://13.125.127.220:8000/api/v1/"
    val endpoint = "get_corp/{corp_name}"
    val url = "$baseUrl$endpoint"

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    return withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            // JSON을 파싱하여 Company 객체로 변환합니다.
            val gson = Gson()
            gson.fromJson(responseBody, Company::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
