import java.io.Serializable

data class News(
    val topic: String,
    val title: String,
    val pubData: String,
    val link: String,
    //val imageUrl: String,

) : Serializable