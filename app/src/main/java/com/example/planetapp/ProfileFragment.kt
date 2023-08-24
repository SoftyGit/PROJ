package com.example.planetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.planetapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val mWindow = requireActivity().window
        val dialog1 = LogoutDialog()
        val dialog2 = DeleteDialog()
        mWindow.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.background)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.logout.setOnClickListener {
            dialog1.show(requireActivity().supportFragmentManager, "LogoutDialog")
        }
        binding.delaccount.setOnClickListener {
            dialog2.show(requireActivity().supportFragmentManager, "DeleteDialog")
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}