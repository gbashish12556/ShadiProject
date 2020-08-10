package com.example.shadiproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val invitationRepository: InvitationRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InvitationviewModel::class.java)) {
            return InvitationviewModel(invitationRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}