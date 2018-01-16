package hlina.hnist.animationdeveloperoptionstest

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val d = resources.displayMetrics.density

        droid1.setOnClickListener {
            val translationX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, 200f*d)
            val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(droid1, translationX)
            objectAnimator.duration = 3000
            objectAnimator.interpolator = LinearInterpolator()
            objectAnimator.addUpdateListener {
                Log.e("aaaa", "object:" + objectAnimator.currentPlayTime)
            }
            objectAnimator.start()
        }

        droid2.setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(0f, 200f*d);
            valueAnimator.setDuration(3000)
            valueAnimator.interpolator = LinearInterpolator()
            valueAnimator.addUpdateListener {
                Log.e("aaaa", "value:" + valueAnimator.currentPlayTime)
                droid2.translationX = valueAnimator.animatedValue as Float
            }
            valueAnimator.start()
        }

        droid3.setOnClickListener {

            val fraction = (200f*d) / 3000f

            val startTime = System.currentTimeMillis()
            val handler = Handler()
            val r = object : Runnable {
                override fun run() {
                    val currentTime = System.currentTimeMillis()
                    val interval =  currentTime - startTime
                    Log.e("aaaa", "delay:" + interval)
                    if(interval >= 3000f){
                        handler.removeCallbacks(this);
                        return
                    }

                    droid3.translationX = fraction * interval

                    //30fps
                    handler.postDelayed(this, 33)
                }
            }
            handler.post(r)
        }

        move.setOnClickListener {
            droid1.performClick()
            droid2.performClick()
            droid3.performClick()
        }
    }
}
