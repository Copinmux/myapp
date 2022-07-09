package fr.epsfenelonlille.myapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.epsfenelonlille.myapp.data.UserDao
import fr.epsfenelonlille.myapp.model.*

class UserRepository (private val userDao: UserDao) {
    val readAllDataUser: LiveData<List<User>>? = userDao.getUserData()
    val readAllDataEval: LiveData<List<Eval>>? = userDao.getEvalData()
    val readAllDataTeam: LiveData<List<Team>>? = userDao.getTeamData()
    val readAllDataUserEval: LiveData<List<UserWithEval>>? = userDao.getAllUserWithEval()
    val readAllDataTeamWithUser: LiveData<List<TeamWithUser>>? = userDao.getAllTeamWithUser()

    //User
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    //Eval
    suspend fun addEval(eval: Eval) {
        userDao.addEval(eval)
    }

    suspend fun updateEval(eval: Eval) {
        userDao.updateEval(eval)
    }

    suspend fun deleteEval(eval: Eval) {
        userDao.deleteEval(eval)
    }

    suspend fun deleteAllEvals() {
        userDao.deleteAllEvals()
    }

    //Eval
    suspend fun addTeam(team: Team) {
        userDao.addTeam(team)
    }

    suspend fun updateTeam(team: Team) {
        userDao.updateTeam(team)
    }

    suspend fun deleteTeam(team: Team) {
        userDao.deleteTeam(team)
    }

    suspend fun deleteAllTeams() {
        userDao.deleteAllTeams()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>>? {
        return userDao.searchDatabase(searchQuery)
    }

    fun searchDatabaseTeam(searchQuery: String): LiveData<List<Team>>? {
        return userDao.searchDatabaseTeam(searchQuery)
    }

    fun getTeam(searchTeam: String): LiveData<List<User>>? {
        return userDao.getTeam(searchTeam)
    }

    suspend fun getAllUserWithEval() {
        userDao.getAllUserWithEval()
    }

    fun getAllTeamWithUser(){
        userDao.getAllTeamWithUser()
    }

}