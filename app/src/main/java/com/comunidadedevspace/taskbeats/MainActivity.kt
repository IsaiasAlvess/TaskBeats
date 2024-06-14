package com.comunidadedevspace.taskbeats

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import java.io.InvalidObjectException
import java.io.Serializable
import java.io.ObjectStreamException



class MainActivity : AppCompatActivity() {

    private  val taskList = arrayListOf(
        Task( 0,"title0 ", "description0"),
        Task( 1,"title1 ", "description1"),
    )
    private val adapter:TaskListAdapter = TaskListAdapter(::openTaskDetailView)

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
         if (result.resultCode != Activity.RESULT_OK) {
             // you will get the result here in result.date
              val data = result.data
              val taskAction = data?.getSerializableExtra(TASK_ACTION_RESULT) as TaskAction
              val task: Task = taskAction.task
             taskList.remove(task)

             adapter.submit(taskList)




         }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvtaskList: RecyclerView = findViewById(R.id.RV_task_List)


        val adapter:TaskListAdapter = TaskListAdapter(::openTaskDetailView)
        adapter.submit(taskList)
        rvtaskList.adapter = adapter


    }

     private fun openTaskDetailView(task: Task){
         val intent = TaskDatailActivity.start(this, task)
         startForResult.launch(intent)
     }
}

sealed class ActionType : Serializable {
    object DELETE : ActionType()
    object UPDATE : ActionType()
    object CREATE : ActionType()
}


data class  TaskAction(
    val task: Task,
    val actionType: ActionType
) : Serializable

const val  TASK_ACTION_RESULT = "TASK_ACTION_RESULT"