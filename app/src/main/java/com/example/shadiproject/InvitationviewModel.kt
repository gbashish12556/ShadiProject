package com.example.shadiproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shadiproject.Pojo.PersonInfo

class InvitationviewModel(private val invitationRepository: InvitationRepository) :ViewModel() {

    fun getAllInvitation(): MutableLiveData<Resource<List<PersonInfo>>> {
            return invitationRepository.getAllInvitation()
    }

    fun updateStatus(status:String,id:String){
        invitationRepository!!.updateStatus(status,id)
    }


}
