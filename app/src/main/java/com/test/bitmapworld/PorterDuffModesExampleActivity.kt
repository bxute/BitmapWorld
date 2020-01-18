/*
 * Developer email: hiankit.work@gmail.com
 * GitHub: https://github.com/bxute
 */

package com.test.bitmapworld

import android.graphics.*
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_porter_duff_modes.*

class PorterDuffModesExampleActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        nameToDescription[(view as TextView).text as String]?.let { description.text = it }
        nameToXFerMode[view.text as String]?.let { composeBitmapForMode(it) }
    }

    val nameToXFerMode = mapOf(
        "SRC" to PorterDuff.Mode.SRC,
        "SRC_IN" to PorterDuff.Mode.SRC_IN,
        "SRC_OVER" to PorterDuff.Mode.SRC_OVER,
        "SRC_ATOP" to PorterDuff.Mode.SRC_ATOP,
        "DST" to PorterDuff.Mode.DST,
        "DST_IN" to PorterDuff.Mode.DST_IN,
        "DST_OVER" to PorterDuff.Mode.DST_OVER,
        "DST_ATOP" to PorterDuff.Mode.DST_ATOP,
        "CLEAR" to PorterDuff.Mode.CLEAR,
        "SRC_OUT" to PorterDuff.Mode.SRC_OUT,
        "DST_OUT" to PorterDuff.Mode.DST_OUT,
        "XOR" to PorterDuff.Mode.XOR
    )

    val nameToDescription = mapOf(
        "SRC" to "The source pixels replace the destination pixels.",
        "SRC_IN" to "Keeps the source pixels that cover the destination pixels, discards the remaining source and destination pixels. ",
        "SRC_OVER" to "The source pixels are drawn over the destination pixels.",
        "SRC_ATOP" to "Discards the source pixels that do not cover destination pixels.",
        "DST" to "The source pixels are discarded, leaving the destination intact.",
        "DST_IN" to "Keeps the destination pixels that cover source pixels, discards the remaining source and destination pixels. ",
        "DST_OVER" to "The source pixels are drawn behind the destination pixels. ",
        "DST_ATOP" to "Discards the destination pixels that are not covered by source pixels.",
        "CLEAR" to "Destination pixels covered by the source are cleared to 0. ",
        "SRC_OUT" to "Keeps the source pixels that do not cover destination pixels. ",
        "DST_OUT" to "Keeps the destination pixels that are not covered by source pixels. ",
        "XOR" to "Discards the source and destination pixels where source pixels cover destination pixels. "
    )

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_porter_duff_modes)
        modeSelector.onItemSelectedListener = this
        val aa = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.porter_duff_modes)
        )
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        modeSelector.adapter = aa
    }

    private fun composeBitmapForMode(mode: PorterDuff.Mode) {
        val sourceBitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
        val destinationBitmap = BitmapFactory.decodeResource(resources, R.drawable.square)
        //create empty bitmap
        val resultBitmap =
            Bitmap.createBitmap(
                (destinationBitmap.width * 1.5).toInt(),
                (destinationBitmap.height * 1.5).toInt(),
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
        paint.xfermode = PorterDuffXfermode(mode)

        //We want the cicle to be in center
        val left = 0
        val top = (resultBitmap.height - sourceBitmap.height)
        //now draw source bitmap
        canvas.drawBitmap(
            sourceBitmap,
            left.toFloat(),
            top.toFloat(),
            paint
        )

        //show bitmap on view
        result_img.setImageBitmap(resultBitmap)
    }
}
