package com.example.baseproject.presentation.face

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.baseproject.R
import com.example.baseproject.databinding.FaceDetectActBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions

class FaceDetectAct : AppCompatActivity() {

    lateinit var binding: FaceDetectActBinding

    companion object {
        const val URL_IMAGE = "https://media-cdn.hahalolo.com/2020/12/03/22/5c205932d577a7125c98425e201203223402T2.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this@FaceDetectAct)
        binding = DataBindingUtil.setContentView(this@FaceDetectAct, R.layout.face_detect_act)
        setImage()
        binding.detectBt.setOnClickListener {
            detectImage()
        }

    }

    private fun setImage() {
        Glide.with(this@FaceDetectAct).apply {
            load(URL_IMAGE)
                .into(binding.photoImg)
        }

    }

    private fun detectImage() {
        // High-accuracy landmark detection and face classification
        val detectorOptions = FirebaseVisionFaceDetectorOptions.Builder()
            .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
            .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
            .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
            .build()

        // Real-time contour detection of multiple faces
        val realTimeOpts = FirebaseVisionFaceDetectorOptions.Builder()
            .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
            .build()

        val detector = FirebaseVision
            .getInstance()
            .getVisionFaceDetector(realTimeOpts)

        var visionImage: FirebaseVisionImage? = null

        Glide.with(this@FaceDetectAct).apply {
            asBitmap()
                .load(URL_IMAGE)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        visionImage = FirebaseVisionImage.fromBitmap(resource)
                        Log.i("================","visionImage")
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })

        }
        visionImage?.let {
            detector.detectInImage(it).addOnFailureListener{e->
                Log.i("====================","addOnFailureListener: $e")
            }


            detector.detectInImage(it).addOnSuccessListener {
                Log.i("====================","addOnSuccessListener: ${it?.size}")
                it?.forEach {
                    val contour = it?.getContour(FirebaseVisionFaceContour.FACE)
                    contour?.apply {
                        val canvas = Canvas(visionImage!!.bitmap)
                        val myPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                        myPaint.color = Color.parseColor("#99ff0000")
                        val path = Path()
                        path.moveTo(contour.points[0].x, contour.points[0].y)
                        contour.points.forEach {
                            path.lineTo(it.x, it.y)
                        }
                        path.close()
                        canvas.drawPath(path, myPaint)
                        binding.photoImg.setImageBitmap(visionImage!!.bitmap)
                    }
                }
            }

        }



    }
}