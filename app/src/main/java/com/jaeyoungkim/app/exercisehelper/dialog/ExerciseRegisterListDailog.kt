package com.jaeyoungkim.app.exercisehelper.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jaeyoungkim.app.exercisehelper.R
import kotlinx.android.synthetic.main.exercise_register_list_dialog.*


class ExerciseRegisterListDailog(context: Context,title:String,onCallBackListner : (title : String, exerciseKind :String,exerciseSetNum :Int,exercisePerformNum : Int) -> Unit) : Dialog(context) {

    private var mContext = context
    private var items = mutableListOf<String>()
    private var exerciseKind = ""
    init {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.exercise_register_list_dialog)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCanceledOnTouchOutside(false)
        exercise_list_dialog_title.text = title
        setArray(title)
        
        cancel_btn.setOnClickListener {
            this.dismiss()
        }

        ok_btn.setOnClickListener {
            if(exercise_set_edit.text.toString()!="" && perform_edit.text.toString()!="") {
                if(exerciseKind == "직접입력") {
                    if(exercise_kind_edit.text.toString()=="") Toast.makeText(mContext,"운동 종류를 입력해주세요.",Toast.LENGTH_SHORT).show()
                    else {
                        exerciseKind = exercise_kind_edit.text.toString()
                        onCallBackListner(title,exerciseKind,Integer.parseInt(exercise_set_edit.text.toString()),Integer.parseInt(perform_edit.text.toString()))
                        this.dismiss()
                    }
                } else {
                    onCallBackListner(title,exerciseKind,Integer.parseInt(exercise_set_edit.text.toString()),Integer.parseInt(perform_edit.text.toString()))
                    this.dismiss()
                }

            } else {
                Toast.makeText(mContext,"운동 세트 및 횟수를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }

        val arrayAdapter = ArrayAdapter(mContext,android.R.layout.simple_spinner_dropdown_item,items)
        exercise_kind_spinner.adapter = arrayAdapter
        exercise_kind_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(items[position]=="직접입력") exercise_kind_edit.visibility = View.VISIBLE
                else exercise_kind_edit.visibility = View.GONE
                exerciseKind = items[position]
            }

        }

        this.show()
    }



    private fun setArray(title:String?){
        when(title){
            "팔 운동" -> items = mContext.resources.getStringArray(R.array.arm_exercise).toMutableList()
            "다리 운동" -> items = mContext.resources.getStringArray(R.array.leg_exercise).toMutableList()
            "어깨 운동" -> items = mContext.resources.getStringArray(R.array.shoulder_exercise).toMutableList()
            "가슴 운동" -> items = mContext.resources.getStringArray(R.array.chest_exercise).toMutableList()
            "복근 운동" -> items = mContext.resources.getStringArray(R.array.abs_exercise).toMutableList()
            "등 운동" -> items = mContext.resources.getStringArray(R.array.back_exercise).toMutableList()
        }

    }
}