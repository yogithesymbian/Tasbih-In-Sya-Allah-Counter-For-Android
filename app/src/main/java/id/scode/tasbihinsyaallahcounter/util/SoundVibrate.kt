package id.scode.tasbihinsyaallahcounter.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat.getSystemService

/**
 * @Authors scodeid | Yogi Arif Widodo
 * Created on 03 4/3/20 9:32 AM 2020
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
fun vibrateTasbih(ctx: Context){
    val vibrator = ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (vibrator.hasVibrator()) { // Vibrator availability checking
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100L, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
        } else {
            vibrator.vibrate(100L) // Vibrate method for below API Level 26
        }
    }
}
fun vibrateTasbih3(ctx: Context){
    val vibrator = ctx.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (vibrator.hasVibrator()) { // Vibrator availability checking
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(300L, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
        } else {
            vibrator.vibrate(300L) // Vibrate method for below API Level 26
        }
    }
}