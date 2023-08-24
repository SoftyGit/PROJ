package com.example.planetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.planetapp.databinding.FragmentCalendarBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val mWindow = requireActivity().window
        mWindow.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.accent)

        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.backBtm.setOnClickListener {
            bottomNavigationView?.selectedItemId = R.id.app_home
            childFragmentManager.beginTransaction()
                .replace(R.id.cal_container, HomeFragment())
                .commit()
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}