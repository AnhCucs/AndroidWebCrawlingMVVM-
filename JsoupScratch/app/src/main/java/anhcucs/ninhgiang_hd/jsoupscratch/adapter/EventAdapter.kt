package anhcucs.ninhgiang_hd.jsoupscratch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import anhcucs.ninhgiang_hd.jsoupscratch.R
import anhcucs.ninhgiang_hd.jsoupscratch.data.EventItem
import anhcucs.ninhgiang_hd.jsoupscratch.databinding.ItemEventBinding
import coil.load
import coil.transform.CircleCropTransformation

class EventAdapter : ListAdapter<EventItem, EventAdapter.EventViewHolder>(DiffUtilCallBack()) {
    var onItemClick: (EventItem) -> Unit = {}

    inner class EventViewHolder(private val binding: ItemEventBinding,private val onItemClick:(EventItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick.invoke(getItem(adapterPosition))
            }
        }
        fun bind(data: EventItem) {
            binding.titleTextView.text = data.title
            binding.dateTextView.text = data.date
            binding.placeTextView.text = data.place
            binding.descTextView.text = data.desc
            binding.imageView.load(
                data.image
            ) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EventViewHolder(binding,onItemClick)
    }


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<EventItem>() {
    override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem.id == newItem.id
    }

}
