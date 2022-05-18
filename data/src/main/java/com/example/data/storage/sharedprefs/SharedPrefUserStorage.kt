package com.example.data.storage.sharedprefs

import android.content.Context
import com.example.data.storage.UserStorage
import com.example.data.storage.models.UserModel

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "first_name"
private const val KEY_LAST_NAME = "last_name"

class SharedPrefUserStorage(context: Context) : UserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)


    override fun save(userModel: UserModel): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, userModel.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME, userModel.lastName).apply()
        return true
    }

    override fun get(): UserModel {
        val firstName = sharedPreferences.getString(KEY_FIRST_NAME, "Default Name").toString()
        val lastName =
            sharedPreferences.getString(KEY_LAST_NAME, "Default Last Name From Repository")
                .toString()
        return UserModel(firstName = firstName, lastName = lastName)
    }
}