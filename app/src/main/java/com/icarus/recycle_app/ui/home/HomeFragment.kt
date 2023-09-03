package com.icarus.recycle_app.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.AddressRecyclerViewAdapter
import com.icarus.recycle_app.adapters.HomeAdapter
import com.icarus.recycle_app.databinding.FragmentHomeBinding
import com.icarus.recycle_app.dto.Address
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.category.CategoryResultActivity
import com.icarus.recycle_app.ui.search.base.SearchListActivity
import com.icarus.recycle_app.utils.ServerConnectHelper


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val dialogFragment = DaumAddressDialogFragment()
    private val serverConnectHelper = ServerConnectHelper()
    private lateinit var adapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 북마크 목록을 초기화하거나 표시하는 코드를 추가합니다.
        // 예를 들어, RecyclerView를 초기화하고 어댑터를 설정합니다.
        val recyclerView = binding.gridView
        adapter = HomeAdapter(listOf(),activity)
        recyclerView.adapter = adapter


        val sp: SharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val gson = Gson()
        val addressJson = sp.getString("addresses", null)

        if (addressJson != null) {
            val addressListType = object : TypeToken<ArrayList<Address>>() {}.type
            val addressList: ArrayList<Address> = gson.fromJson(addressJson, addressListType)
            val selectedAddress: Address? = addressList.find { it.selected }


            binding.tvAddress.text = selectedAddress!!.address
        } else {
            binding.tvAddress.text = "주소를 선택해주세요."
        }

        setGridViewHeightBasedOnChildren(binding.gridView, 4)
        initListener()

        return binding.root
    }

    private fun setGridViewHeightBasedOnChildren(gridView: GridView, columns: Int) {
        val listAdapter = gridView.adapter
            ?: // pre-condition
            return
        var totalHeight = 0
        val items = listAdapter.count
        var rows = 0
        val listItem = listAdapter.getView(0, null, gridView)
        listItem.measure(0, 0)
        totalHeight = listItem.measuredHeight
        var x = 1f
        if (items > columns) {
            x = (items / columns).toFloat()
            rows = (x + 1).toInt()
            totalHeight *= rows
        }

        // Add any additional height for decorations (such as padding or vertical spacing)
        totalHeight += gridView.verticalSpacing * (rows - 1)
        val params = gridView.layoutParams
        params.height = totalHeight
        gridView.layoutParams = params
    }

    private fun initListener(){

        val imageButtons = listOf(binding.ibFurniture,binding.ibElectronics,binding.ibDaily,binding.ibBathroom,binding.ibBook,binding.ibCosmetics,binding.ibKitchen,binding.ibFood,binding.ibContainer,binding.ibDress,binding.ibMore)
        val categories = listOf("가구/인테리어","가전제품","생활용품","욕실용품","도서/문구","화장품","주방용품","식품","용기/포장재","패션/잡화","기타")
        imageButtons.forEachIndexed { index, imageButton ->
            imageButton.setOnClickListener {
                val intent = Intent(activity, CategoryResultActivity::class.java)
                intent.putExtra("category", categories[index])
                startActivity(intent)
            }
        }

        binding.svCl1.setOnClickListener {

            startActivity(Intent(activity,SearchListActivity::class.java))

        }


        // 주소 검색
        binding.ibAddressSearch.setOnClickListener {

            dialogFragment.show(parentFragmentManager, "dialog_tag")



        }

        val searchView = binding.svCl1
        val icon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
        icon.isClickable = false



        dialogFragment.listener = object : DaumAddressDialogFragment.ChangeAddressListener {
            override fun onChange(roadAdd: String) {

                val sp: SharedPreferences = requireActivity().getSharedPreferences("UserPrefs",
                    Context.MODE_PRIVATE
                )

                var addressJson: String? = sp.getString("addresses", null)
                val address = Address(true, roadAdd)

                if (addressJson == null) {
                    // 기존에 저장된 주소가 없을 경우

                    val addressList = ArrayList<Address>()
                    addressList.add(address)

                    val gson = Gson()
                    addressJson = gson.toJson(addressList)
                    sp.edit().putString("addresses", addressJson).apply()



                } else {

                    val gson = Gson()
                    val addressListType = object : TypeToken<ArrayList<Address>>() {}.type

                    Log.d("asd", addressJson)

                    var addressList: ArrayList<Address> = gson.fromJson(addressJson, addressListType)

                    addressList = addressList.map { address ->
                        address.copy(selected = false)
                    }.toCollection(ArrayList())

                    addressList.add(address)


                    addressJson = gson.toJson(addressList)
                    sp.edit().putString("addresses", addressJson).apply()

                }

                binding.tvAddress.text = roadAdd
            }

        }


        binding.ibAddressOpenList.setOnClickListener {
            val sp: SharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

            val addressJson: String? = sp.getString("addresses", null)

            if (addressJson == null) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("알림")
                builder.setMessage("저장된 주소가 없습니다.")
                builder.setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()

            } else {
                val gson = Gson()
                val addressListType = object : TypeToken<List<Address>>() {}.type
                var addressList: ArrayList<Address> = gson.fromJson(addressJson, addressListType)

                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_address_open, null)
                val recyclerView = dialogView.findViewById<RecyclerView>(R.id.recyclerview)

                // RecyclerView에 표시할 데이터 리스트 생성
                val adapter = AddressRecyclerViewAdapter()
                adapter.setAddressList(addressList)

                adapter.listener = object : AddressRecyclerViewAdapter.OnItemClickListener {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onItemClicked(clickAddress: Address) {


                        addressList = addressList.map { address ->
                            address.copy(selected = false)
                        }.toCollection(ArrayList())


                        adapter.setAddressList(addressList)

                        adapter.notifyDataSetChanged()


                        clickAddress.selected = true


                        for ((index, address) in addressList.withIndex()) {
                            if (address.address == clickAddress.address) {
                                addressList[index] = clickAddress
                                adapter.notifyDataSetChanged()


                                val editAddressJson = gson.toJson(addressList)
                                sp.edit().putString("addresses", editAddressJson).apply()
                                binding.tvAddress.text = clickAddress.address

                                break

                            }
                        }
                    }
                }

                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                val builder = AlertDialog.Builder(requireContext())
                builder.setView(dialogView)
                builder.setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }

                val alertDialog = builder.create()
                alertDialog.show()

            }


        }

    }

    private fun updateBookmarkList(){
        // 북마크 목록 데이터를 업데이트합니다.
        val text = "1 2 3"
        serverConnectHelper.requestMultiTrashes = object : ServerConnectHelper.RequestTrashes{
            override fun onSuccess(trashes: List<Trash>) {
                Log.d("test", trashes.toString())
                binding.gridView.adapter = HomeAdapter(trashes,activity)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure() {
                Log.d("numberss2","씰패")
            }

        }
        serverConnectHelper.getMultiTrashes(text)

    }

    override fun onResume() {
        super.onResume()
        updateBookmarkList()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}