package com.icarus.recycle_app.ui.current_situation

import android.graphics.Matrix
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentCurrentSituationBinding
import com.icarus.recycle_app.dto.TrashGraph

/**
 * A fragment representing a list of Items.
 */
class CurrentSituationFragment : Fragment() {

    private lateinit var _binding: FragmentCurrentSituationBinding
    private var data: HashMap<Int,TrashGraph> = hashMapOf()
    private var pageNum = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =  FragmentCurrentSituationBinding.inflate(inflater,container,false,)

        dataInit()
        initListener()
        return _binding.root
    }

    private fun initListener(){
        _binding.ibUndo.setOnClickListener {
            if(pageNum==1){
                Toast.makeText(activity,"페이지의 처음 입니다",Toast.LENGTH_SHORT).show()
            }else{
                pageNum--
                dataShow(pageNum)
                }
        }
        _binding.ibNext.setOnClickListener {
            if(pageNum==15){
                Toast.makeText(activity,"페이지의 끝 입니다",Toast.LENGTH_SHORT).show()
            }else{
                pageNum++
                dataShow(pageNum)
            }
        }
        _binding.ibRefresh.setOnClickListener {
            _binding.ivImage.setCenteredScaledImage()
        }

    }




    private fun dataShow(pageNum:Int) {
        if (data[pageNum] != null) {
            _binding.tvTitle.text = data[pageNum]?.title
            data[pageNum]?.let { it1 -> _binding.ivImage.setImageResource(it1.image) }
        }

    }
    fun dataInit() {
        data[1] = TrashGraph("시도별 생활폐기물 총 발생량(톤/일)", R.drawable.graph1)
        data[2] = TrashGraph("시도별 1인당 생활폐기물 발생량(kg/일)", R.drawable.graph2)
        data[3] = TrashGraph("시도별 하루 생활폐기물 중 재활용 분리 배출의 비율(%)", R.drawable.graph3)
        data[4] = TrashGraph("시도별 생활폐기물 중 종량제 방식에 의한 혼합배출의 비율(%)", R.drawable.graph4)
        data[5] = TrashGraph("시도별 생활폐기물 중 음식물류 분리 배출의 비율(%)", R.drawable.graph5)
        data[6] = TrashGraph("시도별 음식물류 폐기물의 1인당 배출량(kg/일)", R.drawable.graph6)
        data[7] = TrashGraph("1인당 음식물류 폐기물(kg/일) 배출이 많은 시군구 Top 20", R.drawable.graph7)
        data[8] = TrashGraph("시도별 발생 총량 중 폐합성수지류의 비율(%)", R.drawable.graph8)
        data[9] = TrashGraph("시도별 폐합성수지류 폐기물 1인당 배출량(kg/일)", R.drawable.graph9)
        data[10] = TrashGraph("1인당 폐합성수지류 폐기물(kg/일) 배출이 많은 시군구 Top 20", R.drawable.graph10)
        data[11] = TrashGraph("시도별 배출방식 별 생활폐기물의 비율(%)", R.drawable.graph11)
        data[12] = TrashGraph("재활용으로 배출된 폐기물의 재활용 처리 비율(%)", R.drawable.graph12)
        data[13] = TrashGraph("종량제로 배출된 폐기물의 재활용 처리 비율(%)", R.drawable.graph13)
        data[14] = TrashGraph("2014년~2019년 배출 총 량(톤/일)", R.drawable.graph14)
        data[15] = TrashGraph("2014년~2019년 처리방식 별 총량(톤/일)", R.drawable.graph15)
    }
}