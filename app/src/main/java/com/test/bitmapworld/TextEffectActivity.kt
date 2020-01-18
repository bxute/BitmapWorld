/*
 * Developer email: hiankit.work@gmail.com
 * GitHub: https://github.com/bxute
 */

package com.test.bitmapworld

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_special_effect.*


class TextEffectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_effect)
        curateText()
        animateView(movingView1)
        animateView(movingView2)
        animateView(movingView3)
    }

    private fun animateView(view: View) {
        ObjectAnimator.ofFloat(view, "translationY", 800f).apply {
            duration = 2000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 100
            start()
        }
    }

    private fun curateText() {
        val destinationBitmap = BitmapFactory.decodeResource(resources, R.drawable.button)
        //create empty bitmap
        val resultBitmap =
            Bitmap.createBitmap(
                destinationBitmap.width,
                destinationBitmap.height,
                Bitmap.Config.ARGB_8888
            )
        //create new canvas
        val canvas = Canvas(resultBitmap)
        //paint object
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        //draw destination Bitmap first
        canvas.drawBitmap(
            destinationBitmap,
            (resultBitmap.width - destinationBitmap.width).toFloat(),
            0f,
            paint
        )

        //select Transfer mode
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)

        paint.color = Color.BLACK
        paint.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            60f,
            resources.displayMetrics
        )
        val top = destinationBitmap.height / 2
        val left = destinationBitmap.width / 8
        canvas.drawText("HELLO!", left.toFloat(), top.toFloat(), paint)

        //show bitmap on view
        button.setImageBitmap(resultBitmap)
    }
}
