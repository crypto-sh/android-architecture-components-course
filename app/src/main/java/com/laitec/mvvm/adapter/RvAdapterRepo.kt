package com.laitec.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laitec.mvvm.R
import com.laitec.mvvm.databinding.RepoItemBinding
import com.laitec.mvvm.model.RepoSearch

class RvAdapterRepo(val repos : List<RepoSearch>) : RecyclerView.Adapter<RvAdapterRepo.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(repos[position])
    }

    class Holder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo : RepoSearch){
            binding.repo = repo
        }
    }
}