package com.example.tasklistapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapplication.model.UserData
import com.example.tasklistapplication.view.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var taskAdapter:TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userList= ArrayList()
        addsBtn=findViewById(R.id.fab)
        recv = findViewById(R.id.tasksRecycler)
        taskAdapter= TaskAdapter(this,userList)
        recv.layoutManager=LinearLayoutManager(this)
        recv.adapter=taskAdapter
        addsBtn.setOnClickListener{addInfo()}

    }

    private fun addInfo() {
        val infilter=LayoutInflater.from(this)
        val v=infilter.inflate(R.layout.add_item,null)
        val taskName=v.findViewById<EditText>(R.id.taskName)
        val taskDesc=v.findViewById<EditText>(R.id.taskDesc)

        val addDialog=AlertDialog.Builder(this)
        addDialog.setView(v)
       addDialog.setPositiveButton("Ok"){

           dialog, _->
           val names=taskName.text.toString()
           val description=taskDesc.text.toString()
           userList.add(UserData("Task: $names","Task Description: $description"))
           taskAdapter.notifyDataSetChanged()
           Toast.makeText(this,"Adding Task Information Success",Toast.LENGTH_SHORT).show()
           dialog.dismiss()
       }
        addDialog.setNegativeButton("cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}