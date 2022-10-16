package com.batuhanberkertekin.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts.Data
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kayit.*
import kotlinx.android.synthetic.main.activity_main.*

class KayitActivity : AppCompatActivity() {
    private lateinit var dt : DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)


        dt = DataBaseHelper(this)

         toolbarKayit.title = "Not kayıt"
        setSupportActionBar(toolbarKayit)


        saveButton.setOnClickListener {

           val dersAdi = editDers1.text.toString().trim()
            val not1 = editNot3.text.toString().trim()
            val not2 = editNot4.text.toString().trim()

            if(TextUtils.isEmpty(dersAdi)){
                Toast.makeText(this,"Boş bırakmayınız lütfen ",Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            if(TextUtils.isEmpty(not1)){
                Toast.makeText(this,"Boş bırakmayınız lütfen ",Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            if(TextUtils.isEmpty(not2)){
                Toast.makeText(this,"Boş bırakmayınız lütfen ",Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            NotesDao().addNot(dt,dersAdi,not1.toInt(),not2.toInt())


            val intent = Intent(applicationContext,MainActivity::class.java)
            finish()
            startActivity(intent)
        }

    }
}