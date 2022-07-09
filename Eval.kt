package fr.epsfenelonlille.myapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eval_table")
data class Eval(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idEval") var idEval: Int=0,
    @ColumnInfo(name = "note_classement")    var note_classement: String,
    @ColumnInfo(name = "note_attaque")    var note_attaque: String,
    @ColumnInfo(name = "note_passe")    var note_passe: String,
    @ColumnInfo(name = "note_afl2")    var note_afl2: String,
    @ColumnInfo(name = "note_afl3")    var note_afl3: String,
    @ColumnInfo(name = "note_sur_vingt")    var note_sur_vingt: String,
)
