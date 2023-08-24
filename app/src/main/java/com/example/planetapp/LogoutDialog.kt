package com.example.planetapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.planetapp.databinding.DialogLogoutBinding
import com.example.planetapp.databinding.DialogLogoutCompleteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LogoutDialog : DialogFragment() {
    private var _binding: DialogLogoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = DialogLogoutBinding.inflate(inflater, container, false)

        binding.yesBtn.setOnClickListener{
            // 로그아웃 부분 API 구현

            //
            val fragmentManager = parentFragmentManager
            dismiss()

            Handler(Looper.getMainLooper()).postDelayed({
                val completeDialog = LogoutCompleteDialog()
                completeDialog.show(fragmentManager, "LogoutCompleteDialog")
            }, 500)


        }
        binding.noBtn.setOnClickListener{
            dismiss()
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
class LogoutCompleteDialog : DialogFragment() {
    private var _binding: DialogLogoutCompleteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

        _binding = DialogLogoutCompleteBinding.inflate(inflater, container, false)


        Handler(Looper.getMainLooper()).postDelayed({
            dismiss()
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            childFragmentManager.beginTransaction()
                .replace(R.id.pro_container, HomeFragment())
                .commit()
            bottomNavigationView?.selectedItemId = R.id.app_home
        }, 3000)


        return binding.root
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}