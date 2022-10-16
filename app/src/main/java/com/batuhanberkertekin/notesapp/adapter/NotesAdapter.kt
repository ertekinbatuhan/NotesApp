package com.batuhanberkertekin.notesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.batuhanberkertekin.notesapp.DetayActivity
import com.batuhanberkertekin.notesapp.R
import com.batuhanberkertekin.notesapp.data.Notes
import kotlinx.android.synthetic.main.recyler_view.view.*

class NotesAdapter( private val context : Context , private val myList : ArrayList<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesView>() {



    inner class NotesView(view : View) : RecyclerView.ViewHolder(view)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesView {
      val design = LayoutInflater.from(context).inflate(R.layout.recyler_view,parent,false)

        return NotesView(design)

    }

    override fun onBindViewHolder(holder: NotesView, position: Int) {
      val notesList = myList.get(position)

        holder.itemView.dersText.text = notesList.ders_adi
        holder.itemView.note1Text.text = notesList.not1.toString()
        holder.itemView.note2Text.text = notesList.not2.toString()
         holder.itemView.cardView.setOnClickListener{


            val intent = Intent(context,DetayActivity::class.java)

             intent.putExtra("data",notesList)
            context.startActivity(intent)
         }







    }

    override fun getItemCount(): Int {
       return myList.size
    }

}