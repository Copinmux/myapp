package fr.epsfenelonlille.myapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamWithUser(
    var idTeam: Int,
    var nameTeam: String,
    var firstName: String,
    var lastName: String,
    var nbTeam:Int
): Parcelable
