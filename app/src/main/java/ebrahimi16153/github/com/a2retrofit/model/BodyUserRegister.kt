package ebrahimi16153.github.com.a2retrofit.model

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class BodyUserRegister(

    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password:String,
    @SerializedName("name")
    val name: String


)
