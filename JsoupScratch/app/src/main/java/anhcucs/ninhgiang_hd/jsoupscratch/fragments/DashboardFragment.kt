package anhcucs.ninhgiang_hd.jsoupscratch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import anhcucs.ninhgiang_hd.jsoupscratch.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

}