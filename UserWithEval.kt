package fr.epsfenelonlille.myapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserWithEval(
    var id: Int,
    var nameTeam: String,
    var firstName: String,
    var lastName: String,
    var nbTeam:Int,
    var note_attaque: String,
    var note_passe: String,
    var note_classement: String,
    var note_afl2: String,
    var note_afl3: String,
    var note_sur_vingt: String
): Parcelable