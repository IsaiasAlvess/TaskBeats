package com.comunidadedevspace.taskbeats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(
    private val openTaskDetailView: (task: Task) -> Unit
): RecyclerView.Adapter<TaskListViewHolder>() {

     private var  listtask: List<Task> = emptyList()

     fun submit(list: List<Task>){
         listtask = list
         notifyDataSetChanged()
     }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TaskListViewHolder {
        val view: View = LayoutInflater
          .from(parent.context)
          .inflate(R.layout.item_task, parent, false )
        return TaskListViewHolder(view)
    }

    override fun getItemCount(): Int {
          return listtask.size
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = listtask[position]
        holder.bind(task, openTaskDetailView)

    }
}

class TaskListViewHolder(
    private val view:View) : RecyclerView.ViewHolder(view){

    val tvTaskTitle = view.findViewById<TextView>(R.id.tv_Task_Title)
    val tvtaskdiscription = view.findViewById<TextView>(R.id.tv_Task_Drescription)

    fun bind(task: Task,
             openTaskDetailView: (task: Task) -> Unit){
        tvTaskTitle.text = task.title
        tvtaskdiscription.text = task.descripton

        view.setOnClickListener{
            openTaskDetailView.invoke(task)
        }


    }


}