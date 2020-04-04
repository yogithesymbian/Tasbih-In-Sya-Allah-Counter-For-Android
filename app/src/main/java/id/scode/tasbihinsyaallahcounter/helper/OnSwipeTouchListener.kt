package id.scode.tasbihinsyaallahcounter.helper

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

/**
 * @Authors scodeid | Yogi Arif Widodo
 * Created on 03 4/3/20 9:20 AM 2020
 * id.scode.tasbihinsyaallahcounter.util
 * https://github.com/yogithesymbian
 * Android Studio 3.6.1
 * Build #AI-192.7142.36.36.6241897, built on February 27, 2020
 * Runtime version: 1.8.0_212-release-1586-b4-5784211 amd64
 * VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
 * Linux 5.4.0-kali2-amd64
 * GC: ParNew, ConcurrentMarkSweep
 * Memory: 1246M
 * Cores: 4
 * Registry: ide.new.welcome.screen.force=true, ide.tree.ui.experimental=false, ide.balloon.shadow.size=0
 * Non-Bundled Plugins: JAXB 2.0 XJC generator, Key Promoter X, com.wix.eslint, mobi.hsz.idea.gitignore, com.vladsch.idea.multimarkdown, VectorDrawableImporter, com.chrisrm.idea.MaterialThemeUI, com.developerphil.adbidea, com.google.services.firebase, com.konifar.material_icon_generator, de.mprengemann.intellij.plugin.androidicons, com.jetbrains.edu, wu.seal.tool.jsontokotlin
 */
open class OnSwipeTouchListener(ctx: Context) : View.OnTouchListener {

    private val gestureDetector: GestureDetector

    companion object {

        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }


    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}
}