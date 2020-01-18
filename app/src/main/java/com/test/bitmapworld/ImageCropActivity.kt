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

    private fun cutOurSemiCircle(): Bitmap {
        //1. Create Bitmap from image resource that we need to crop
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.image)

        //2.
        //   Currently its empty, we will draw our elements here.
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
        canvas.drawBitmap(originalBitmap,0f,0f,paint)
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
        canvas.drawBitmap(originalBitmap,0f,0f,paint)
        return resultBitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_crop)
        modified_image_iv.setImageBitmap(cropImage())
    }
}
