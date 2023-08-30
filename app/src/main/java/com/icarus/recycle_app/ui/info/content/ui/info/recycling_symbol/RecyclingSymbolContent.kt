package com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol

import android.graphics.Color
import com.icarus.recycle_app.dto.RecyclingSymbol

object RecyclingSymbolContent {
    val ITEMS: MutableList<RecyclingSymbol> = mutableListOf()

    init {
        // 페트
        ITEMS.add(RecyclingSymbol(
            "페트",
            "",
            Color.rgb(204, 204, 0),
            "PET 용기, 포장재",
            "내용물을 깨끗이 비우고 뚜껑 등 다른 재질 제거 후 압착하여 배출",
            ""
        ))

        // 플라스틱
        val plasticTypes = listOf("HDPE", "LDPE", "PP", "PS", "PVC", "OTHER")
        for (type in plasticTypes) {
            ITEMS.add(RecyclingSymbol(
                "플라스틱",
                type,
                Color.rgb(0, 0, 204),
                "$type 플라스틱 용기, 포장재",
                "내용물을 비우고 물로 헹군 후 압착하여 배출",
                ""
            ))
        }

        // 비닐류
        for (type in plasticTypes) {
            ITEMS.add(RecyclingSymbol(
                "비닐류",
                type,
                Color.rgb(204, 0, 204),
                "$type 비닐 포장재",
                "내용물을 비우고 깨끗이 하여 압착하여 배출",
                ""
            ))
        }

        // 종이
        ITEMS.add(RecyclingSymbol(
            "종이",
            "",
            Color.rgb(0, 0, 0),
            "신문지, 책자, 노트, 종이쇼핑백, 달력, 포장지 등",
            "물기에 젖지 않도록 하고 반듯하게 펴서 차곡차곡 쌓은 후 묶어서 배출",
            "비닐, 은박지 등 코팅된 종이류, 1회용기저귀"
        ))

        // 종이팩
        ITEMS.add(RecyclingSymbol(
            "종이팩",
            "",
            Color.rgb(0, 204, 0),
            "우유팩, 음료수팩, 종이컵 등",
            "내용물을 비운 후 물로 헹군 후 압축하여 배출",
            ""
        ))

        // 유리
        ITEMS.add(RecyclingSymbol(
            "유리",
            "",
            Color.rgb(204, 132, 0),
            "음료수 병, 기타 병류 등",
            "플라스틱이나 알루미늄 뚜껑을 제거하고 내용물을 비운 후 헹구어 배출",
            "거울, 차유리, 도자기, 사기그릇, 화장품병"
        ))

        // 캔류
        val canTypes = listOf("일반", "알루미늄", "철")
        for (type in canTypes) {
            ITEMS.add(RecyclingSymbol(
                "캔류",
                type,
                Color.rgb(102, 102, 102),
                "$type 캔",
                "이물질을 넣지 말고 헹구어 배출",
                ""
            ))
        }
    }
}