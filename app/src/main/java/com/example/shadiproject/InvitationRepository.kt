package com.example.shadiproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shadiproject.Pojo.PersonInfo
import com.example.shadiproject.Pojo.ResultItem
import com.example.shadiproject.Pojo.ShadiResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class InvitationRepository {

    private var allData = MutableLiveData<Resource<List<PersonInfo>>>()
    private var invitationDao: InvitationDao? = null
    private var status: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    constructor(application: Application) {
        val noteDatabase = InvitationDatabase.getInstance(application)
        invitationDao = noteDatabase.invitationDao()
    }

    fun getAllInvitation(): MutableLiveData<Resource<List<PersonInfo>>> {
        val invitation = invitationDao!!.getAllInvitation()
        if (invitation.size < 1) {
            allData.postValue(Resource.loading(arrayListOf()))
            fetchData()
        }else{
            allData.postValue(Resource.success(invitation))
        }
        return allData
    }

    fun updateStatus(status:String,id:String){
            invitationDao!!.updateStatus(status,id)
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
                    if (reponse!!.results!!.size > 0) {
                        callInsert(reponse.results!!)
                    } else {
                        allData.postValue(Resource.error("Failed",null))
                    }
                } else {
                    allData.postValue(Resource.error("Failed",null))
                }
            }

            override fun onFailure(call: Call<ShadiResponse>, t: Throwable) {
                allData.postValue(Resource.error("Failed",null))
            }

        })

    }

    fun callInsert(result:List<ResultItem>){
        var data = convertToInvitation((result))
        insert(data)
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
        GlobalScope.launch {
            invitationDao!!.insert(list)
        }
        allData.postValue(Resource.success(list))
    }
}