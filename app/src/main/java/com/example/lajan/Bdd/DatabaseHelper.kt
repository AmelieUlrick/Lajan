package com.example.lajan.Bdd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import com.example.lajan.Class.CarteBancaire
import com.example.lajan.Class.Compte
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
    //Création table Carte Bancaire
    private val CREATE_PRODUCTS_TABLE_CARTE = ("CREATE TABLE " +
            TABLE_CARTE + "(" +
            COLUMN_ID_CARTE +  " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NUMERO_CARTE + " INTEGER, "
            + COLUMN_DATE_EXP + " INTEGER, "
            + COLUMN_TYPE + " TEXT, "
            + COLUMN_KEY_USER_CARTE + " INTEGER, "
            + " FOREIGN KEY ("+ COLUMN_KEY_USER_CARTE+") REFERENCES "+ TABLE_NAME +" ("+ COLUMN_ID +")"
            + ")"
            )
    //Création table Compte
    private val CREATE_PRODUCTS_TABLE_COMPTE = ("CREATE TABLE " +
            TABLE_COMPTE + "(" +
            COLUMN_ID_COMPTE + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SOLDE + " REAL,"
            + COLUMN_DECOUVERT + " REAL, "
            + COLUMN_KEY_CARTE + " INTEGER, "
            + COLUMN_KEY_USER_COMPTE + " INTEGER, "
            + " FOREIGN KEY ("+ COLUMN_KEY_CARTE+") REFERENCES "+ TABLE_CARTE +" ("+ COLUMN_ID_CARTE +")"
            + " FOREIGN KEY ("+ COLUMN_KEY_USER_COMPTE+") REFERENCES "+ TABLE_NAME +" ("+ COLUMN_ID +")"
            + ")"
            )

    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    private val DROP_USER_TABLE_CARTE = "DROP TABLE IF EXISTS $TABLE_CARTE"
    private val DROP_USER_TABLE_COMPTE = "DROP TABLE IF EXISTS $TABLE_COMPTE"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_PRODUCTS_TABLE)
        db.execSQL(CREATE_PRODUCTS_TABLE_CARTE)
        db.execSQL(CREATE_PRODUCTS_TABLE_COMPTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        db.execSQL(DROP_USER_TABLE_CARTE)
        db.execSQL(DROP_USER_TABLE_COMPTE)
        onCreate(db)
    }

    fun addUser(utilisateur: Utilisateur, image:ByteArray){

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_PRENOM, utilisateur.Prenom)
        values.put(COLUMN_NOM, utilisateur.Nom)
        values.put(COLUMN_ADRESSE_MAIL, utilisateur.adresse_mail)
        values.put(COLUMN_LOGIN, utilisateur.login)
        values.put(COLUMN_MDP, utilisateur.mdp)
        values.put(COLUMN_IMAGE,image)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun addrForBdd(adress_mail: String) : Boolean {
        val columns = arrayOf(COLUMN_ID)
        val db = this.readableDatabase
        val selection = "$COLUMN_ADRESSE_MAIL = ? "
        val selectionArgs = arrayOf(adress_mail)
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

    //Vérifie sur l'utilisateur existe lors de la connexion
    fun connexion(login : String, mdp : String) : Boolean{
        val columns = arrayOf(COLUMN_ID)
        val action = this.readableDatabase
        val selection = "$COLUMN_LOGIN = ? AND $COLUMN_MDP = ?"
        val selectionArgs = arrayOf(login,mdp)
        val cursor = action.query(
            TABLE_NAME,
            columns,
            selection,
            selectionArgs, null, null, null
        )
        val pronouns = cursor.count
        cursor.close()
        action.close()
        if (pronouns > 0) {
            return true
        }
        return false

    }

    //Trouver l'utilisateur à partir de son login et de son mot de passe
    //S'il existe, cela retourne son id
    fun getUser(login : String, mdp : String) : Int{
        val action = this.readableDatabase
        val cursor = action.query(
            TABLE_NAME, null, "$COLUMN_LOGIN = ? AND $COLUMN_MDP = ?", arrayOf(login,mdp),
            null, null, null
        )
        if (cursor.getCount() < 1) {
            return 0
        }
        else {
            cursor.moveToFirst()
            val idUser : Int = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            return idUser
        }
    }

    fun getImage(user:Int): ByteArray? {
        val db = this.writableDatabase
        val qb = SQLiteQueryBuilder()
        val sqlSelect = arrayOf(COLUMN_IMAGE)
        /* val cursor = qb.query(
             TABLE_NAME, null, "$COLUMN_ID = ?", arrayOf(user.toString()),
             null, null, null
         )*/
        qb.tables = TABLE_NAME
        val c = qb.query(db,sqlSelect,"id_utilisateur = ?", arrayOf(user.toString()),null,null,null)
        var result :ByteArray?=null
        if(c.moveToFirst()){
            do{
                result = c.getBlob((c.getColumnIndex(COLUMN_IMAGE)))
            }while(c.moveToNext())
        }
        return result
    }

    fun updateImage(image:ByteArray,user:Int)
    {
        val database = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_IMAGE, image)
        database.update(TABLE_NAME, cv, "$COLUMN_ID = ?",
            arrayOf(user.toString()))
        database.close()
    }

    fun addCarte(carte : CarteBancaire){
        val action = this.writableDatabase

        val cv = ContentValues()
        cv.put(COLUMN_NUMERO_CARTE,carte.numeroCarte)
        cv.put(COLUMN_DATE_EXP,carte.dateExpiration)
        cv.put(COLUMN_TYPE,carte.typeCarte)
        cv.put(COLUMN_KEY_CARTE,carte.keyUserCarte)

        action.insert(TABLE_CARTE, null, cv)
        action.close()
    }

    fun addCompte(compte : Compte){
        val action = this.writableDatabase

        val cv = ContentValues()
        cv.put(COLUMN_SOLDE,compte.solde)
        cv.put(COLUMN_DECOUVERT,compte.decouvert)
        cv.put(COLUMN_KEY_CARTE,compte.keyCarte)
        cv.put(COLUMN_KEY_USER_COMPTE,compte.keyUserCpt)

        action.insert(TABLE_COMPTE, null, cv)
        action.close()
    }



    companion object {
        private val DATABASE_VERSION = 1

        private val DATABASE_NAME = "lajan.db"

        private val TABLE_NAME = "Utilisateur"

        private val COLUMN_ID = "id_utilisateur"
        private val COLUMN_PRENOM = "Prenom"
        private val COLUMN_NOM = " Nom"
        private val COLUMN_ADRESSE_MAIL = "adresse_mail"
        private val COLUMN_LOGIN = "login"
        private val COLUMN_MDP = "mdp"
        private val COLUMN_IMAGE ="image_data"

        private val TABLE_CARTE = "CarteBancaire"

        private val COLUMN_ID_CARTE = "idCarte"
        private val COLUMN_NUMERO_CARTE = "numeroCarte"
        private val COLUMN_DATE_EXP = "dateExpiration"
        private val COLUMN_TYPE = "type"
        private val COLUMN_KEY_USER_CARTE = "keyUserCarte"

        private val TABLE_COMPTE = "Compte"

        private val COLUMN_ID_COMPTE = "idCompte"
        private val COLUMN_SOLDE = "solde"
        private val COLUMN_DECOUVERT = "decouvert"
        private val COLUMN_KEY_CARTE = "keyCarte"
        private val COLUMN_KEY_USER_COMPTE = "keyUserCpt"
    }
}