package anhcucs.ninhgiang_hd.jsoupscratch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import anhcucs.ninhgiang_hd.jsoupscratch.adapter.EventAdapter
import anhcucs.ninhgiang_hd.jsoupscratch.databinding.FragmentHomeBinding
import anhcucs.ninhgiang_hd.jsoupscratch.viewmodel.EventViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val eventViewModel by viewModels<EventViewModel>()

    private val eventAdapter by lazy {
        EventAdapter()
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        super.initView()
        initRecyclerView()
    }

    override fun initAction() {
        super.initAction()
        eventAdapter.onItemClick = { eventItem ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsEventFragment(eventItem)
            findNavController().navigate(action)
        }
    }

    private fun initRecyclerView(){
        binding.progressBar.visibility = View.VISIBLE
        with(binding.recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }
    }

    override fun observeLiveData() {
        super.observeLiveData()
        eventViewModel.eventItem.observe(viewLifecycleOwner, Observer { listEventItem ->
            eventAdapter.submitList(listEventItem)
            binding.progressBar.visibility = View.GONE
        })
    }

}