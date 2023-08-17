package com.icarus.recycle_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.icarus.recycle_app.databinding.ActivityMainBinding
import com.icarus.recycle_app.ui.onboarding.OnBoardingActivity
import com.icarus.recycle_app.ui.search.SearchActivity

class MainActivity : AppCompatActivity(), MainBottomSheetDialog.BottomSheetListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_info,R.id.navigation_current_situation,R.id.navigation_setting
            )
        )

        // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        initListener()


    }

    private fun initListener() {
        binding.fab.setOnClickListener {
            val bottomSheetDialog = MainBottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, "example")

        }
    }

    override fun onButtonClicked(text: String?) {
        // 버튼이 클릭되었을 때의 동작을 구현합니다.
        // 이 메서드는 MainBottomSheetDialog 내부에서 호출될 것입니다.
        // 예시로 로그 출력을 해보겠습니다.
        Log.d("MainActivity", "Button clicked with text: $text")

        when (text) {
            "button1" -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("type_click", 0)
                startActivity(intent)
            }
            "button2" -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("type_click", 1)
                startActivity(intent)
            }
        }

    }
}
