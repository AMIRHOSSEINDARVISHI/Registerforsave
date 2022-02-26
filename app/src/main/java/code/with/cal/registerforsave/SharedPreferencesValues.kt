package code.with.cal.registerforsave

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

const val fullNameID = "FullNameID"
const val userNameID = "UserNameID"
const val emailID = "EmailID"
const val passwordID = "PasswordID"
const val genderID = "GenderID"

const val nameFile = "User-Save"
const val modeFile = MODE_PRIVATE

const val fullNameKey = "FullNameKEY"
const val userNameKey = "UserNameKEY"
const val emailKey = "EmailKEY"
const val passwordKey = "PasswordKEY"
const val genderKey = "GenderKEY"

lateinit var valuesPreference:SharedPreferences