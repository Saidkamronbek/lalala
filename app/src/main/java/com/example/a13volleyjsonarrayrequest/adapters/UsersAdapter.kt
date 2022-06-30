package com.example.a13volleyjsonarrayrequest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a13volleyjsonarrayrequest.databinding.ItemUserBinding
import com.example.a13volleyjsonarrayrequest.models.User

class UsersAdapter(var list: List<User>): RecyclerView.Adapter<UsersAdapter.Vh>() {

    inner class Vh(var itemUserBinding: ItemUserBinding) :RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: User) {
            itemUserBinding.tv1.text = user.name
            itemUserBinding.tv2.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int  = list.size
}