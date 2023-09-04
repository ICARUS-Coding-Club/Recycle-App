package com.icarus.recycle_app.ui.info.placeholder

import androidx.fragment.app.Fragment
import com.icarus.recycle_app.R
import com.icarus.recycle_app.ui.info.content.environment_tip.EnvironmentTipFragment
import com.icarus.recycle_app.ui.info.content.trash_place.TrashPlaceFragment
import com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol.RecyclingSymbolFragment
import java.util.ArrayList
import java.util.HashMap

object PlaceholderContent {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    init {
        addItem(PlaceholderItem("0", "재활용 마크 설명", "재활용 마크에 대한 자세한 설명을 볼 수 있습니다.", RecyclingSymbolFragment(), R.drawable.info_1))
        addItem(PlaceholderItem(
            "1",
            "우리 지역 쓰레기 버리는 곳",
            "우리 지역의 쓰레기 버리는 곳을 볼 수 있습니다.",
            TrashPlaceFragment(),
            R.drawable.info_2
        ))
        addItem(PlaceholderItem(
            "2",
            "환경 관련 팁 안내",
            "환경 관련 팁을 볼 수 있습니다.",
            EnvironmentTipFragment(),
            R.drawable.info_3
        ))
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    data class PlaceholderItem(
        val id: String,
        val title: String,
        val content: String,
        val fragment: Fragment,
        val info: Int
    )
}