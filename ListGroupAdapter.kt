package fr.epsfenelonlille.myapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.epsfenelonlille.myapp.R
import fr.epsfenelonlille.myapp.model.Team
import fr.epsfenelonlille.myapp.model.TeamWithUser
import fr.epsfenelonlille.myapp.model.User

class ListGroupAdapter: RecyclerView.Adapter<ListGroupAdapter.MyViewHolder>() {

    var teamList = emptyList<Team>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_team, parent, false))
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItemTeam = teamList[position]

        holder.itemView.findViewById<TextView>(R.id.nameTeam_txt).text = currentItemTeam.nameTeam

        holder.itemView.findViewById<View>(R.id.rowLayoutTeam).setOnClickListener {
            val action = ListGroupFragmentDirections.actionListGroupFragmentToUpdateGroupFragment(currentItemTeam)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.findViewById<Button>(R.id.btn_start_team).setOnClickListener {
            val action = ListGroupFragmentDirections.actionListGroupFragmentToListFragment(currentItemTeam)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setDataTeam(team: List<Team>){
        this.teamList = team
        notifyDataSetChanged()
    }

}