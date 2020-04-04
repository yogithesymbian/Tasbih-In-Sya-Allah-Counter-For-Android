package id.scode.tasbihinsyaallahcounter.ui.home.tasbihcounter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.shape.MaterialShapeUtils
import id.scode.tasbihinsyaallahcounter.R
import id.scode.tasbihinsyaallahcounter.data.JmlTasbih
import id.scode.tasbihinsyaallahcounter.data.NumberTasbih
import id.scode.tasbihinsyaallahcounter.helper.OnSwipeTouchListener
import id.scode.tasbihinsyaallahcounter.util.vibrateTasbih
import id.scode.tasbihinsyaallahcounter.util.vibrateTasbih3
import kotlinx.android.synthetic.main.activity_tasbih.*

class TasbihActivity : AppCompatActivity() {
//    , GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener

    companion object {
        //        private const val DEBUG_TAG = "Gestures"
        private val TAG_LOG: String? = TasbihActivity::class.simpleName
    }

    private var items: MutableList<NumberTasbih> = mutableListOf()

    var stateTasbih = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasbih)

        spinnerDataJmlTasbih() // jumlah tasbih load
        spinnerDataChooseTasbih()

        val stateUpDown = true
        var numberDefault = 0
        // val numberDefaultTemp = 0

        btn_reset.setOnClickListener {
            numberDefault = 0
            txt_tasbih_counter.text = numberDefault.toString()
        }

        img_tasbih_counter.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                Log.d(TAG_LOG, "onTouch")
                return super.onTouch(v, event)
            }

            override fun onSwipeRight() {
                Log.d(TAG_LOG, "onSwipeRight")
                super.onSwipeRight()
            }

            override fun onSwipeLeft() {
                Log.d(TAG_LOG, "onSwipeLeft")
                super.onSwipeLeft()
            }

            override fun onSwipeTop() {
                Log.d(TAG_LOG, "onSwipeTop")

                // avoid minus number
                if (numberDefault >= 0 && numberDefault < items.size -1 ) {
                    numberDefault += 1
                } else if (numberDefault < 0) {
                    numberDefault = 0
                    numberDefault += 1
                }

                Log.d(TAG_LOG, "number data is : $numberDefault")
                txt_tasbih_counter.text = items[numberDefault].num

                if (numberDefault == 3 && numberDefault == items.size - 1){
                    vibrateTasbih3(this@TasbihActivity)
                }
                else if (numberDefault == 33 && numberDefault == items.size - 1){
                    vibrateTasbih3(this@TasbihActivity)
                }
                else if (numberDefault == 100 && numberDefault == items.size - 1){
                    vibrateTasbih3(this@TasbihActivity)
                } else {
                    vibrateTasbih(this@TasbihActivity)
                }

                super.onSwipeTop()
            }

            override fun onSwipeBottom() {
                Log.d(TAG_LOG, "onSwipeBottom")

                // avoid minus number
                if(numberDefault == 0){
                    numberDefault = 0
                }
                else if (numberDefault >= 0 && numberDefault < items.size) {
                    numberDefault -= 1
                } else if (numberDefault < 0) {
                    numberDefault = 0
                } else if (numberDefault == items.size) {
                    numberDefault -= 2
                }

                Log.d(TAG_LOG, "number data is : $numberDefault")
                txt_tasbih_counter.text = items[numberDefault].num

                vibrateTasbih(this@TasbihActivity)

                super.onSwipeBottom()
            }

        })

        spinner_jml_tasbih.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selected: Any = parent.getItemAtPosition(position)
                Log.d(TAG_LOG, "onItemSelected : $selected")
                if (selected == "Jumlah Tasbih 3x"){
                    txt_tasbih_counter_limit.text = "/003"
                    stateTasbih = 3
                }
                else if (selected == "Jumlah Tasbih 33x"){
                    txt_tasbih_counter_limit.text = "/033"
                    stateTasbih = 33
                }
                else if (selected == "Jumlah Tasbih 100x"){
                    txt_tasbih_counter_limit.text = "/100"
                    stateTasbih = 100
                }
                tvJmlTasbih(stateTasbih) //set tasbih ( jumlah ) by state
            }
        }
        spinner_choose_tasbih.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selected: Any = parent.getItemAtPosition(position)
                Log.d(TAG_LOG, "onItemSelected : $selected")
            }

        }

    }

    private fun spinnerDataChooseTasbih() {

        val listChooseTasbih = mutableListOf<String>()

        val tasbihList: Array<String> = resources.getStringArray(R.array.tasbih_list)

        for (i in tasbihList.indices) {
            listChooseTasbih.add(
                JmlTasbih(tasbihList[i]).jml.toString()
            )
        }

        val adapterChooseTasbih =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listChooseTasbih)
        adapterChooseTasbih.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_choose_tasbih.adapter = adapterChooseTasbih

    }

    private fun tvJmlTasbih(stateTasbih: Int) {
        when(stateTasbih){
            3 -> {
                val tasbihCounterNum: Array<String> = resources.getStringArray(R.array.tasbih_counter_num_3)
                items.clear()

                for (i in tasbihCounterNum.indices) {
                    items.add(
                        NumberTasbih(tasbihCounterNum[i])
                    )
                }
            }
            33 -> {
                val tasbihCounterNum: Array<String> = resources.getStringArray(R.array.tasbih_counter_num33)
                items.clear()

                for (i in tasbihCounterNum.indices) {
                    items.add(
                        NumberTasbih(tasbihCounterNum[i])
                    )
                }
            }
            100 -> {
                val tasbihCounterNum: Array<String> = resources.getStringArray(R.array.tasbih_counter_num_100)
                items.clear()

                for (i in tasbihCounterNum.indices) {
                    items.add(
                        NumberTasbih(tasbihCounterNum[i])
                    )
                }
            }
        }
    }

    private fun spinnerDataJmlTasbih() {

        val listJmlTasbih = mutableListOf<String>()

        val tasbihCounterJml: Array<String> = resources.getStringArray(R.array.tasbih_jml)

        for (i in tasbihCounterJml.indices) {
            listJmlTasbih.add(
                "Jumlah Tasbih ${JmlTasbih(tasbihCounterJml[i]).jml.toString()}"
            )
        }

//        listJmlTasbih.add(arrayListCategory[i].cat1) //category 1

        val adapterJmlTasbih =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listJmlTasbih)
        adapterJmlTasbih.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_jml_tasbih.adapter = adapterJmlTasbih

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        MaterialShapeUtils.setParentAbsoluteElevation(View(applicationContext))
    }
}
