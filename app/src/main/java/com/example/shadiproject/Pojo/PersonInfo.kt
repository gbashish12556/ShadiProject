package com.example.shadiproject.Pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.util.*

@Entity(tableName = "invitation_table")
data class PersonInfo(

    @PrimaryKey
    var id:String,
    var gender: String?,
    var name: String?,
    var email: String?,
    var dob: String?,
    var age: Int?,
    var phone: String?,
    var cell: String?,

    @TypeConverters(PictureConverter::class)
    var picture: Picture?,

    var created_at: Long?,

    var is_action_taken:Boolean?,

    var status:String?

) : Serializable