package fr.epsfenelonlille.myapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import fr.epsfenelonlille.myapp.model.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUserData(): LiveData<List<User>>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    fun insertEval(eval: Eval)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table WHERE id=:id")
    fun getOneUser(id: Int): User

    @Transaction
    @Query("SELECT * FROM user_table JOIN eval_table  on user_table.id = eval_table.idEval ")
    fun getAllUserWithEval(): LiveData<List<UserWithEval>>

    @Query("SELECT * FROM user_table WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery OR nbTeam LIKE :searchQuery OR nameTeam LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<User>>?

    @Query("SELECT * FROM team_table WHERE  nameTeam LIKE :searchQuery")
    fun searchDatabaseTeam(searchQuery: String): LiveData<List<Team>>?

    @Transaction
    @Query("SELECT * FROM user_table WHERE nameTeam LIKE :searchTeam")
    fun getTeam(searchTeam: String): LiveData<List<User>>?

    //Eval
    @Query("SELECT * FROM eval_table ORDER BY idEval ASC")
    fun getEvalData(): LiveData<List<Eval>>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEval(eval: Eval)

    @Update
    fun updateEval(eval: Eval)

    @Delete
    fun deleteEval(eval: Eval)

    @Transaction
    @Query("DELETE FROM eval_table")
    fun deleteAllEvals()

    //Team
    @Query("SELECT * FROM team_table ORDER BY nameTeam ASC")
    fun getTeamData(): LiveData<List<Team>>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTeam(team: Team)

    @Update
    fun updateTeam(team: Team)

    @Delete
    fun deleteTeam(team: Team)

    @Query("DELETE FROM team_table")
    fun deleteAllTeams()

    @Transaction
    @Query("SELECT * FROM user_table JOIN team_table  on user_table.id = team_table.idTeam ")
    fun getAllTeamWithUser(): LiveData<List<TeamWithUser>>
}
