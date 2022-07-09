package fr.epsfenelonlille.myapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.epsfenelonlille.myapp.R
import fr.epsfenelonlille.myapp.databinding.FragmentUpdateTeamBinding
import fr.epsfenelonlille.myapp.model.Team
import fr.epsfenelonlille.myapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update_team.*
import kotlinx.android.synthetic.main.fragment_update_team.view.*

class UpdateGroupFragment : Fragment() {

    //Pour accéder au fab
    private var _binding: FragmentUpdateTeamBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateGroupFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateTeamBinding.inflate(inflater, container,false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateNameTeam_et.setText(args.currentTeam.nameTeam)

        view.update_team_btn.setOnClickListener {
            updateItemTeam()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }


    private fun updateItemTeam(){
        val nameTeam = updateNameTeam_et.text.toString()

        if (inputCheck( nameTeam)){
            //Create User OBject
            val updateTeam = Team(args.currentTeam.idTeam, nameTeam)
            //Update Current User
            mUserViewModel.updateTeam(updateTeam)
            Toast.makeText(requireContext(), "Mise à jour réussie!", Toast.LENGTH_SHORT).show()
            // NAvigate back
            findNavController().navigate(R.id.action_updateGroupFragment_to_listGroupFragment)
        } else{
            Toast.makeText(requireContext(), "Veuillez remplir les champs", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nameTeam: String): Boolean{
        return !( TextUtils.isEmpty(nameTeam) )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteTeam()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTeam() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteTeam(args.currentTeam)
            Toast.makeText(
                requireContext(),
                "Suppression réussie: ${args.currentTeam.nameTeam}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateGroupFragment_to_listGroupFragment)
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Supprimer ${args.currentTeam.nameTeam}?")
        builder.setMessage("Êtes-vous sûr de vouloir supprimer ${args.currentTeam.nameTeam}?")
        builder.create().show()
    }
}