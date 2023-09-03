package com.icarus.recycle_app.ui.setting

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)


        val dialog = AlertDialog.Builder(requireContext())
        dialog.setPositiveButton("확인") { sDialog, _ -> sDialog?.dismiss() }


        binding.clTeamInfo.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), DeveloperInfoActivity::class.java))
        }

        binding.clAppInfo.setOnClickListener {
            dialog.setMessage("인공지능 쓰레기 재활용 안내 애플리케이션\n\nv0.1-beta.0")
            dialog.show()
        }

        binding.clEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com")) // 수신인 주소
                putExtra(Intent.EXTRA_SUBJECT, "제목") // 이메일 제목
                putExtra(Intent.EXTRA_TEXT, "이메일 본문") // 이메일 본문
            }

            // 이메일 앱이 설치되어 있는지 확인
            if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(emailIntent)
            } else {
                Toast.makeText(this@SettingFragment.requireContext(), "이메일 앱이 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}