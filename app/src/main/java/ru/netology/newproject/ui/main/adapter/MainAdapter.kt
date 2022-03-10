package ru.netology.newproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*
import ru.netology.newproject.R
import ru.netology.newproject.data.model.ResultResponseDto

class MainAdapter(
    private val users: ArrayList<ResultResponseDto>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int =
        users.size


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: ResultResponseDto) {
            itemView.userNameView.text = user.name
            itemView.userSpeciesView.text = user.species

            Glide.with(itemView.userAvatarView.context)
                .load(user.image)
                .into(itemView.userAvatarView)

        }
    }

    fun addData(list:List<ResultResponseDto>){
        users.addAll(list)
    }
}