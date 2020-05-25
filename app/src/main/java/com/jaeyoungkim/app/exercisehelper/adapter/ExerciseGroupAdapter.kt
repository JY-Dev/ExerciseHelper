package com.jaeyoungkim.app.exercisehelper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.jaeyoungkim.app.exercisehelper.R
import com.jaeyoungkim.app.exercisehelper.room.ExerRoutine

class ExerciseGroupAdapter(context: Context, exerRoutine : List<ExerRoutine>) : BaseAdapter() {
    private var mExerRoutine = exerRoutine
    private var mContext = context
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view :View = convertView ?: LayoutInflater.from(mContext).inflate(R.layout.exercise_group_list_item,null)
        val groupNameTv = view.findViewById<TextView>(R.id.group_name_tv)
        groupNameTv.text = mExerRoutine[position].group
        return view
    }

    override fun getItem(position: Int): Any {
        return mExerRoutine[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mExerRoutine.size
    }
}