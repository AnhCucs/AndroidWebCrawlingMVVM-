package anhcucs.ninhgiang_hd.jsoupscratch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import anhcucs.ninhgiang_hd.jsoupscratch.R
import anhcucs.ninhgiang_hd.jsoupscratch.databinding.FragmentDetailsEventBinding
import anhcucs.ninhgiang_hd.jsoupscratch.viewmodel.EventViewModel
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsEventFragment : BaseFragment<FragmentDetailsEventBinding>() {
    private val eventViewModel by viewModels<EventViewModel>()
    private val args by navArgs<DetailsEventFragmentArgs>()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailsEventBinding {
        return FragmentDetailsEventBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        binding.progressBar.isVisible = true
        binding.apply {
            titleTextView.text = args.eventItem.title
            imageView.load(args.eventItem.image) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }
            placeTextView.text = args.eventItem.place
            dateTextView.text = args.eventItem.date
            eventViewModel.getDetails(args.eventItem.url)
        }

    }

    override fun observeLiveData() {
        super.observeLiveData()
        eventViewModel.detailsItem.observe(viewLifecycleOwner, { details ->
            binding.apply {
                ticketTextView.text = details.ticketInfo
                detailTextView.text = details.detail
                progressBar.isVisible = false
            }
        })
    }
}