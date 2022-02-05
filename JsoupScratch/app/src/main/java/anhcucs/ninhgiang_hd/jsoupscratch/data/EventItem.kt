package anhcucs.ninhgiang_hd.jsoupscratch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventItem(
    var id: Int = 0,
    val title: String = "",
    val place: String = "",
    val date: String = "",
    val desc: String = "",
    val image: String = "",
    val url: String = ""
) : Parcelable