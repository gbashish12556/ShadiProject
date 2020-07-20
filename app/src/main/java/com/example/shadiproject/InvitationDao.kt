package com.example.shadiproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shadiproject.Pojo.PersonInfo

@Dao
interface InvitationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(invitations: List<PersonInfo>)

    @Query("SELECT*FROM invitation_table")
    fun getAllInvitation(): LiveData<List<PersonInfo>>

}
