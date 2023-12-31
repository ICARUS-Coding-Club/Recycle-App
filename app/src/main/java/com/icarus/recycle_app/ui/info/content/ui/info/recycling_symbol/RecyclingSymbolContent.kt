package com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol

import android.graphics.Color
import com.icarus.recycle_app.dto.RecyclingSymbol

object RecyclingSymbolContent {
    val ITEMS: MutableList<RecyclingSymbol> = mutableListOf()

    init {
        // 페트
        ITEMS.add(RecyclingSymbol(
            "페트",
            "페트",
            Color.rgb(255, 228, 0),
            "PET 용기, 포장재",
            "1.비우기 & 헹구기\n\n -용기에 남아있는 음료나 식품을 완전히 비우십시오. 가능한 한 용기를 헹구워 깨끗이 만들어 재활용 과정에 방해되지 않도록 합니다.\n\n\n" +
                    "2.라벨 제거\n\n -일부 지역에서는 PET 용기의 라벨을 제거하라는 지침이 있을 수 있습니다. 라벨은 다른 재료로 만들어질 수 있으므로 재활용 과정에서 분리될 수 있습니다.\n\n\n" +
                    "3.뚜껑 제거\n\n -PET 병의 뚜껑은 다른 유형의 플라스틱으로 제작되었을 수 있으므로, 지침에 따라 병과 분리하여 재활용할 수 있습니다.\n\n\n" +
                    "4.압착하기\n\n -병을 압착하여 공간을 절약하고, 운송 및 저장에 효율적으로 만듭니다.\n\n\n" +
                    "5.지정된 재활용통에 버리기\n\n -많은 지역에서는 PET 용기를 커뮤니티 재활용 프로그램에 포함하고 있습니다. 지역의 재활용 지침에 따라 지정된 재활용통에 PET 용기를 버립니다.\n\n\n" +
                    "6.지역 지침 확인\n\n -지역에 따라 PET 용기 재활용 방법이 다를 수 있습니다.",
            "-열에 녹지 않는 딱딱한 재질\n"+
                    "-전자 오락기,다리미 등과 같은 합성된 제품\n"+
                    "-이물질 혹은 음식물이 묻어있는 재활용품",
            "김해시청, 환경부 권장 사항 "

        ))

        // 플라스틱
        val plasticTypes = listOf("HDPE", "LDPE", "PP", "PS", "PVC", "OTHER")
        for (type in plasticTypes) {
            ITEMS.add(RecyclingSymbol(
                "플라스틱",
                type,
                Color.rgb(0, 84, 255),
                "$type 플라스틱 용기, 포장재",
                "플라스틱 용기를 재활용하기 전에 항상 라벨을 확인하십시오.\n라벨에는 플라스틱의 유형 및 재활용 정보가 표시되어 있을 수 있습니다.\n\n\n" +
                        "가능한 한 플라스틱 용기를 깨끗이 비우고 헹구십시오.\n이는 재활용 과정을 더 효율적으로 만들어 줍니다.\n\n\n" +
                        "재활용은 중요하지만, 플라스틱 사용을 줄이거나 다른 친환경적인 대체품을 선택하는 것도 환경을 보호하는 데 큰 도움이 됩니다.",
                "-이물질 혹은 음식물이 묻어있는 재활용품",
                "파주시청,나주시청,환경부 권장 사항 "
            ))
        }

        // 비닐류
        for (type in plasticTypes) {
            ITEMS.add(RecyclingSymbol(
                "비닐류",
                type,
                Color.rgb(128, 65, 217),
                "$type 비닐 포장재",
                "1.비닐 클린업\n\n -비닐 포장재에 남아 있는 음식물, 물 등의 오염물을 제거하고 가능한 한 깨끗이 헹군 후 말려야 합니다.\n\n\n" +
                        "2.평평하게 하기\n\n -비닐 포장재를 평평하게 펴서 공간을 최소화하고, 재활용 과정에서의 효율성을 높이기 위해 필요합니다.\n\n\n" +
                        "3.모으기\n\n -비닐 포장재는 다른 플라스틱과 분리되어야 합니다. 따라서 비닐만을 위한 별도의 봉투나 바구니에 모으는 것이 좋습니다.\n\n\n" +
                        "4.지역 지침 확인\n\n -모든 지역이 비닐 포장재의 재활용을 지원하는 것은 아닙니다. 지역의 재활용 지침을 확인하여 해당 지역에서 비닐 재활용이 가능한지 확인해야 합니다.\n\n\n" +
                        "5.지정된 재활용통에 버리기\n\n -지역에서 비닐 재활용을 지원하는 경우 지정된 재활용통에 비닐을 버려야 합니다.\n\n\n" +
                        "6.특별한 수거 프로그램 이용\n\n -일부 지역에서는 대형 슈퍼마켓이나 상점에서 비닐 포장재 전용 수거함을 제공할 수 있습니다. 이러한 프로그램을 활용하여 비닐을 별도로 수거할 수 있습니다.",
                "-이물질 혹은 음식물이 묻어있는 재활용품",
                "마포구청,김포시청 권장 사항"
            ))
        }

        // 종이
        ITEMS.add(RecyclingSymbol(
            "종이",
            "종이",
            Color.rgb(0, 0, 0),
            "신문지,노트 등",
            "1.오염된 종이 제외\n\n -음식물, 기름, 왁스 등으로 오염된 종이는 재활용에 적합하지 않습니다. 예를 들어, 기름진 피자 상자의 오염된 부분은 일반 쓰레기로 처리하고, 깨끗한 부분만 재활용해야 합니다.\n\n\n"+
                    "2.제거할 아이템 확인\n\n -물기에 젖지 않도록 하고, 테이프, 스테이플러, 플라스틱 클립 등은 가능한 한 제거하고 재활용하세요. 그렇지만 대부분의 재활용 시설은 이러한 소량의 금속이나 플라스틱을 분리할 수 있습니다.\n\n\n" +
                    "3.종이 분류\n\n -일부 지역에서는 재활용 가능한 종이의 유형(예: 신문, 오피스 용지, 판지 등)에 따라 분류를 요구할 수 있습니다.\n\n\n" +
                    "4.판지 접기\n\n -큰 판지 박스는 작게 접어서 공간을 절약하고 재활용통에 넣습니다.\n\n\n" +
                    "5.종이 제품 확인\n\n -모든 종이 제품이 재활용 가능한 것은 아닙니다. 왁스로 코팅된 종이컵이나 특정 유형의 래미네이트된 종이와 같은 제품은 재활용하기 어려울 수 있습니다.\n\n\n" +
                    "6.지역의 재활용 지침 확인\n\n -지역마다 재활용 지침이 다를 수 있으므로, 항상 지역 재활용 프로그램의 지침을 확인하십시오.\n\n\n" +
                    "7.지정된 재활용통에 버리기\n\n -지역의 재활용통이나 지정된 재활용 센터에 종이를 버립니다.",
            "-코팅된 종이류\n"+
                    "-이물질 혹은 음식물이 묻어있는 재활용품",
            "파주시청,김해시청,환경부 권장 사항 "
        ))

        // 종이팩
        ITEMS.add(RecyclingSymbol(
            "종이팩",
            "종이",
            Color.rgb(47, 157, 39),
            "우유팩,음료수팩",
            "1.비우기\n\n -종이팩에 남아있는 내용물을 완전히 비우십시오.\n\n\n"+
                    "2.헹구기\n\n -가능한 한 내용물을 깨끗이 헹궈 오염을 최소화합니다.\n\n\n" +
                    "3.평평하게 만들기\n\n -공간을 절약하고 수거 및 운송을 용이하게 하기 위해 종이팩을 평평하게 접습니다.\n\n\n" +
                    "4.지역 지침 확인\n\n -모든 지역이 종이팩 재활용을 지원하는 것은 아닙니다. 따라서 지역의 재활용 지침을 확인하여 종이팩 재활용이 가능한지 확인해야 합니다.\n\n\n" +
                    "5.지정된 재활용통에 버리기\n\n -종이팩 재활용을 지원하는 지역에서는 지정된 재활용통이나 수거 지점에 종이팩을 버려야 합니다.종이팩 전용 수거함이 없는 경우에는 종이류와 구분할 수 있도록 끈 등으로 묶어 종이류 수거함으로 배출해야 합니다.",
            "-코팅된 종이류\n"+
                    "-이물질 혹은 음식물이 묻어있는 재활용품",
            "김해시청,파주시청,환경부 권장 사항"
        ))

        // 유리
        ITEMS.add(RecyclingSymbol(
            "유리",
            "유리",
            Color.rgb(255, 187, 0),
            "음료수 병 등",
            "1.오염물질 제거\n\n -유리병이나 유리용기에 남아 있는 음료나 음식물을 완전히 비우고 헹군 후 말립니다.\n\n\n" +
                    "2.라벨 제거\n\n -가능하면 유리병의 라벨을 제거합니다. 그러나 대부분의 재활용 시설에서는 라벨을 처리할 수 있으므로 완벽하게 제거하지 않아도 됩니다.\n\n\n" +
                    "3.색상별 분리\n\n -일부 지역에서는 유리를 색상별(투명, 녹색, 갈색 등)로 분리해야 합니다. 지역의 재활용 지침을 확인하여 유리를 올바르게 분류합니다.\n\n\n" +
                    "4.부서진 유리 조심\n\n -부서진 유리는 다칠 위험이 있으므로 조심스럽게 처리해야 합니다. 일부 재활용 센터는 부서진 유리를 받아들이지 않을 수 있습니다.\n\n\n" +
                    "5.지역 재활용 지침 확인\n\n -지역마다 유리 재활용의 지침과 수용 가능한 유리 유형이 다를 수 있습니다. 따라서 지역의 재활용 지침을 확인하십시오.\n\n\n" +
                    "6.지정된 재활용통에 버리기\n\n -유리 재활용을 지원하는 지역에서는 지정된 재활용통이나 수거 지점에 유리를 버립니다.",
            "거울,차유리,도자기,사기그릇,화장품 병\n"+
                    "-이물질 혹은 음식물이 묻어있는 재활용품",
            "김해시청,송파구청,환경부 권장 사항"
        ))

        // 캔류
        val canTypes = listOf("일반", "알루미늄", "철")
        for (type in canTypes) {
            ITEMS.add(RecyclingSymbol(
                "캔류",
                type,
                Color.rgb(166, 166, 166),
                "$type 캔",
                "1.비우기\n\n -캔에 남아 있는 음료나 음식물을 완전히 비우십시오.\n\n\n" +
                        "2.헹구기\n\n -가능하면 캔을 헹궈 내부의 잔여물을 제거합니다. 이렇게 하면 재활용 과정에서 오염을 줄일 수 있습니다.\n\n\n" +
                        "3.라벨 제거\n\n -일부 재활용 센터는 캔의 라벨 제거를 요구하지만, 대부분은 라벨이 붙어 있어도 처리할 수 있습니다. 지역의 재활용 지침을 확인하세요.\n\n\n" +
                        "4.캔 압착\n\n -캔을 약간 압착하여 공간을 절약하고 저장 및 운송을 용이하게 합니다.\n\n\n" +
                        "5.재질별 분리\n\n -알루미늄 캔과 철 캔은 다르게 재활용됩니다. 일부 지역에서는 이 두 유형의 캔을 분리하라는 지침이 있을 수 있습니다.\n\n\n" +
                        "6.지역 재활용 지침 확인\n\n -지역마다 캔류의 재활용 지침이 다를 수 있습니다. 지역의 재활용 프로그램 지침을 확인하여 캔을 올바르게 분리 및 처리하십시오.\n\n\n" +
                        "7.지정된 재활용통에 버리기\n\n -지정된 재활용통이나 수거 지점에 캔을 버립니다.",
                "-이물질 혹은 음식물이 묻어있는 재활용품",
                "김해시청,송파구청,환경부 권장 사항"
            ))
        }
    }
}