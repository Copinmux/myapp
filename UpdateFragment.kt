package fr.epsfenelonlille.myapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fr.epsfenelonlille.myapp.R
import fr.epsfenelonlille.myapp.databinding.FragmentUpdateBinding
import fr.epsfenelonlille.myapp.model.User
import fr.epsfenelonlille.myapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    //Pour accéder au fab
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container,false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateNameGroup_et.setText(args.currentUser.nameTeam)
        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateNumberTeam_et.setText(args.currentUser.nbTeam.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }


    private fun updateItem(){
        val nameTeam = updateNameGroup_et.text.toString()
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val nbTeam = Integer.parseInt(updateNumberTeam_et.text.toString())

        if (inputCheck( nameTeam, firstName, lastName, updateNumberTeam_et.text)){
            //Create User OBject
            val updateUser = User(args.currentUser.id, nameTeam, firstName, lastName, nbTeam)
            //Update Current User
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Mise à jour réussie!", Toast.LENGTH_SHORT).show()
            // NAvigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(), "Veuillez remplir les champs", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nameTeam: String, firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(nameTeam) && TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){

        }
        return super.onOptionsItemSelected(item)
    }


}