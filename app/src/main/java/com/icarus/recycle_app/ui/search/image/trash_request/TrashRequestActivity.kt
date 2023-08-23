package com.icarus.recycle_app.ui.search.image.trash_request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.databinding.ActivityTrashRequestBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.utils.ServerConnectHelper

class TrashRequestActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTrashRequestBinding
    private val serverConnectHelper = ServerConnectHelper()

    private val binding get() = _binding

    // 임시 데이터
    private val testTrash : Trash = Trash(0, "김치", "음식물쓰레기", "-상해서 먹지 못하는 김치는 김장 양념을 물에 한번 헹군 후 음식물쓰레기로 버려요.\n" +
            "\n" +
            "-가정에서 배출하는 소량의 김치국물은 하수구로 흘려보내도 무방합니다. 다만 건더기는 걸러서 음식물쓰레기로 버려주세요.\n" +
            "\n" +
            "-식당 등에서 대량으로 버려야할 경우 주민센터/구청 등에 생활폐기물로 신고/배출해주세요.", "-음식물 쓰레기는 살균 처리와 고온 건조 과정을 거쳐 동물용 사료 또는 경작용 퇴비로 재활용됩니다. 그래서 쉽게 분해되지 않거나 매운 맛과 향이 강해서 사료나 퇴비로 쓰일 수 없는 음식물은 재활용이 불가능합니다.\n" +
            "\n" +
            "-김치 양념과 국물에는 매운 캡사이신 성분과 나트륨이 다량 함유되어 있어 사료나 퇴비로 적합하지 않습니다. 반드시 양념을 헹군 후 음식물쓰레기로 배출해주세요.  ",
        0,
        favorite = false,
        "",
        image = "test.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityTrashRequestBinding.inflate(layoutInflater)


        // 서버 요청 리스너 등록
        serverConnectHelper.request = object : ServerConnectHelper.RequestServer {
            override fun onSuccess(testPost: TestPost) {
                // 데이터 가져오기 성공
                println("Post title: ${testPost.title}")

                // 임시 데이터
                initRequestData(testTrash)

                // 데이터 입력이 끝나면
                setContentView(binding.root)
            }

            override fun onFailure() {
                // 서버 요청에 실패 했을 때
                setContentView(binding.root)
            }

        }

        // 서버 요청 실행
        serverConnectHelper.getPost()

        initListener()


    }


    private fun initRequestData(trash: Trash) {
        binding.tvToolBarTitle.text = trash.name
        binding.tvTrashTitle.text = "${trash.name} 정보"
        binding.tvTrashThrowInfoTitle.text = "${trash.name}의 버리는 방법"
        binding.tvTrashTipInfoTitle.text = "${trash.name}의 알아두면 좋은 점"

        binding.tvTrashThrowInfo.text = "${trash.method}"
        binding.tvTrashTipInfo.text = "${trash.etc}"

    }

    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}