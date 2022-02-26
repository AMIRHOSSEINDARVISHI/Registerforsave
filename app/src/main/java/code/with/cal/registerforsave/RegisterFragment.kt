package code.with.cal.registerforsave

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import code.with.cal.registerforsave.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {


    private lateinit var bindingRegister: FragmentRegisterBinding
    private var gender = "Gender"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingRegister = FragmentRegisterBinding.bind(view)

        valuesPreference = activity!!.getSharedPreferences(nameFile, modeFile)

        setGender()

        bindingRegister.buttonRegister.setOnClickListener {
            saveValue()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setGender() {
        bindingRegister.groupGender.setOnCheckedChangeListener { _, checkedID ->
            gender = when (checkedID) {
                R.id.radio_male -> "Male"
                R.id.radio_female -> "Female"
                else -> "Gender"
            }
        }
    }

    private fun saveValue() {
        var nameTemp = ""
        var userTemp = ""
        var emailTemp = ""
        var passwordTemp = ""

        if (bindingRegister.editTextSetFullName.text!!.isNotEmpty()) {
            nameTemp = bindingRegister.editTextSetFullName.text.toString()
        } else {
            bindingRegister.editTextSetFullName.error = getString(R.string.toast_fullName)
        }

        if (bindingRegister.editTextSetUserName.text!!.isNotEmpty()) {
            userTemp = bindingRegister.editTextSetUserName.text.toString()
        } else {
            bindingRegister.editTextSetUserName.error = getString(R.string.toast_userName)
        }

        if (bindingRegister.editTextSetEmail.text!!.isNotEmpty()) {
            emailTemp = bindingRegister.editTextSetEmail.text.toString()
        } else {
            bindingRegister.editTextSetEmail.error = getString(R.string.toast_email)
        }

        if (bindingRegister.editTextSetPassword.text!!.isEmpty()) {
            bindingRegister.editTextSetPassword.error = getString(R.string.toast_password)
        }
        if (bindingRegister.editTextSetRetypePassword.text!!.isNotEmpty()) {
            bindingRegister.editTextSetRetypePassword.error =
                getString(R.string.toast_retype_password)
        }

        if (bindingRegister.editTextSetRetypePassword.text.toString() ==
            bindingRegister.editTextSetPassword.text.toString()
        ) {
            passwordTemp = bindingRegister.editTextSetPassword.text.toString()
        } else {
            bindingRegister.editTextSetRetypePassword.error =
                getString(R.string.toast_match_not_passwords)
        }

        val genderTemp = if (gender == "Gender") {
            Toast.makeText(activity, R.string.toast_gender, Toast.LENGTH_SHORT).show()
            ""
        } else {
            gender
        }

        if (
            nameTemp.isNotEmpty() && userTemp.isNotEmpty() && emailTemp.isNotEmpty() &&
            passwordTemp.isNotEmpty() && genderTemp.isNotEmpty()
        ) {
            val data = Bundle().apply {
                putString(fullNameID, nameTemp)
                putString(userNameID, userTemp)
                putString(emailID, emailTemp)
                putString(passwordID, passwordTemp)
                putString(genderID, genderTemp)
            }

            val saveValue = SaveValueFragment()

            saveValue.arguments = data

            activity!!.supportFragmentManager.commit {
                replace(R.id.container_fragments, saveValue)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

    }
}