package fr.epsfenelonlille.myapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "team_table")
data class Team(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idTeam") var idTeam: Int = 0,
    @ColumnInfo(name = "nameTeam") var nameTeam: String,
): Parcelable
