package com.example.shadiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shadiproject.Pojo.PersonInfo
import java.util.*

class MainActivity : AppCompatActivity() {

    private var invitationViewModel: InvitationviewModel? = null
    private var allInvitation: List<PersonInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intialiseViewModel()
    }

    fun intialiseViewModel(){

        invitationViewModel = ViewModelProviders.of(this).get(InvitationviewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        allInvitation = ArrayList<PersonInfo>()

        val noteAdapter = InvitationAdapter(this, allInvitation)

        recyclerView.setAdapter(noteAdapter)

        invitationViewModel!!.getAllInvitation().observe(this, androidx.lifecycle.Observer { result->
            if (result.size > 0) {
                noteAdapter.resetList(result)
            }
        })

    }
}
