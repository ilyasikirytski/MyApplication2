package com.example.myapplication2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication2.data.repository.UserRepositoryImlpementation
import com.example.myapplication2.databinding.FragmentStartScreenBinding
import com.example.myapplication2.domain.models.SaveUserNameParam
import com.example.myapplication2.domain.models.UserName
import com.example.myapplication2.domain.usecase.GetUserNameUseCase
import com.example.myapplication2.domain.usecase.SaveUserNameUseCase

class StartScreenFragment : Fragment() {
    private lateinit var binding: FragmentStartScreenBinding
    private val userRepository by lazy { UserRepositoryImlpementation(context = activity!!.applicationContext) }
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
            val listOfElement = text.split(" ")
            val params = SaveUserNameParam(firstName = listOfElement.component1(), lastName = listOfElement.component2())
            val result: Boolean = saveUserNameUseCase.execute(param = params)
            binding.textView.text = "Save result: $result"
        }

        binding.getData.setOnClickListener {
            val userName: UserName = getUserNameUseCase.execute()
            binding.textViewFirstName.text = "${userName.firstName}"
            binding.textViewLastName.text = "${userName.lastName}"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartScreenFragment()
    }
}