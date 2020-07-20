package com.example.shadiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.shadiproject.Pojo.PersonInfo
import java.lang.Exception

class ResultActivity : AppCompatActivity() {

    @BindView(R.id.personImage) lateinit var personImage: ImageView
    @BindView(R.id.personName) lateinit var personName: TextView
    @BindView(R.id.personAge) lateinit var personAge: TextView
    @BindView(R.id.personPhone) lateinit var personPhone: TextView
    @BindView(R.id.personEmail) lateinit var personEmail: TextView
    @BindView(R.id.personCountry) lateinit var personCountry: TextView
    @BindView(R.id.statusButtonView) lateinit var statusButtonView: ConstraintLayout
    @BindView(R.id.statusMessage) lateinit var statusMessage: TextView
    @BindView(R.id.acceptButton) lateinit var acceptButton: Button
    @BindView(R.id.rejectButton) lateinit var rejectButton: Button
    var invitationViewModel:InvitationviewModel ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        invitationViewModel = ViewModelProviders.of(this).get(InvitationviewModel::class.java)
        ButterKnife.bind(this)
        updateview()
    }

    fun updateview(){
        if(intent != null && intent.extras != null) {
            var personInfo = intent.extras?.getSerializable("personInfo") as PersonInfo

            acceptButton.setOnClickListener({
                updateStatus("Accepted",personInfo.id!!)
            })
            rejectButton.setOnClickListener({
                updateStatus("Rejected",personInfo.id!!)
            })

            try {
                Glide.with(this)
                    .load(personInfo.picture!!.large)
                    .thumbnail(0.1f)
                    .into(personImage);
            }catch (e: Exception){
                //
            }
            var isUpdated = true
            if(personInfo.status ==  null || personInfo.status == "null"){
                isUpdated = false
            }

            if(isUpdated){
                statusButtonView.visibility = View.GONE
                statusMessage.visibility = View.VISIBLE
                statusMessage.text = String.format(getString(R.string.status_message), personInfo.status)
            }else{
                statusButtonView.visibility = View.VISIBLE
                statusMessage.visibility = View.GONE
            }
            personName.text = String.format(getString(R.string.name_string), personInfo.name)
            personAge.text = String.format(getString(R.string.age_string), personInfo.age)
            personPhone.text = String.format(getString(R.string.phone_string), personInfo.phone)
            personEmail.text = String.format(getString(R.string.email_string), personInfo.email)
            personCountry.text = String.format(getString(R.string.country_string), personInfo.country)
        }
    }

    fun updateStatus(status:String, id:String){
        invitationViewModel!!.updateStatus(status,id)
        statusButtonView.visibility = View.GONE
        statusMessage.visibility = View.VISIBLE
        statusMessage.text = String.format(getString(R.string.status_message), status)
    }
}
