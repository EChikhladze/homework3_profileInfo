package com.example.homework3_profileinfo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import com.example.homework3_profileinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            if(isInputValid(binding)) {
                val profile = ProfileInfo(binding.edEmail.text.toString(), binding.edUsername.text.toString(),
                    binding.edFirstName.text.toString(), binding.edLastName.text.toString(), binding.edAge.text.toString())
                val intent = Intent(this, SavedActivity::class.java)
                intent.putExtra("email", profile.email)
                intent.putExtra("username", profile.username)
                intent.putExtra("firstName", profile.firstName)
                intent.putExtra("lastName", profile.lastName)
                intent.putExtra("age", profile.age)
                startActivity(intent)
            }
        }

        binding.btnClear.setOnLongClickListener {
            clearInput(binding)
            true
        }

    }

    private fun clearInput(binding: ActivityMainBinding) {
        val fieldsList = listOf(binding.edEmail, binding.edUsername, binding.edFirstName, binding.edLastName, binding.edAge)

        for (element in fieldsList) {
            element.setText("")
            element.setHintTextColor(Color.GRAY)
            element.hint = when(element) {
                binding.edEmail -> getString(R.string.email)
                binding.edUsername -> getString(R.string.username)
                binding.edFirstName -> getString(R.string.first_name)
                binding.edLastName -> getString(R.string.last_name)
                binding.edAge -> getString(R.string.age)
                else -> ""
            }
        }

    }

    private fun isInputValid(binding: ActivityMainBinding): Boolean {
        isNotEmpty(binding)
        checkUsernameLength(binding.edUsername)
        isEmailValidity(binding.edEmail)
        isAgePositiveInt(binding.edAge)
        if (binding.edEmail.text.toString().isNotEmpty() && binding.edUsername.text.toString().isNotEmpty()
            && binding.edFirstName.text.toString().isNotEmpty() && binding.edLastName.text.toString().isNotEmpty()
            && binding.edAge.text.toString().isNotEmpty()) return true
        return false
    }

    private fun isNotEmpty(binding: ActivityMainBinding) {
        val fieldsList = listOf(binding.edEmail, binding.edUsername, binding.edFirstName, binding.edLastName, binding.edAge)

        for (element in fieldsList) {
            if (element.text.toString().isEmpty()) {
                displayErrorMessage(element, getString(R.string.requiredError))
            }
        }
    }

    private fun checkUsernameLength(username: AppCompatEditText) {
        val minLength = 10

        if (username.text.toString().length < minLength) {
            displayErrorMessage(username, getString(R.string.usernameLengthError))
        }
    }

    private fun isEmailValidity(email: AppCompatEditText) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            displayErrorMessage(email, getString(R.string.emailValidityError))
        }
    }

    private fun isAgePositiveInt(age: AppCompatEditText) {
        if (!Regex("^[1-9]+$").matches(age.text.toString())) {
            displayErrorMessage(age, getString(R.string.ageValidityError))
        }
    }

    private fun displayErrorMessage(element: AppCompatEditText, errorMsg: String) {
        element.setText("")
        element.setHintTextColor(Color.RED)
        element.hint = errorMsg
    }

    private fun saveData(binding: ActivityMainBinding) {
        val profile = ProfileInfo(binding.edEmail.text.toString(), binding.edUsername.text.toString(),
            binding.edFirstName.text.toString(), binding.edLastName.text.toString(), binding.edAge.text.toString())


    }

    class ProfileInfo(val email: String, val username: String, val firstName: String, val lastName: String, val age: String) {}
}