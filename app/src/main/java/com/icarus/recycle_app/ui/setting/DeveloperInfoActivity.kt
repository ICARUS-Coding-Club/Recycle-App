package com.icarus.recycle_app.ui.setting

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityDeveloperInfoBinding

class DeveloperInfoActivity : AppCompatActivity() {

    val impression = mutableMapOf<String, String>()
    init {
        impression["차도훈"] = "dddddddddddd"
        impression["김주영"] = "ddddddddddddd"
        impression["김민기"] = "dddddddddddd"
        impression["박지홍"] = "dddddddddddd"
        impression["최민화"] = "dddddddddddd"
        impression["임소희"] = "dddddddddddd"
        impression["이지훈"] = "dddddddddddd"
        impression["차호련"] = "dddddddddddd"
        impression["김나은"] = "dddddddddddd"
        impression["김재겸"] = "dddddddddddd"
        impression["이가은"] = "dddddddddddd"
        impression["태 원"] = "dddddddddddd"

    }

    private lateinit var binding: ActivityDeveloperInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeveloperInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayout = binding.linearLayoutForText

        val layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.MarginLayoutParams.WRAP_CONTENT,
            ViewGroup.MarginLayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(
                dpToPx(20), // Start
                dpToPx(10), // Top
                dpToPx(20), // End
                0
            )
        }

        val drawable = ContextCompat.getDrawable(this, R.drawable.custom_circle_background_gray)


        for (item in impression) {

            val nameText = TextView(this)
            nameText.text = item.key

            nameText.setPadding(
                dpToPx(20),
                dpToPx(15),
                dpToPx(20),
                dpToPx(15)
            )

            nameText.layoutParams = layoutParams

            nameText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            nameText.background = drawable

            val contentText = TextView(this)
            contentText.text = item.value

            contentText.setPadding(
                dpToPx(20),
                dpToPx(20),
                dpToPx(20),
                dpToPx(20)
            )

            contentText.layoutParams = layoutParams


            contentText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            contentText.background = drawable

            linearLayout.addView(nameText)
            linearLayout.addView(contentText)
        }



    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}