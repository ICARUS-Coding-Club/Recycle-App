package com.icarus.recycle_app.ui.search.image

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R
import com.icarus.recycle_app.utils.CameraHelper
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SearchImageFragment : Fragment() {

    private lateinit var capturedImageView: ImageView

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_GALLERY_IMAGE = 2 // 갤러리 요청 코드 추가
    private var photoUri: Uri? = null
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

    private lateinit var cameraHelper: CameraHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchImageViewModel::class.java)

        cameraHelper = CameraHelper(requireActivity())

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),
            cameraHelper.REQUEST_IMAGE_CAPTURE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search_image, container, false)
        capturedImageView = view.findViewById(R.id.ivCameraResult)  // 참조 얻기

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments?.getInt(ARG_TYPE)

        when (type) {
            0 -> takePhotoFromCamera()
            1 -> openGallery() // 갤러리를 열어 사진을 선택
            else -> {
                // 다른 클래스나 메서드 사용 등의 다른 동작 수행
            }
        }
    }
    private fun takePhotoFromCamera() {
        val intent = cameraHelper.dispatchTakePictureIntent()
        intent?.let {
            startActivityForResult(it, cameraHelper.REQUEST_IMAGE_CAPTURE)
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) // 필요한 호출 추가

        if (requestCode == cameraHelper.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val photoUri = cameraHelper.getPhotoUri()
            Glide.with(requireActivity()).load(photoUri).into(capturedImageView)
        } else if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK) {
            val selectedImage = data?.data
            Glide.with(requireActivity()).load(selectedImage).into(capturedImageView)
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(SearchImageViewModel::class.java)
//
//        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_IMAGE_CAPTURE)
//
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        dispatchTakePictureIntent()
//    }
//
//
//    @SuppressLint("QueryPermissionsNeeded")
//    private fun dispatchTakePictureIntent() {
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
//            // startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//
//            // 이미지 파일을 생성하는 코드
//            val photoFile: File? = try {
//                createImageFile()
//            } catch (ex: IOException) {
//                null
//            }
//
//            photoFile?.also {
//                photoUri = FileProvider.getUriForFile(
//                    requireActivity(),
//                    "com.icarus.recycle_app.fileprovider",
//                    it
//                )
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }
//    }
//
//    private fun createImageFile(): File {
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//            "JPEG_${timeStamp}_",
//            ".jpg",
//            storageDir
//        )
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Glide.with(requireActivity()).load(photoUri).into(capturedImageView)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            val imageBitmap = data?.extras?.get("data") as Bitmap
//            Glide.with(requireActivity()).load(imageBitmap).into(capturedImageView)
//        }
//    }

}