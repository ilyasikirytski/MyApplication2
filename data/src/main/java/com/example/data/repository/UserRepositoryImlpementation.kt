package com.example.data.repository

import com.example.data.storage.UserStorage
import com.example.data.storage.models.UserModel
import com.example.domain.models.SaveUserNameParam
import com.example.domain.models.UserName
import com.example.domain.repository.UserRepository


class UserRepositoryImlpementation(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        val userName = mapToStorage(saveParam)
        val result = userStorage.save(userName)
        return result
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): UserModel {
        return UserModel(saveParam.firstName, saveParam.lastName)
    }

    private fun mapToDomain(userModel: UserModel): UserName {
        return UserName(userModel.firstName, userModel.lastName)
    }
}