package com.example.planetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val mWindow = requireActivity().window
        mWindow.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.accent)

        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}