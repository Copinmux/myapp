package fr.epsfenelonlille.myapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epsfenelonlille.myapp.R
import fr.epsfenelonlille.myapp.databinding.FragmentListGroupBinding
import fr.epsfenelonlille.myapp.viewmodel.UserViewModel

class ListGroupFragment : Fragment(), SearchView.OnQueryTextListener {

    //Pour accéder au fab
    private var _binding: FragmentListGroupBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListGroupBinding.inflate(inflater, container,false)
        val view = binding.root

        //Recyclerview pour affichier le custom row et les données
        val adapter = ListGroupAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_team)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel pour afficher les données
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllDataTeam?.observe(viewLifecycleOwner, Observer { team ->
            adapter.setDataTeam(team)
        })

        binding.floatingActionButtonTeam.setOnClickListener{
            findNavController().navigate(R.id.action_listGroupFragment_to_addGroupFragment)
        }
        // Add menu
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        inflater.inflate(R.menu.delete_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(
                requireContext(),
                "Suppression complète réussie",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Tout Supprimer?")
        builder.setMessage("Êtes-vous sûr de vouloir supprimer l'ensemble des données?")
        builder.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        val adapter = ListGroupAdapter()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        mUserViewModel.searchDatabaseTeam(searchQuery)?.observe(this, { team ->
            team.let {
                adapter.setDataTeam(it)
            }
        })
    }
}