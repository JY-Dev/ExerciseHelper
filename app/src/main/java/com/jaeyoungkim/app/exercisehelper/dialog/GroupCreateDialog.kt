package com.jaeyoungkim.app.exercisehelper.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Toast
import com.jaeyoungkim.app.exercisehelper.R
import kotlinx.android.synthetic.main.group_create_dialog.*

class GroupCreateDialog(context: Context,onCreateCallback : (groupName : String) -> Unit) : Dialog(context) {
    init {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.group_create_dialog)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCanceledOnTouchOutside(false)

        cancel_btn.setOnClickListener {
            this.dismiss()
        }

        create_btn.setOnClickListener {
            if(group_et.text.toString() == ""){
                Toast.makeText(context,"그룹 이름을 입력해주세요.",Toast.LENGTH_SHORT).show()
            } else {
                onCreateCallback(group_et.text.toString())
                this.dismiss()
            }
        }

        this.show()

    }
}