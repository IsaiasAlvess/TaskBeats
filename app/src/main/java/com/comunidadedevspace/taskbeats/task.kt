package com.comunidadedevspace.taskbeats
import java.io.Serializable

data class Task(
    val id: Int,
    val title:String,
    val descripton: String
    ):Serializable

