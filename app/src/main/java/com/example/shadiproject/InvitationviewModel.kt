package com.example.shadiproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shadiproject.Pojo.PersonInfo

class InvitationviewModel(application: Application) : AndroidViewModel(application) {

    private var invitationRepository: InvitationRepository? = null
    private val allInvitation: LiveData<List<PersonInfo>>? = null

    private var status: MutableLiveData<Boolean>? = null

    fun getAllInvitation(): LiveData<List<PersonInfo>> {

        return invitationRepository!!.getAllInvitation()
    }

    fun updateStatus(status:String,id:String){
        invitationRepository!!.updateStatus(status,id)
    }


    init {
        invitationRepository = InvitationRepository(application)
    }


}
