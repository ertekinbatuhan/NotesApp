package com.batuhanberkertekin.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.provider.ContactsContract.Contacts.Data
import com.batuhanberkertekin.notesapp.data.Notes

class NotesDao {
    @SuppressLint("Range")
    fun allNot(dt : DataBaseHelper) : ArrayList<Notes>{
        val wt = dt.writableDatabase

        val notList = ArrayList<Notes>()

        val x = wt.rawQuery("SELECT * FROM notlar",null)


        while (x.moveToNext()){
            val not = Notes(x.getInt(x.getColumnIndex("not_id")),
            x.getString(x.getColumnIndex("ders_adi")),
            x.getInt(x.getColumnIndex("not1")),
            x.getInt(x.getColumnIndex("not2")))
            notList.add(not)
        }
          return notList
    }



    fun deleteNot(dt : DataBaseHelper, not_id : Int){

        val wt = dt.writableDatabase

        wt.delete("notlar","not_id=?", arrayOf(not_id.toString()))
        wt.close()



    }

    fun addNot(dt : DataBaseHelper ,  ders_adi : String , not1 : Int , not2 : Int){


        val wt = dt.writableDatabase
        val values = ContentValues()
        values.put("ders_adi",ders_adi)
        values.put("not1",not1)
        values.put("not2",not2)

        wt.insertOrThrow("notlar",null,values)
        wt.close()
    }


    fun updateNot(dt : DataBaseHelper , not_id : Int , ders_adi : String , not1 : Int , not2 : Int){


        val wt = dt.writableDatabase
        val values = ContentValues()
        values.put("ders_adi",ders_adi)
        values.put("not1",not1)
        values.put("not2",not2)

        wt.update("notlar",values,"not_id=?", arrayOf(not_id.toString()))

        wt.close()
    }
}