package com.icarus.recycle_app.ui.search.image.trash_request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityTrashRequestBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.home.HomeFragment
import com.icarus.recycle_app.utils.ServerConnectHelper

class TrashRequestActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTrashRequestBinding
    private val serverConnectHelper = ServerConnectHelper()

    private val binding get() = _binding
    private var _type = 0


    //Intent Type 0 - 일반검색 1- 카메라/갤러리 검색


    private val testTrash : Trash = Trash(0, "김치", "음식물쓰레기", "-상해서 먹지 못하는 김치는 김장 양념을 물에 한번 헹군 후 음식물쓰레기로 버려요.\n" +
            "\n" +
            "-가정에서 배출하는 소량의 김치국물은 하수구로 흘려보내도 무방합니다. 다만 건더기는 걸러서 음식물쓰레기로 버려주세요.\n" +
            "\n" +
            "-식당 등에서 대량으로 버려야할 경우 주민센터/구청 등에 생활폐기물로 신고/배출해주세요.", "-음식물 쓰레기는 살균 처리와 고온 건조 과정을 거쳐 동물용 사료 또는 경작용 퇴비로 재활용됩니다. 그래서 쉽게 분해되지 않거나 매운 맛과 향이 강해서 사료나 퇴비로 쓰일 수 없는 음식물은 재활용이 불가능합니다.\n" +
            "\n" +
            "-김치 양념과 국물에는 매운 캡사이신 성분과 나트륨이 다량 함유되어 있어 사료나 퇴비로 적합하지 않습니다. 반드시 양념을 헹군 후 음식물쓰레기로 배출해주세요.  ",
        "",
        0,
        "",
        image = "test.png",
        "",
        ""
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityTrashRequestBinding.inflate(layoutInflater)

        setContentView(binding.root)

        _type = intent.getIntExtra("type",0)

        initListener()
        when(_type){

            //일반 검색
            0 -> {
                val trashObject = intent.getParcelableExtra<Trash>("trash")
                if (trashObject != null) {
                    // Trash 객체가 전달되었으므로 이를 사용할 수 있습니다.
                    initRequestData(trashObject)
                }
            }

            //카메라/갤러리 검색
            1-> {
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
            }
        }




    }


    private fun initRequestData(trash: Trash) {
        binding.tvToolBarTitle.text = trash.name
        binding.tvTrashTitle.text = "${trash.name} 정보"
        Glide.with(applicationContext)
            .load(trash.image) // item.image는 이미지 파일의 경로 문자열
            .into(binding.ivTrash)
        binding.tvTrashType.text = trash.type
        binding.tvTrashThrowInfoTitle.text = "${trash.name}의 버리는 방법"
        binding.tvTrashTipInfoTitle.text = "${trash.name}의 알아두면 좋은 점"

        binding.tvTrashThrowInfo.text = "${trash.method}"
        binding.tvTrashTipInfo.text = "${trash.etc}"

        binding.ibFavorite.setOnClickListener {
            favoriteUpdate(trash.id)
        }
        favoriteInit(trash.id)


    }

    private fun favoriteInit(id: Int){
        //favorite 확인
        val map = AppManager.getFavorites()
        if(map.containsKey(id)){
            if(map[id]==true){
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite)
            }else{
                binding.ibFavorite.setImageResource(R.drawable.ic_blank_favorite)
            }
        }else{
            binding.ibFavorite.setImageResource(R.drawable.ic_blank_favorite)
            AppManager.setFavorites(id,false)
        }
    }
    private fun favoriteUpdate(id: Int){//버그 있음
        //favorite 확인
        val map = AppManager.getFavorites()
        if(map[id]==true){
            binding.ibFavorite.setImageResource(R.drawable.ic_blank_favorite)
            AppManager.setFavorites(id,false)
        }else if(map[id]==false){
            binding.ibFavorite.setImageResource(R.drawable.ic_favorite)
            AppManager.setFavorites(id,true)
        }
    }



    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}