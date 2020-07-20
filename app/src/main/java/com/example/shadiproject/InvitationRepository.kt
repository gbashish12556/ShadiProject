package com.example.shadiproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shadiproject.Pojo.PersonInfo
import com.example.shadiproject.Pojo.ResultItem
import com.example.shadiproject.Pojo.ShadiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class InvitationRepository {

    private var invitationDao: InvitationDao? = null
    private var status: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    constructor(application: Application) {
        val noteDatabase = InvitationDatabase.getInstance(application)
        invitationDao = noteDatabase.invitationDao()
    }

    fun getAllInvitation(): LiveData<List<PersonInfo>> {
        val invitation = invitationDao!!.getAllInvitation()
        if (invitation.getValue() == null) {
            fetchData()
        }
        return invitation
    }

    fun updateStatus(status:String,id:String){
        Thread(Runnable {
            invitationDao!!.updateStatus(status,id)
        }).start()
    }

    fun getCurrentStatus(): MutableLiveData<Boolean> {
        return status!!
    }


    fun fetchData() {

        val call1 = RetrofitClient.instance.api.getAllResponse()

        call1.enqueue(object : Callback<ShadiResponse> {

            override fun onResponse(
                call: Call<ShadiResponse>,
                response: Response<ShadiResponse>
            ) {

                if (response.code() == 200) {
                    val reponse = response.body()
                    if (reponse.results!!.size > 0) {
                        callInsert(reponse.results!!)
                    } else {
                        status!!.setValue(false)
                    }
                } else {
                    status!!.setValue(false)
                }
            }

            override fun onFailure(call: Call<ShadiResponse>, t: Throwable) {
                status!!.setValue(false)
            }

        })

    }

    fun callInsert(result:List<ResultItem>){
            insert(convertToInvitation(result))
    }
    fun convertToInvitation(response: List<ResultItem>): List<PersonInfo> {
        val issues = ArrayList<PersonInfo>()
        for (i in response.indices) {
            val item = response[i]

            var country = "NA"
            if(item.locations !=  null && item.locations!!.country != null){
                country = item.locations!!.country!!
            }
            issues.add(
                PersonInfo(
                    item!!.login!!.uuid,item.gender, item.name!!.title+" "+item.name!!.first+" "+item.name!!.last,item.email!!,item.dob!!.date,
                    item.dob!!.age,item.phone!!, item.cell!!, country, item.picture, Date().time, false, null
                )
            )
        }
        return issues
    }

    fun insert(list:List<PersonInfo>){
        Thread(Runnable {
            invitationDao!!.insert(list)
        }).start()
    }
}