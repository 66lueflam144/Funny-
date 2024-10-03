package com.example.crazylightning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

}



//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var circularProgressBar: ProgressBar
//    private lateinit var linearProgressBar: ProgressBar
//    private var progress = 0
//    private lateinit var seekBar: SeekBar
//    private lateinit var ratingBar: RatingBar
//    private lateinit var radioGroup: RadioGroup
//    private lateinit var increaseProgress1Button: Button
//    private lateinit var increaseProgress2Button: Button
//    private lateinit var resetButton: Button
//    private lateinit var signMsg: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.bar_spaceship)
//
//        //绑定
//        circularProgressBar = findViewById(R.id.circularProgressBar)
//        linearProgressBar = findViewById(R.id.linearProgressBar)
//        seekBar = findViewById(R.id.seekBar)
//        ratingBar = findViewById(R.id.ratingBar)
//        radioGroup = findViewById(R.id.radioGroup)
//        increaseProgress1Button = findViewById(R.id.increaseProgress1)
//        increaseProgress2Button = findViewById(R.id.increaseProgress2)
//        resetButton = findViewById(R.id.reset)
//        signMsg = findViewById(R.id.sign)
//
//
//        signMsg.text = "YSM"
//
//        //第一进度条
//        increaseProgress1Button.setOnClickListener {
//            increaseProgress(10)
//        }
//
//        //第二进度条
//        increaseProgress2Button.setOnClickListener {
//            increaseProgress(20)
//        }
//
//        // 复原按钮
//        resetButton.setOnClickListener {
//            resetProgress()
//        }
//
//        // 增加进度的按钮
//        val btnIncreaseProgress: Button = findViewById(R.id.btnIncreaseProgress)
//        btnIncreaseProgress.setOnClickListener {
//            progress += 10  // 每次点击增加10%的进度
//            if (progress > 100) progress = 100  // 防止超过最大值
//            circularProgressBar.progress = progress
//        }
//
//        //分配绑定不同的进度条
//        radioGroup.setOnCheckedChangeListener { _, checkedId ->
//            when (checkedId) {
//                R.id.radioCircle -> {
//                    showCircularProgressBar()
//                }
//                R.id.radioLine -> {
//                    showLinearProgressBar()
//                }
//                R.id.radioSeekBar -> {
//                    showSeekBar()
//                }
//                R.id.radioRatingBar -> {
//                    showRatingBar()
//                }
//            }
//        }
//    }
//    // 增加进度
//    private fun increaseProgress(amount: Int) {
//        circularProgressBar.progress = (circularProgressBar.progress + amount).coerceAtMost(circularProgressBar.max)
//        linearProgressBar.progress = (linearProgressBar.progress + amount).coerceAtMost(linearProgressBar.max)
//        seekBar.progress = (seekBar.progress + amount).coerceAtMost(seekBar.max)
//        ratingBar.rating = (ratingBar.rating + amount / 20f).coerceAtMost(ratingBar.numStars.toFloat())
//    }
//
//    // 复原进度
//    private fun resetProgress() {
//        circularProgressBar.progress = 0
//        linearProgressBar.progress = 0
//        seekBar.progress = 0
//        ratingBar.rating = 0f
//    }
//
//    // 显示圆形进度条
//    private fun showCircularProgressBar() {
//        circularProgressBar.visibility = ProgressBar.VISIBLE
//        linearProgressBar.visibility = ProgressBar.GONE
//        seekBar.visibility = SeekBar.GONE
//        ratingBar.visibility = RatingBar.GONE
//    }
//
//    // 显示水平进度条
//    private fun showLinearProgressBar() {
//        circularProgressBar.visibility = ProgressBar.GONE
//        linearProgressBar.visibility = ProgressBar.VISIBLE
//        seekBar.visibility = SeekBar.GONE
//        ratingBar.visibility = RatingBar.GONE
//    }
//
//    // 显示 SeekBar
//    private fun showSeekBar() {
//        circularProgressBar.visibility = ProgressBar.GONE
//        linearProgressBar.visibility = ProgressBar.GONE
//        seekBar.visibility = SeekBar.VISIBLE
//        ratingBar.visibility = RatingBar.GONE
//    }
//
//    // 显示 RatingBar
//    private fun showRatingBar() {
//        circularProgressBar.visibility = ProgressBar.GONE
//        linearProgressBar.visibility = ProgressBar.GONE
//        seekBar.visibility = SeekBar.GONE
//        ratingBar.visibility = RatingBar.VISIBLE
//    }
//
////        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar
////        textsView = findViewById<TextView>(R.id.text_view)
//
////        val btn = findViewById<Button>(R.id.show_button)
////        btn.setOnClickListener() {
////            progressBar!!.visibility = View.VISIBLE
////            i = progressBar!!.progress
////            Thread(Runnable {
////                while (i < 100) {
////                    i += 1
////                    handler.post(Runnable {
////                        progressBar!!.progress = i
////                        textsView!!.text = i.toString() + "/" + progressBar!!.max
////                    })
////                    try {
////                        Thread.sleep(100)
////                    }catch (e: InterruptedException) {
////                        e.printStackTrace()
////                    }
////                }
////
////                progressBar!!.visibility = View.INVISIBLE
////            }).start()
////        }
//
//
////        val seek = findViewById<SeekBar>(R.id.seekbar1)
////
////        seek?.setOnSeekBarChangeListener(
////            object : SeekBar.OnSeekBarChangeListener {
////                override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
////                    val output = "当前进度: $progress"
////                    Toast.makeText(this@MainActivity, output, Toast.LENGTH_SHORT).show()
////                }
////
////                override fun onStartTrackingTouch(p0: SeekBar?) {
////                    //TODO("Not yet implemented")
////
////                }
////
////                override fun onStopTrackingTouch(p0: SeekBar?) {
////                    //TODO("Not yet implemented")
////
////                    val output = "Progress is:" + seek.progress + "%"
////
////                    Toast.makeText(this@MainActivity, output, Toast.LENGTH_SHORT).show()
////                }
////            }
////        )
//
//
////        imageView = findViewById(R.id.imageView)
////        val btnEnlarge = findViewById<Button>(R.id.btnEnlarge)
////        val btnOriginal = findViewById<Button>(R.id.btnOriginal)
////        val btnShrink = findViewById<Button>(R.id.btnShrink)
////
////        //定义点击动作
////        btnEnlarge.setOnClickListener {
////            scale *= 1.5f
////            imageView?.apply {
////                scaleX = scale
////                scaleY = scale
////            }
////        }
////
////        btnOriginal.setOnClickListener {
////            scale = 1.0f
////            imageView?.apply {
////                scaleX = scale
////                scaleY = scale
////            }
////        }
////
////        btnShrink.setOnClickListener {
////            scale *= 0.5f
////            imageView?.apply {
////                scaleX = scale
////                scaleY = scale
////            }
////        }
//    }


