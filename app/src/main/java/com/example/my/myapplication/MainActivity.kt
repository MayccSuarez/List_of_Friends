package com.example.my.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var edtName   :EditText
    private lateinit var btnSubmit :Button
    private lateinit var lvPersons :ListView
    private lateinit var adapter: PersonAdapterListView
    private  var persons: ArrayList<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "My List of Friends"

        bindViews()
        loadArrayListPersons()
        fillListViewPersons()
        addListenerBtnSubmit()
        addListenerListViewPersons()
    }

    private fun bindViews (){
         edtName      = findViewById(R.id.edtName)
         btnSubmit    = findViewById(R.id.btnSubmit)
         lvPersons    = findViewById(R.id.lvPersons)
    }

    private fun loadArrayListPersons () {
        persons.add(Person("Maycc Suàrez", R.drawable.icon))
        persons.add(Person("Martìn Suàrez", R.drawable.icon))
        persons.add(Person("Kerly Suàrez",  R.drawable.icon))
        persons.add(Person("Tayron Suàrez",  R.drawable.icon))
    }

    private fun fillListViewPersons(){
        //adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        adapter = PersonAdapterListView(this, persons)
        lvPersons.adapter  = adapter
    }

    private fun addListenerBtnSubmit (){

        btnSubmit.setOnClickListener(View.OnClickListener {

            val name = edtName.text

            if (checkNameInput(name)){
                loadPersonToListViewPersons(name.toString())
                edtName.setText("")
            }

        })
    }

    private fun addListenerListViewPersons () {

        var txt = ""

        lvPersons.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            txt = "Amigo ${i + 1} -> ${persons[i].name}"
            showAlert(txt)
        }

        lvPersons.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
            deletePerson(i)
            txt = "Amigo ${persons[i].name} Eliminado!!!"
            showAlert(txt)

            true
        }
    }


    private fun checkNameInput(name :Editable) : Boolean{

        if (name.isNotEmpty())
            return true

        return false
    }

    private fun loadPersonToListViewPersons(name :String){
        persons.add(Person(name, R.drawable.icon))
        adapter.notifyDataSetChanged()
    }

    private fun deletePerson (i :Int){
        persons.removeAt(i)
        adapter.notifyDataSetChanged()
    }

    private fun showAlert (txt :String) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }

}
