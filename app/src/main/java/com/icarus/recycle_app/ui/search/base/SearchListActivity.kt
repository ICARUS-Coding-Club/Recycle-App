package com.icarus.recycle_app.ui.search.base
import SearchListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.R

import com.icarus.recycle_app.databinding.ActivitySearchListBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.trash_request.TrashRequestActivity

class SearchListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchListBinding
    private lateinit var searchAdapter: SearchListAdapter
    private val lists = mutableListOf<Trash>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

        lists.add(Trash(1,"바나나 껍질","2","2","2",2,false,false, R.drawable.bananas.toString()))

        val adapter = SearchListAdapter(this,lists)
        binding.autoCompleteTextView.setAdapter(adapter)
        //자동완성 선택시 이름만 입력되게
        binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedTrash = adapter.getItem(position) as Trash
            binding.autoCompleteTextView.setText(selectedTrash.name, false)
            binding.autoCompleteTextView.setSelection(selectedTrash.name.length) // 커서 위치 설정

            // 여기에서 검색 결과 화면으로 이동하는 코드를 추가합니다.
            // intent에 쿼리전달
            val intent = Intent(this, TrashRequestActivity::class.java)
            intent.putExtra("searchQuery", selectedTrash.name)
            startActivity(intent)

        }
    }
    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }


}