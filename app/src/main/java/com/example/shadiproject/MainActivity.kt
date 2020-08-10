package com.example.shadiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.shadiproject.Pojo.PersonInfo
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var invitationViewModel: InvitationviewModel? = null
    private lateinit var adapter: InvitationAdapter

    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setupUI()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            getDataAndDisplay()
        }
    }
    fun startNextActivity(personInfo:PersonInfo){
        val intent = Intent(this, ResultActivity::class.java)
        var bundle = Bundle()
        bundle.putSerializable("personInfo", personInfo)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            InvitationAdapter(this,
                arrayListOf()
            )
        adapter.getPersonPublishSubject().subscribe{
            startNextActivity(it)
        }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    suspend fun getDataAndDisplay(){
        var data=    setupObserver()
        data.observe(this, androidx.lifecycle.Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    suspend fun setupObserver() :MutableLiveData<Resource<List<PersonInfo>>>{
        return GlobalScope.async(Dispatchers.IO) {
             invitationViewModel!!.getAllInvitation()
        }.await()
    }

    private fun renderList(users: List<PersonInfo>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        invitationViewModel = ViewModelProviders.of(this, ViewModelFactory(InvitationRepository(application)
        )).get(InvitationviewModel::class.java)
    }
}
