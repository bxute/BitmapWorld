/*
 * Developer email: hiankit.work@gmail.com
 * GitHub: https://github.com/bxute
 */

package com.test.bitmapworld

import android.graphics.*
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_crop.*

class ImageCropActivity : AppCompatActivity() {

    private fun diamondShapedBitmap(): Bitmap {
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(resultBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.DKGRAY
        val path = Path()
        val height = originalBitmap.height.toFloat()
        val width = height

        path.moveTo(width / 2, height / 5)
        // Upper left path
        path.cubicTo(
            5 * width / 14, 0f,
            0f, height / 15,
            width / 28, 2 * height / 5
        )

        // Lower left path
        path.cubicTo(
            width / 14, 2 * height / 3,
            3 * width / 7, 5 * height / 6,
            width / 2, height
        )

        // Lower right path
        path.cubicTo(
            4 * width / 7, 5 * height / 6,
            13 * width / 14, 2 * height / 3,
            27 * width / 28, 2 * height / 5
        )

        // Upper right path
        path.cubicTo(
            width, height / 15,
            9 * width / 14, 0f,
            width / 2, height / 5
        )
        canvas.drawPath(path, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        return resultBitmap
    }

    private fun circularBitmap(): Bitmap {
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(resultBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.DKGRAY
        val path = Path()
        val centerX = originalBitmap.width.toFloat() / 2
        val centerY = originalBitmap.height.toFloat() / 2
        val radius = centerY
        path.addCircle(centerX, centerY, radius, Path.Direction.CW)
        canvas.drawPath(path, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        return resultBitmap
    }

    private fun cutOurSemiCircleBitmap(): Bitmap {
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(resultBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        val path = Path()
        val centerX = originalBitmap.width.toFloat() / 2
        val centerY = 0f
        val radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100f,
            resources.displayMetrics
        )
        path.addCircle(centerX, centerY, radius, Path.Direction.CW)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas.drawPath(path, paint)
        return resultBitmap
    }

    private fun cropImage(): Bitmap {
        //1. Create Bitmap from image resource that we need to crop
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.image)

        //2. Create A Empty Mutable Bitmap of same height and width as of original bitmap
        // Currently its empty, we will draw our elements here.
        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )

        //3. Create a New Canvas and Paint Object
        val canvas = Canvas(resultBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.DKGRAY

        //We need to draw a rounded rectangular first
        //For that we will need a path (We can draw with other options but i will choose path)
        val path = Path()
        //Add Path for a rounded rect

        //RectF defines the bounds of our rectangle
        val rectF = RectF(0f, 0f, originalBitmap.width.toFloat(), originalBitmap.height.toFloat())
        //We want 20Dp corner radius
        val cornerRadius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            resources.displayMetrics
        )
        path.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)
        canvas.drawPath(path, paint)

        //Magic happens here
        //Set Tranfer Mode of Bitmap
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        //now draw original image which we want to crop
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        return resultBitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_crop)
        rounded_rect_iv.setImageBitmap(cropImage())
        circular_iv.setImageBitmap(circularBitmap())
        cutout_iv.setImageBitmap(cutOurSemiCircleBitmap())
        heart_iv.setImageBitmap(diamondShapedBitmap())
    }
}
