package com.icarus.recycle_app.ui.info.content.trash_place

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icarus.recycle_app.databinding.FragmentTrashPlaceBinding
import com.icarus.recycle_app.dto.Address
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.ui.home.DaumAddressDialogFragment
import com.icarus.recycle_app.utils.ServerConnectHelper

class TrashPlaceFragment : Fragment() {

    private var _binding: FragmentTrashPlaceBinding? = null
    private val binding get() = _binding!!



    companion object {
        fun newInstance() = TrashPlaceFragment()
    }

    private lateinit var viewModel: TrashPlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrashPlaceBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TrashPlaceViewModel::class.java)


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



        binding.tvSearch.setOnClickListener {
            val dialogFragment = DaumAddressDialogFragment()

            dialogFragment.listener = object : DaumAddressDialogFragment.ChangeAddressListener {
                override fun onChange(roadAdd: String) {
                    binding.tvAddress.text = roadAdd
                    binding.tvSearch.text = roadAdd

                }
            }
            dialogFragment.show(parentFragmentManager, "dialog_tag")
        }


        binding.tvAddress.setOnClickListener {
            binding.tvSearch.text = binding.tvAddress.text
        }

        binding.btnApply.setOnClickListener {
            val serverConnector = ServerConnectHelper()
            serverConnector.requestRegionInfo = object : ServerConnectHelper.RequestRegionInfo {
                override fun onSuccess(regionInfoList: List<RegionInfo>) {
                    for (regionInfo in regionInfoList) {

                        Log.d("asd", regionInfo.toString())
                    }


                }

                override fun onFailure() {

                    Log.d("asd", "씰패")
                }

            }
            serverConnector.getTrashPlace(binding.tvAddress.text.toString())
        }

        binding.tvSearch.text = binding.tvAddress.text


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}