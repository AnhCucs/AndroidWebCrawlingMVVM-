package anhcucs.ninhgiang_hd.jsoupscratch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import anhcucs.ninhgiang_hd.jsoupscratch.databinding.FragmentNotificationBinding

class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater, container, false)
    }

}