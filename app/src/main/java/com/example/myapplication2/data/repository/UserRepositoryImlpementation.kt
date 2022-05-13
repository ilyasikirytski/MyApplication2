package com.example.myapplication2.data.repository

import android.content.Context
import com.example.myapplication2.domain.models.SaveUserNameParam
import com.example.myapplication2.domain.models.UserName
import com.example.myapplication2.domain.repository.UserRepository

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "first_name"
private const val KEY_LAST_NAME = "last_name"

class UserRepositoryImlpementation(context: Context) : UserRepository {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, saveParam.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME, saveParam.lastName).apply()
        return true
    }

    override fun getName(): UserName {
        val firstName = sharedPreferences.getString(KEY_FIRST_NAME, "Default Name").toString()
        val lastName = sharedPreferences.getString(KEY_LAST_NAME, "Default Last Name From Repository").toString()
        return UserName(firstName = firstName, lastName = lastName)
    }
}