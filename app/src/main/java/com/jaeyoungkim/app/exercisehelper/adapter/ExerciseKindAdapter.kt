package com.jaeyoungkim.app.exercisehelper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.activity.routine.RoutineRegister02

class ExerciseKindAdapter(exerciseKindArray : MutableList<RoutineRegister02.ExerciseKind>, context: Context) : BaseAdapter() {
    var exerciseKindList = exerciseKindArray
    private var mContext = context
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(mContext).inflate(R.layout.exercise_kind_list_item,null)
        val titleTv = view.findViewById<TextView>(R.id.title_tv)
        val exerciseKindTv= view.findViewById<TextView>(R.id.exercise_kind_tv)
        val setNumTv = view.findViewById<TextView>(R.id.set_num_tv)
        val performNumTv = view.findViewById<TextView>(R.id.perform_num_tv)
        val deleteBtn = view.findViewById<Button>(R.id.del_btn)

        titleTv.text = exerciseKindList[position].title
        exerciseKindTv.text = exerciseKindList[position].exerciseKind
        setNumTv.text = exerciseKindList[position].exerciseSetNum.toString()
        performNumTv.text = exerciseKindList[position].exercisePerformNum.toString()

        deleteBtn.setOnClickListener {
            exerciseKindList.removeAt(position)
            this.notifyDataSetChanged()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return exerciseKindList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return exerciseKindList.size
    }

}