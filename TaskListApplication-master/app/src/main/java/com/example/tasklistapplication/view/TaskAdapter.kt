package com.example.tasklistapplication.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapplication.R
import com.example.tasklistapplication.model.UserData

class TaskAdapter (val c:Context, val userList:ArrayList<UserData>):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    inner class TaskViewHolder(val v: View): RecyclerView.ViewHolder(v){
        var name:TextView
        var description:TextView
        var mMenus:ImageView



        init{
             name=v.findViewById<TextView>(R.id.taskTitle)
             description=v.findViewById<TextView>(R.id.taskSubTitle)
            mMenus=v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{popupMenus(it)}
        }

        private fun popupMenus(v:View) {
            val position=userList[absoluteAdapterPosition]
           val popupMenus=PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v=LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val name=v.findViewById<EditText>(R.id.taskName)
                        val desc=v.findViewById<EditText>(R.id.taskDesc)

                              AlertDialog.Builder(c)
                                  .setView(v)
                                  .setPositiveButton("Ok"){
                                      dialog,_->
                                      position.taskName=name.text.toString()
                                      position.taskSub=desc.text.toString()
                                      notifyDataSetChanged()
                                      Toast.makeText(c,"Task Information is Edited", Toast.LENGTH_SHORT).show()
                                      dialog.dismiss()
                                  }
                                  .setNegativeButton("Cancel"){
                                      dialog,_->
                                      dialog.dismiss()
                                  }

                                  .create()
                                  .show()

                        true
                    }
                    R.id.deleteText->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure you want to delete this Task?")
                            .setPositiveButton("Yes"){
                                dialog,_->
                                userList.removeAt(absoluteAdapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Task has been deleted", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible=true
            val menu=popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item,parent,false)
        return TaskViewHolder(v)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val newList=userList[position]
        holder.name.text=newList.taskName
        holder.description.text=newList.taskSub
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}