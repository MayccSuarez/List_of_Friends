package com.example.my.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_layout.view.*

class PersonAdapterListView(var context: Context, var persons :ArrayList<Person>) :BaseAdapter() {

    override fun getView(position: Int, row: View?, parent: ViewGroup?): View {

        val viewHolder :ViewHolder?
        var view :View? = row

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        } else {
            viewHolder = view.tag as ViewHolder
        }

        val person = getItem(position) as Person
        viewHolder.tvName.text = person.name
        viewHolder.ivIcon.setImageResource(person.image)

        return view!!
    }

    override fun getItem(position: Int): Any {
        return persons[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return persons.count()
    }

    class ViewHolder(view: View) {

        var tvName: TextView = view.tvName
        var ivIcon: ImageView = view.ivIcon
    }
}