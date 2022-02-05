package anhcucs.ninhgiang_hd.jsoupscratch.viewmodel

import androidx.lifecycle.*
import anhcucs.ninhgiang_hd.jsoupscratch.data.EventDetail
import anhcucs.ninhgiang_hd.jsoupscratch.data.EventItem
import anhcucs.ninhgiang_hd.jsoupscratch.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private val _eventItem = eventRepository.getEvents()
        .map {
            it
        }.asLiveData(viewModelScope.coroutineContext)

    val eventItem: LiveData<MutableList<EventItem>> get() = _eventItem

    private val _detailsItem =  MutableLiveData<EventDetail>()
    val detailsItem: LiveData<EventDetail> get() = _detailsItem
    fun getDetails(url: String) =
        viewModelScope.launch {
            eventRepository.getEvenDetails(url = url).collect {
                _detailsItem.value = it
        }
    }

//    fun fetchEvent(url: String): MutableLiveData<EventDetail> {
//        val item = MutableLiveData<EventDetail>()
//        viewModelScope.launch(IO) {
//            eventRepository.getEvenDetails(url).collect {
//                item.postValue(it)
//            }
//        }
//        return item
//    }
}