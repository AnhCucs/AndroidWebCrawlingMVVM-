package anhcucs.ninhgiang_hd.jsoupscratch.repository

import anhcucs.ninhgiang_hd.jsoupscratch.data.EventDetail
import anhcucs.ninhgiang_hd.jsoupscratch.data.EventItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import java.io.IOException
import javax.inject.Inject

class EventRepository @Inject constructor() {
    private val url = "https://www.eventfinda.co.nz/whatson/events/new-zealand"
    private val BASE_URL = "https://www.eventfinda.co.nz"
    fun getEvents(): Flow<MutableList<EventItem>> = flow {
        val listData = mutableListOf<EventItem>()
        try {
            val doc = Jsoup.connect(url).get()
            val events = doc.select("div.card.h-event.vevent.h-card")

            val sizeEvents = events.size
            for (i in 0 until sizeEvents) {
                val title = events.select("a.url.summary")
                    .eq(i)
                    .text()
                val place = events.select("a.location")
                    .eq(i)
                    .text()
                val date = events.select("span.value-title")
                    .eq(i)
                    .text()
                val desc = events.select("p.teaser.description")
                    .eq(i)
                    .text()
                val eventUrl = BASE_URL + events.select("div.card-body")
                        .select("a")
                        .eq(i)
                        .attr("href")
                val image = events.select("a.card-image")
                    .select("img")
                    .eq(i)
                    .attr("data-src")
                listData.add(EventItem(i, title, place, date, desc, image, eventUrl))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        emit(listData)
    }.flowOn(Dispatchers.IO)

   suspend fun getEvenDetails(url:String): Flow<EventDetail> = flow {
        val itemDetail = EventDetail()
        try {
            val document = Jsoup.connect(url).get()
            val details = document.select("div.module.description")
                .select("p")
                .eq(1)
                .text()
            val ticketName = document.select("strong.ticket-name")
                .text()
            val ticketPrice = document.select("span.ticket-price")
                .text()

            val ticketInfo = "$ticketName: $ticketPrice"
            itemDetail.detail = details
            itemDetail.ticketInfo = ticketInfo
            emit(itemDetail)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)
}