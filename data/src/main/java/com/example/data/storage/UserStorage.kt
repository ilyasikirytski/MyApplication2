package com.example.data.storage

import com.example.data.storage.models.UserModel

interface UserStorage {
    fun save(userModel: UserModel): Boolean
    fun get(): UserModel
}