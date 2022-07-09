package fr.epsfenelonlille.myapp.fragments.add

import android.os.Bundle
import android.text.Editable
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
import fr.epsfenelonlille.myapp.databinding.FragmentAddBinding
import fr.epsfenelonlille.myapp.model.Eval
import fr.epsfenelonlille.myapp.model.Team
import fr.epsfenelonlille.myapp.model.User
import fr.epsfenelonlille.myapp.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var  mUserViewModel: UserViewModel
    //Pour accéder au fab
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<AddFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container,false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val idTeam = args.currentTeam.idTeam
        val name = args.currentTeam.nameTeam
        val team = Team(idTeam, name)
        mUserViewModel.updateTeam(team)

        binding.btnAddFragmentAdd.setOnClickListener {
            insertDataToDatabase()
            insertEvalToDatabase()
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

    private fun insertDataToDatabase() {
        val idTeam = args.currentTeam.idTeam
        val nameTeam = args.currentTeam.nameTeam

        val nameTeamEt = nameTeam
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val nbTeam = binding.addNumberTeamEt.text


        if(inputCheck( nameTeam, firstName, lastName, nbTeam)){
            //Create User OBject
            val team = Team(idTeam, nameTeam)
            val user = User(0, nameTeamEt, firstName, lastName, Integer.parseInt(nbTeam.toString()))
            //Add Data to Database
            mUserViewModel.addTeam(team)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Ajout avec succès !", Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Veuillez remplir tous les champs, SVP !", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(nameTeam: String, firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(nameTeam) && TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}