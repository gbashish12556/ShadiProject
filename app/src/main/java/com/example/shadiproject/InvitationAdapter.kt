package com.example.shadiproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shadiproject.Pojo.PersonInfo
import java.lang.Exception

class InvitationAdapter(private val mContext: Context, private var mItems: List<PersonInfo>?) : RecyclerView.Adapter<InvitationAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return mItems!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val personImage: ImageView
        val personName: TextView
        val personAge: TextView

        init {

            personImage = itemView.findViewById(R.id.personImage) as ImageView
            personName = itemView.findViewById(R.id.personName) as TextView
            personAge = itemView.findViewById(R.id.personAge) as TextView

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val postView = inflater.inflate(R.layout.invitation_item, parent, false)

        return ViewHolder(postView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems!![position]

        try {
            Glide.with(mContext)
                .load(item.picture!!.thumbnail)
                .thumbnail(0.1f)
                .into(holder.personImage);
        }catch (e:Exception){
            //
        }
        holder.personAge.text = String.format(mContext.getString(R.string.age), item.age)
        holder.personName.text = item.name

    }
    private fun getItem(adapterPosition: Int): PersonInfo {
        return mItems!![adapterPosition]
    }

    fun resetList(issues: List<PersonInfo>) {
        mItems = issues
        notifyDataSetChanged()
    }


}