package com.example.myapplication2.domain.repository

import com.example.myapplication2.domain.models.SaveUserNameParam
import com.example.myapplication2.domain.models.UserName

interface UserRepository {
    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): UserName
}
