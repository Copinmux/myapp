package fr.epsfenelonlille.myapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.epsfenelonlille.myapp.R
import fr.epsfenelonlille.myapp.databinding.FragmentAddGroupBinding
import fr.epsfenelonlille.myapp.fragments.list.ListFragmentArgs
import fr.epsfenelonlille.myapp.model.Eval
import fr.epsfenelonlille.myapp.model.Team
import fr.epsfenelonlille.myapp.model.User
import fr.epsfenelonlille.myapp.viewmodel.UserViewModel

class AddGroupFragment: Fragment() {

    private lateinit var  mUserViewModel: UserViewModel
    //Pour accéder au fab
    private var _binding: FragmentAddGroupBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<AddGroupFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddGroupBinding.inflate(inflater, container,false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAddFragmentTeam.setOnClickListener {
            insertEvalToDatabase()
            insertDataTeamToDatabase()
        }
        return view
    }

    private fun insertEvalToDatabase() {
        val note_attaque= "0.0"
        val note_classement = "0.0"
        val note_passe= "0.0"
        val noteAfl2 = "0.0"
        val noteAfl3 = "0.0"
        val noteFinale = "0.0"
        val eval = Eval(0, note_attaque, note_classement, note_passe,
            noteAfl2, noteAfl3, noteFinale)
        mUserViewModel.addEval(eval)
    }

    private fun insertDataTeamToDatabase() {
        val nameTeam = binding.addNameTeamEt.text.toString()

        if(inputCheck( nameTeam)){
            //Create User OBject
            val team = Team(0, nameTeam)
            val user = User(0, nameTeam,  "", "", 1)
            //Add Data to Database
            mUserViewModel.addTeam(team)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Ajout avec succès !", Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_addGroupFragment_to_addFragment)
        }else{
            Toast.makeText(requireContext(), "Veuillez remplir tous les champs, SVP !", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck( nameTeam: String): Boolean{
        return !(TextUtils.isEmpty(nameTeam))
    }

}