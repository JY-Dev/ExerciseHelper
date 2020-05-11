package shinhan.ac.kr.exercisehelper

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_routine_register.*
import kotlinx.android.synthetic.main.app_tool_bar.*
import kotlinx.android.synthetic.main.app_tool_bar.app_toolbar


class RoutineRegister : AppCompatActivity() {
    private var exercisePartRbGroup = mutableListOf<RadioGroup>()
    private var clearFlag = mutableListOf<Boolean>()
    private lateinit var multiRadioGroup: MultiRadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_register)
        init()

    }

    fun init(){
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        radioSetting()

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun radioSetting(){
        for(i in 0 until radioGroup_layout.childCount){
            exercisePartRbGroup.add(radioGroup_layout.getChildAt(i) as RadioGroup)
            clearFlag.add(true)
        }
        multiRadioGroup = MultiRadioGroup(exercisePartRbGroup,clearFlag,this)
    }
}
