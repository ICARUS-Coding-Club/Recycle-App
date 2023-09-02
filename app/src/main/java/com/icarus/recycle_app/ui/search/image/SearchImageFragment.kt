package com.icarus.recycle_app.ui.search.image

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.databinding.FragmentSearchImageBinding
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.utils.CameraHelper
import java.io.ByteArrayOutputStream

class SearchImageFragment : Fragment() {


    private var _binding: FragmentSearchImageBinding? = null

    private val binding get() = _binding!!


    private val REQUEST_GALLERY_IMAGE = 2 // 갤러리 요청 코드 추가

    init {
        Log.d("ViewModel", "Initialized")
    }

    companion object {

        private const val ARG_TYPE = "click_btn"
        fun newInstance(type: Int?): SearchImageFragment {
            val fragment = SearchImageFragment()
            val args = Bundle()

            // 값이 null이 아닐 경우에만 번들에 넣는다.
            type?.let {
                args.putInt(ARG_TYPE, it)
            }

            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: SearchImageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchImageViewModel::class.java]


        viewModel.cameraHelper = CameraHelper(requireActivity())

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),
            viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchImageBinding.inflate(inflater, container, false)

        initListener()


        if (viewModel.isCameraOpened.value != true) {
            when (arguments?.getInt(ARG_TYPE)) {
                0 -> takePhotoFromCamera()
                1 -> openGallery()
                else -> {
                    // 다른 동작
                }
            }
        }

        Log.d("asd", viewModel.isCameraOpened.value.toString())




        return binding.root
    }

    /**
     * 리스너 등록
     */
    private fun initListener() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnSend.setOnClickListener {
            val image = Image(AppManager.getUid(), viewModel.imageByteArray)
            viewModel.uploadImageToServer(image)
        }

        viewModel.uploadStatus.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                Log.d("asd", "전송 성공")
            } else {
                Log.d("asd", "전송 실패")
            }
        })
    }



    private fun takePhotoFromCamera() {
        val intent = viewModel.cameraHelper.dispatchTakePictureIntent()
        intent?.let {
            startActivityForResult(it, viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE)
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_IMAGE)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewModel.uri = viewModel.cameraHelper.getPhotoUri()
            Glide.with(requireActivity()).load(viewModel.uri).into(binding.ivCameraResult)

            // Uri를 Bitmap으로 변환
            val imageBitmap = createBitmap(viewModel.uri)
            convertBitmapToByteArray(imageBitmap)

        } else if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK) {
            viewModel.uri = data?.data
            Glide.with(requireActivity()).load(viewModel.uri).into(binding.ivCameraResult)

            // Uri를 Bitmap으로 변환
            val imageBitmap = createBitmap(viewModel.uri)
            convertBitmapToByteArray(imageBitmap)

        }
        viewModel.isCameraOpened.value = true
    }

    private fun createBitmap(uri: Uri?): Bitmap {
        return MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
    }

    private fun convertBitmapToByteArray(imageBitmap: Bitmap) {
        // Bitmap을 ByteArray로 변환
        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        viewModel.imageByteArray = byteArrayOutputStream.toByteArray()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}