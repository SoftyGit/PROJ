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
import com.example.planetapp.databinding.DialogDeleteBinding
import com.example.planetapp.databinding.DialogDeleteCompleteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DeleteDialog : DialogFragment() {
    private var _binding: DialogDeleteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = DialogDeleteBinding.inflate(inflater, container, false)

        binding.yesBtn.setOnClickListener{
            // 회원탈퇴 부분 API 구현

            //
            val fragmentManager = parentFragmentManager
            dismiss()

            Handler(Looper.getMainLooper()).postDelayed({
                val completeDialog = DeleteCompleteDialog()
                completeDialog.show(fragmentManager, "DeleteCompleteDialog")
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
class DeleteCompleteDialog : DialogFragment() {
    private var _binding: DialogDeleteCompleteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {

        _binding = DialogDeleteCompleteBinding.inflate(inflater, container, false)

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