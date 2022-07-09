package fr.epsfenelonlille.myapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.epsfenelonlille.myapp.data.UserDatabase
import fr.epsfenelonlille.myapp.model.*
import fr.epsfenelonlille.myapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllDataTeam: LiveData<List<Team>>?
    val readAllDataUser: LiveData<List<User>>?
    val readAllDataEval: LiveData<List<Eval>>?
    val readAllDataUserWithEval: LiveData<List<UserWithEval>>?
    val readAllDataTeamWithUser: LiveData<List<TeamWithUser>>?

    private val repositoryTeam: UserRepository
    private val repositoryUser: UserRepository
    private val repositoryEval: UserRepository
    private val repositoryUserWithEval: UserRepository
    private val repositoryTeamWithUser: UserRepository

    init {
        val teamDao = UserDatabase.getDatabase(application)?.userDao()
        repositoryTeam = UserRepository(teamDao!!)
        readAllDataTeam = repositoryTeam.readAllDataTeam

        val userDao = UserDatabase.getDatabase(application)?.userDao()
        repositoryUser = UserRepository(userDao!!)
        readAllDataUser = repositoryUser.readAllDataUser

        val evalDao = UserDatabase.getDatabase(application)?.userDao()
        repositoryEval = UserRepository(evalDao!!)
        readAllDataEval = repositoryEval.readAllDataEval

        val userEvalDao = UserDatabase.getDatabase(application)?.userDao()
        repositoryUserWithEval = UserRepository(userEvalDao!!)
        readAllDataUserWithEval= repositoryUserWithEval.readAllDataUserEval

        val teamUserDao = UserDatabase.getDatabase(application)?.userDao()
        repositoryTeamWithUser = UserRepository(teamUserDao!!)
        readAllDataTeamWithUser = repositoryTeamWithUser.readAllDataTeamWithUser

    }

    //Team

    fun addTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTeam.addTeam(team)
        }
    }

    fun updateTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTeam.updateTeam(team)
        }
    }

    fun deleteTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTeam.deleteTeam(team)
        }
    }

    fun deleteAllTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryTeam.deleteAllTeams()
        }
    }


    //Eval

    fun addEval(eval: Eval) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryEval.addEval(eval)
        }
    }

    fun updateEval(eval: Eval) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryEval.updateEval(eval)
        }
    }

    fun deleteEval(eval: Eval) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryEval.deleteEval(eval)
        }
    }

    fun deleteAllEvals() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryEval.deleteAllEvals()
        }
    }

    //User

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryUser.deleteAllUsers()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>>? {
        return repositoryUser.searchDatabase(searchQuery)
    }
    fun searchDatabaseTeam(searchQuery: String): LiveData<List<Team>>? {
        return repositoryUser.searchDatabaseTeam(searchQuery)
    }

    fun getTeam(searchTeam: String): LiveData<List<User>>? {
        return repositoryUser.getTeam(searchTeam)
    }
}