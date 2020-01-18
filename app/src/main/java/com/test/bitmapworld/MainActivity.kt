/*
 * Developer email: hiankit.work@gmail.com
 * GitHub: https://github.com/bxute
 */

package com.test.bitmapworld

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        porterDuffModes.setOnClickListener {
            open(PorterDuffModesExampleActivity::class.java)
        }
        specialEffects.setOnClickListener {
            //later
            open(TextEffectActivity::class.java)
        }
        cropImage.setOnClickListener {
            open(ImageCropActivity::class.java)
        }
    }

    private fun open(classType: Class<*>) {
        val intent = Intent(this, classType)
        startActivity(intent)
    }
}
