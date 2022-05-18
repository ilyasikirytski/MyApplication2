package com.example.myapplication2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.data.repository.UserRepositoryImlpementation
import com.example.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.domain.models.SaveUserNameParam
import com.example.domain.models.UserName
import com.example.domain.usecase.GetUserNameUseCase
import com.example.domain.usecase.SaveUserNameUseCase
import com.example.myapplication2.databinding.FragmentStartScreenBinding

class StartScreenFragment : Fragment() {
    private lateinit var binding: FragmentStartScreenBinding
    private val userRepository by lazy {
        UserRepositoryImlpementation(
            userStorage = SharedPrefUserStorage(
                binding.root.context
            )
        )
    }
    private val getUserNameUseCase by lazy { GetUserNameUseCase(userRepository) }
    private val saveUserNameUseCase by lazy { SaveUserNameUseCase(userRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "Hello my new World"

        binding.saveData.setOnClickListener {
            val text = binding.editTextTextPersonName.text.toString()
            if (text == "") {
                Toast.makeText(this.context, "Please enter something", Toast.LENGTH_LONG).show()
            } else {
                val listOfElement = text.split(" ")
                val params =
                    SaveUserNameParam(listOfElement.component1(), listOfElement.component2())
                val result: Boolean = saveUserNameUseCase.execute(param = params)
                binding.textView.text = "Save result: $result"
            }
        }

        binding.getData.setOnClickListener {
            val userName: UserName = getUserNameUseCase.execute()
            binding.textViewFirstName.text = userName.firstName
            binding.textViewLastName.text = userName.lastName
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartScreenFragment()
    }
}