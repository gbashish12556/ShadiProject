package com.example.shadiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.shadiproject.Pojo.PersonInfo
import java.util.*

class MainActivity : AppCompatActivity() {

    private var invitationViewModel: InvitationviewModel? = null
    private var allInvitation: List<PersonInfo>? = null
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        intialiseViewModel()
    }

    fun intialiseViewModel(){

        invitationViewModel = ViewModelProviders.of(this).get(InvitationviewModel::class.java)

        recyclerView.setLayoutManager(LinearLayoutManager(this))

        allInvitation = ArrayList<PersonInfo>()

        val noteAdapter = InvitationAdapter(this, allInvitation)

        (noteAdapter as InvitationAdapter).getPersonPublishSubject().subscribe{personInfo->
            startNextActivity(personInfo)
        }

        recyclerView.setAdapter(noteAdapter)

        invitationViewModel!!.getAllInvitation().observe(this, androidx.lifecycle.Observer { result->
            if (result.size > 0) {
                noteAdapter.resetList(result)
            }
        })

    }

    fun startNextActivity(personInfo:PersonInfo){
        val intent = Intent(this, ResultActivity::class.java)
        var bundle = Bundle()
        bundle.putSerializable("personInfo", personInfo)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
