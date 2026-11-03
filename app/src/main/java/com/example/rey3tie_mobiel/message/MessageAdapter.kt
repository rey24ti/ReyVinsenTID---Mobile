package com.example.rey3tie_mobiel.message // Sesuaikan package Anda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rey3tie_mobiel.R
import com.example.rey3tie_mobiel.message.MessageModel
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    private val context: Context,
    private val listMessage: List<MessageModel>
) : BaseAdapter() {

    override fun getCount(): Int = listMessage.size
    override fun getItem(position: Int): Any = listMessage[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
            holder = ViewHolder()
            holder.imgAvatar = view.findViewById(R.id.avatarImg)
            holder.tvSender = view.findViewById(R.id.textSender)
            holder.tvMessage = view.findViewById(R.id.textMessage)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val data = listMessage[position]


        holder.tvSender?.text = "${data.senderName}, ${data.age} thn"
        holder.tvMessage?.text = data.messageText

        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.imgAvatar!!)


        view.setOnClickListener {

            Snackbar.make(
                view,
                "Pesan dari ${data.senderName}: [${data.messageText}, Usia: ${data.age}]",
                Snackbar.LENGTH_SHORT
            ).show()


        }

        return view!!
    }

    private class ViewHolder {
        var imgAvatar: ImageView? = null
        var tvSender: TextView? = null
        var tvMessage: TextView? = null
    }
}