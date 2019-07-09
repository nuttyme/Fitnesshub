package com.pes.healthforum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String TABLE_NAME1 ="health_forum";
    public static final String TABLE_NAME2 ="trainers";
    public static final String TABLE_NAME3 ="packages";
    public static final String TABLE_NAME4 ="assign_trainer";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 ="answer";

    public static final String U_Firstname ="firstname";

    private static final String TAG = "healthforum";
    private static final String TAG1 = "memberHome";
    private static final String TAG2 = "updateProfile";
    private static final String TAG3 = "editQuestion";
    private static final String TAG4 = "RegisterActivity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, firstname TEXT, lastname TEXT, height integer, weight integer, email TEXT, mobile TEXT, BMI TEXT, APPROVED TEXT, gender TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE health_forum (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, qtitle TEXT, question TEXT, answer TEXT, trainer_id integer)");
        sqLiteDatabase.execSQL("CREATE TABLE trainers (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, firstname TEXT, lastname TEXT, mobile TEXT, age TEXT, slot TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE packages (ID INTEGER PRIMARY  KEY AUTOINCREMENT, packagename TEXT, description TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE assign_trainer (ID INTEGER PRIMARY  KEY AUTOINCREMENT, member_id INTEGER, member_name TEXT,  trainer_id INTEGER, trainer_name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        Log.d(TAG4,"in 7575 addUser onItem clicked " );
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return  res;
    }

    public long addTrainer(String user, String password, String firstname, String lastname, String mobile, String age, String slot){
        SQLiteDatabase db = this.getWritableDatabase();
    //    db.execSQL("CREATE TABLE trainers (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("mobile", mobile);
        contentValues.put("age", age);
        contentValues.put("slot",slot);
        long res = db.insert("trainers",null,contentValues);
        db.close();
        return  res;
    }

    public long addQuestion(String user, String qtitle, String question){


        SQLiteDatabase db1 = this.getWritableDatabase();
//        db1.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
//        db1.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
//        db1.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, firstname TEXT, lastname TEXT, height integer, weight integer, email TEXT, mobile TEXT, BMI INTEGER, APPROVED TEXT)");
//        db1.execSQL("CREATE TABLE health_forum (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, qtitle TEXT, question TEXT, answer TEXT, trainer_id integer)");

        //   db1.execSQL("CREATE TABLE health_forum (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, qtitle TEXT, question TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("qtitle",qtitle);
        contentValues.put("question",question);
        long res = db1.insert(TABLE_NAME1,null,contentValues);
        db1.close();
        return  res;
    }

    public long addPackage(String packagename, String description){


        SQLiteDatabase db1 = this.getWritableDatabase();
//        db1.execSQL("CREATE TABLE packages (ID INTEGER PRIMARY  KEY AUTOINCREMENT, packagename TEXT, description TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("packagename",packagename);
        contentValues.put("description",description);
        long res = db1.insert(TABLE_NAME3,null,contentValues);
        db1.close();
        return  res;
    }


    public long assignTrainer(int member_id,String member_name,int trainer_id,String trainer_name){
        SQLiteDatabase db1 = this.getWritableDatabase();
//        db1.execSQL("CREATE TABLE assign_trainer (ID INTEGER PRIMARY  KEY AUTOINCREMENT, member_id INTEGER, member_name TEXT,  trainer_id INTEGER, trainer_name TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("member_id",member_id);
        contentValues.put("member_name",member_name);
        contentValues.put("trainer_id",trainer_id);
        contentValues.put("trainer_name",trainer_name);
        long res = db1.insert(TABLE_NAME4,null,contentValues);
        db1.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public boolean checkTrainer(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME2,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public Cursor getPackages() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME3;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor get_assign_trainer() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME4;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME1;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTrainers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username != 'admin' and APPROVED IS NULL ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getApprovedMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username != 'admin' and APPROVED = 'Y' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getQuestionID(String name){

        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(TAG,"in get questionID onItem clicked " + name);
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME1 +
                " WHERE " + COL_2 + " like '%" + name.substring(14,19) + "%'";
        Log.d(TAG,"in get questionID onItem clicked " + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getMemberID(String name){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME +
                " WHERE " + COL_2 + " like '%" + name + "%'";
        Log.d(TAG,"member onItem clicked" + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTrainerID(String name){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME2 +
                " WHERE " + COL_2 + " like '%" + name + "%'";
        Log.d(TAG,"member onItem clicked" + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getMemberDetails(String name){

        Log.d(TAG1,"in getMemberDetails" +name);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL_2 + " like '%" + name + "%'";
        Log.d(TAG1,"out getMemberDetails" + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTrainerDetails(String name){

        Log.d(TAG1,"in getTrainerDetails" +name);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2 +
                " WHERE " + COL_2 + " like '%" + name + "%'";
        Log.d(TAG1,"out getTrainerDetails" + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getUserID(String name){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME +
                " WHERE " + COL_2 + " like '%" + name + "%'";
        Log.d(TAG,"in getUserID" + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateAnswer(int id, String answer) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Log.d(TAG3,"in UpdateAnser");
        String query = "UPDATE " + TABLE_NAME1 + " SET " + COL_4
                + " = '" + answer + "' " + " WHERE " + COL_1 + " = '" + id + "' ";
        Log.d(TAG3,"in UpdateAnser" + query);
        db1.execSQL(query);
    }
//db.updateUser(selectedId,l_et_firstname,l_et_lastname,l_et_mobile,l_et_height,l_et_weight ,l_et_email)

    public void updateUser(int id) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET APPROVED "
                + " = '" + "Y" + "' " + " WHERE " + COL_1 + " = '" + id + "' ";
        db1.execSQL(query);
    }

    public void updateUser(int id, String firstname, String lastname, String mobile, String height, String weight, String email, String bmi, String gender) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET "
                + "firstname = '" + firstname + "' "
                + ",lastname = '" + lastname + "' "
                 + ",mobile = '" + mobile + "' "
                + ",height = '" + height + "' "
                + ",weight = '" + weight + "' "
                + ",email = '" + email + "' "
                + ",BMI = '" + bmi + "' "
                + ",gender = '" + gender + "' "
                + " WHERE " + COL_1 + " = '" + id + "' ";

        Log.d(TAG2,"in updateUser" + query);
        db1.execSQL(query);
    }

    public Cursor getAssignedTrainer(String memberId){

        Log.d(TAG1,"in getAssignedTrainer" + memberId);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME4 +
                " WHERE member_name=" + "'" + memberId + "'";
        Log.d(TAG1,"out getAssignedTrainer" + query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteTrainer(int id) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE " + COL_1 + " = '" + id + "' ";
        db1.execSQL(query);
    }
}
