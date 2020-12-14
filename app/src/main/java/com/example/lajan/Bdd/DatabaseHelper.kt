package com.example.lajan.Bdd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.lajan.Class.Utilisateur

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
            TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRENOM + " TEXT,"
            + COLUMN_NOM + " TEXT,"
            + COLUMN_ADRESSE_MAIL + " TEXT,"
            + COLUMN_LOGIN + " TEXT,"
            + COLUMN_MDP + " TEXT,"
            + COLUMN_IMAGE + " BLOB"
            + ")"
            )

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun addUser(utilisateur: Utilisateur, image:ByteArray){

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_PRENOM, utilisateur.Prenom)
        values.put(COLUMN_NOM, utilisateur.Nom)
        values.put(COLUMN_ADRESSE_MAIL, utilisateur.addresse_mail)
        values.put(COLUMN_LOGIN, utilisateur.login)
        values.put(COLUMN_MDP, utilisateur.mdp)
        values.put(COLUMN_IMAGE,image)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun addrForBdd(address_mail: String) : Boolean {
        val columns = arrayOf(COLUMN_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_ADRESSE_MAIL = ? "
        val selectionArgs = arrayOf(address_mail)
        val cursor = db.query(
                TABLE_NAME,
                columns,
                selection,
                selectionArgs, null, null, null
        )
        val pronouns = cursor.count
        cursor.close()
        db.close()
        if (pronouns > 0) {
            return true
        }
        return false
    }



    companion object {
        private val DATABASE_VERSION = 1

        private val DATABASE_NAME = "lajan.db"

        private val TABLE_NAME = "Utilisateur"

        private val COLUMN_ID = "id_utilisateur"
        private val COLUMN_PRENOM = "Prenom"
        private val COLUMN_NOM = " Nom"
        private val COLUMN_ADRESSE_MAIL = "addresse_mail"
        private val COLUMN_LOGIN = "login"
        private val COLUMN_MDP = "mdp"
        private val COLUMN_IMAGE ="image_data"

    }
}