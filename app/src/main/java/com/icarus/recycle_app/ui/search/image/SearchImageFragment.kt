package com.icarus.recycle_app.ui.search.image

import android.Manifest
import android.animation.ObjectAnimator
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
import android.view.ViewOutlineProvider
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSearchImageBinding
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.dto.TrashImage
import com.icarus.recycle_app.ui.search.SearchViewModel
import com.icarus.recycle_app.ui.search.image.trash_request.ImageResultActivity
import com.icarus.recycle_app.utils.CameraHelper
import java.io.ByteArrayOutputStream

class SearchImageFragment : Fragment() {


    private var _binding: FragmentSearchImageBinding? = null

    private val binding get() = _binding!!


    private val REQUEST_GALLERY_IMAGE = 2 // 갤러리 요청 코드 추가

    private lateinit var viewModel: SearchImageViewModel


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SearchImageViewModel::class.java]

        viewModel.cameraHelper = CameraHelper(requireActivity())

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),
            viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE)

//        viewModel.navigateToNextActivity.observe(this) { shouldNavigate ->
//            if (shouldNavigate) {
//
//
//                // Reset the navigation trigger
//                viewModel.navigateToNextActivity.value = false
//            }
//        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchImageBinding.inflate(inflater, container, false)
        binding.ivCameraResult.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.ivCameraResult.clipToOutline = true

        val downArrow = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_down1_black)
        val upArrow = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_up1_balck)


        initListener()

        val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        viewModel.isClickedTextInfo.observe(requireActivity()) {
            if (viewModel.isClickedTextInfo.value == true) {
                binding.tvInfoChild.startAnimation(slideDown)
                binding.tvInfoChild.visibility = View.VISIBLE
                binding.tvInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, upArrow, null)
                ObjectAnimator.ofInt(binding.nestedScrollView, "scrollY", binding.tvInfoChild.bottom).apply {
                    duration = 1000
                    start()
                }


            } else {
                binding.tvInfoChild.startAnimation(slideUp)
                binding.tvInfoChild.visibility = View.INVISIBLE
                binding.tvInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, downArrow, null)
            }
        }

        viewModel.imageResultUri.observe(requireActivity()) {
            if (viewModel.imageResultUri.value != null) {
                Glide.with(requireActivity())
                    .load(viewModel.imageResultUri.value)
                    .into(binding.ivCameraResult)
            }
        }

        viewModel.isCameraOpened.observe(requireActivity()) {
            if (viewModel.isCameraOpened.value == true) {
                binding.progressBar.visibility = View.GONE
                binding.fabBack.visibility = View.VISIBLE
                binding.tvInfo.visibility = View.VISIBLE
                binding.btnSend.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.VISIBLE
                binding.fabBack.visibility = View.INVISIBLE
                binding.tvInfo.visibility = View.INVISIBLE
                binding.btnSend.visibility = View.INVISIBLE
            }
        }

        if (viewModel.isCameraOpened.value == false) {
            binding.progressBar.visibility = View.VISIBLE
            when (arguments?.getInt(ARG_TYPE)) {
                0 -> takePhotoFromCamera()
                1 -> openGallery()
                else -> {
                    // 다른 동작
                }
            }
        }

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


        viewModel.trashItems.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (viewModel.uploadStatus.value == true) {
                Log.d("asd", "전송 성공")
                // Start your next Activity here
                val intent = Intent(activity,ImageResultActivity::class.java)

                val bundle = Bundle()



                val trashArray = viewModel.trashItems.value
                val trashArrayList = trashArray?.let { ArrayList<Trash>(it) }

                bundle.putParcelableArrayList("myKey", trashArrayList)
                intent.putExtras(bundle)



                startActivity(intent)
            } else {
                Log.d("asd", "전송 실패")
            }
        })

        binding.tvInfo.setOnClickListener {
            viewModel.toggleIsClickedTextInfo()
        }
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
            viewModel.imageResultUri.value = viewModel.cameraHelper.getPhotoUri()

            // Uri를 Bitmap으로 변환
            val imageBitmap = createBitmap(viewModel.imageResultUri.value)
            convertBitmapToByteArray(imageBitmap)

        } else if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK) {
            viewModel.imageResultUri.value = data?.data

            // Uri를 Bitmap으로 변환
            val imageBitmap = createBitmap(viewModel.imageResultUri.value)
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