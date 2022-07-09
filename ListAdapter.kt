package fr.epsfenelonlille.myapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.epsfenelonlille.myapp.R
import fr.epsfenelonlille.myapp.model.TeamWithUser
import fr.epsfenelonlille.myapp.model.User
import fr.epsfenelonlille.myapp.viewmodel.UserViewModel

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var userTList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userTList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItemUT = userTList[position]

        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItemUT.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text = currentItemUT.firstName
        holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = currentItemUT.lastName
        holder.itemView.findViewById<TextView>(R.id.numeroTeam_txt).text = currentItemUT.nbTeam.toString()

        holder.itemView.findViewById<View>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItemUT)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.findViewById<Button>(R.id.btn_start_eval).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToEvalFragment(currentItemUT)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<User>){
        this.userTList = user
        notifyDataSetChanged()
    }

}