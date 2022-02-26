package code.with.cal.registerforsave

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import code.with.cal.registerforsave.databinding.FragmentSaveValueBinding

class SaveValueFragment : Fragment(R.layout.fragment_save_value) {

    private lateinit var bindingSaveValue: FragmentSaveValueBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingSaveValue = FragmentSaveValueBinding.bind(view)

        valuesPreference = activity!!.getSharedPreferences(nameFile, modeFile)

        showValue()

        saveValue()

    }

    private fun showValue() {
        bindingSaveValue.textViewShowFullName.text = arguments?.getString(fullNameID) ?: "FullName"
        bindingSaveValue.textViewShowUserName.text = arguments?.getString(userNameID) ?: "UserName"
        bindingSaveValue.textViewShowEmail.text = arguments?.getString(emailID) ?: "Email"
        bindingSaveValue.textViewShowPassword.text = arguments?.getString(passwordID) ?: "Password"
        bindingSaveValue.textViewShowGender.text = arguments?.getString(genderID) ?: "Gender"
    }

    private fun saveValue(){
        val editorValue: SharedPreferences.Editor = valuesPreference.edit()

        bindingSaveValue.buttonSaveValue.setOnClickListener {
            editorValue.putString(fullNameKey,bindingSaveValue.textViewShowFullName.text.toString())
            editorValue.putString(userNameKey,bindingSaveValue.textViewShowUserName.text.toString())
            editorValue.putString(emailKey,bindingSaveValue.textViewShowEmail.text.toString())
            editorValue.putString(passwordKey,bindingSaveValue.textViewShowPassword.text.toString())
            editorValue.putString(genderKey,bindingSaveValue.textViewShowGender.text.toString())
            editorValue.apply()
            editorValue.commit()

            Toast.makeText(activity, R.string.toast_saved_value, Toast.LENGTH_SHORT).show()
        }
    }
}