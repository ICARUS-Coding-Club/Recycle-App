package com.icarus.recycle_app.ui.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.icarus.recycle_app.adapters.CategoryResultAdapter
import com.icarus.recycle_app.databinding.ActivityCategoryResultBinding
import com.icarus.recycle_app.dto.Trash

class CategoryResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        val testTrash: Trash = Trash(
            0,
            "김치",
            "음식물쓰레기",
            "-상해서 먹지 못하는 김치는 김장 양념을 물에 한번 헹군 후 음식물쓰레기로 버려요.\n" +
                    "\n" +
                    "-가정에서 배출하는 소량의 김치국물은 하수구로 흘려보내도 무방합니다. 다만 건더기는 걸러서 음식물쓰레기로 버려주세요.\n" +
                    "\n" +
                    "-식당 등에서 대량으로 버려야할 경우 주민센터/구청 등에 생활폐기물로 신고/배출해주세요.",
            "-음식물 쓰레기는 살균 처리와 고온 건조 과정을 거쳐 동물용 사료 또는 경작용 퇴비로 재활용됩니다. 그래서 쉽게 분해되지 않거나 매운 맛과 향이 강해서 사료나 퇴비로 쓰일 수 없는 음식물은 재활용이 불가능합니다.\n" +
                    "\n" +
                    "-김치 양념과 국물에는 매운 캡사이신 성분과 나트륨이 다량 함유되어 있어 사료나 퇴비로 적합하지 않습니다. 반드시 양념을 헹군 후 음식물쓰레기로 배출해주세요.  ",
            "",
            0,
            "",
            image = "https://cdn.imweb.me/thumbnail/20220120/dba3608172709.jpg",
            "",
            ""
        )
        // 아이템 데이터를 초기화합니다.
        val itemList = ArrayList<Trash>()
        itemList.add(testTrash)
        itemList.add(testTrash)
        // 더 많은 아이템을 추가할 수 있습니다.

        val adapter = CategoryResultAdapter(itemList, applicationContext)
        binding.recyclerView.adapter = adapter

        // RecyclerView에 GridLayoutManager를 설정하여 한 줄에 2개의 아이템이 표시되도록 합니다.
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}