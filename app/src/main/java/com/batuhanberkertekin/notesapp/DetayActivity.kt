package com.batuhanberkertekin.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts.Data
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.batuhanberkertekin.notesapp.data.Notes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detay.*
import kotlinx.android.synthetic.main.activity_detay.editNot3
import kotlinx.android.synthetic.main.activity_detay.editNot4
import kotlinx.android.synthetic.main.activity_kayit.*

class DetayActivity : AppCompatActivity() {
    private lateinit var   notes : Notes
    private lateinit var db : DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)


          toolbarDetay.title = "Not Detay"
          setSupportActionBar(toolbarDetay)
              db = DataBaseHelper(this)


         notes = intent.getSerializableExtra("data") as Notes

        editDersDetay.setText(notes.ders_adi)
        editNot3.setText(notes.not2.toString())
        editNot4.setText(notes.not1.toString())






    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_duzenle ->{
                val ders_adi = editDersDetay.text.toString().trim()
                val not1 = editNot4.text.toString().trim()
                val not2 = editNot3.text.toString().trim()


                if(TextUtils.isEmpty(ders_adi)){
                    Toast.makeText(this,"Ders adı giriniz ",Toast.LENGTH_SHORT).show()
                    return false
                }
                if(TextUtils.isEmpty(not1)){
                    Toast.makeText(this,"1.notu giriniz ",Toast.LENGTH_SHORT).show()
                    return false
                }
                if(TextUtils.isEmpty(not2)){
                    Toast.makeText(this,"2.notu giriniz",Toast.LENGTH_SHORT).show()
                    return false
                }

                NotesDao().updateNot(db,notes.not_id,ders_adi,not1.toInt(),not2.toInt())



                val intent = Intent(applicationContext,MainActivity::class.java)

                startActivity(intent)

                return true
            }
            R.id.action_sil -> {
                 Snackbar.make(toolbarDetay,"Emin misiniz ? ",Snackbar.LENGTH_LONG).setAction("Evet"){
                   NotesDao().deleteNot(db,notes.not_id)

                     val intent = Intent(applicationContext,MainActivity::class.java)

                     startActivity(intent)
                 }.show()


                return true
            }

            else -> {


                return false
            }

        }


    }
}