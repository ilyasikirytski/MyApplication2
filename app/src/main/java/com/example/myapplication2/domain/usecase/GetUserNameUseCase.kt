package com.example.myapplication2.domain.usecase

import com.example.myapplication2.domain.models.UserName
import com.example.myapplication2.domain.repository.UserRepository

class GetUserNameUseCase(private val userRepository: UserRepository) {
    fun execute(): UserName {
        return userRepository.getName()
    }
}