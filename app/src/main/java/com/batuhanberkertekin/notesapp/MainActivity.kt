package com.batuhanberkertekin.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.batuhanberkertekin.notesapp.adapter.NotesAdapter
import com.batuhanberkertekin.notesapp.data.Notes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : NotesAdapter
    private lateinit var myList : ArrayList<Notes>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

           val dt = DataBaseHelper(this)



        toolbar.title = "Notes App"

         setSupportActionBar(toolbar)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        myList = ArrayList()

        myList = NotesDao().allNot(dt)

       adapter = NotesAdapter(this,myList)
        recyclerView.adapter = adapter

        var total = 0

        for(i in myList){
             total = total + (i.not1 + i.not2) / 2

        }

        if(total != 0){
            toolbar.subtitle= "Ortalama : ${total / myList.size}"
        }


        floatingActionButton.setOnClickListener {
            val intent  = Intent(applicationContext,KayitActivity::class.java)

            startActivity(intent)
        }


    }

    override fun onBackPressed() {

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }
}