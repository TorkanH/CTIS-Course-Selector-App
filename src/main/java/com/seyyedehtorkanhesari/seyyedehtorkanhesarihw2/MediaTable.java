package com.seyyedehtorkanhesari.seyyedehtorkanhesarihw2;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class MediaTable {
    public static final String TABLE_NAME="media";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_LIKE = "numOfLike";
    public static final String FIELD_COMMENT = "numOfComment";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+"("+FIELD_ID+" number, "+FIELD_NAME +" text, "+FIELD_LIKE+" number, "+FIELD_COMMENT+" number);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists"+TABLE_NAME;
    public static ArrayList<SocialMedia> getAllMedia (DatabaseHelper db){
        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        //Cursor cursor db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME,null)
        ArrayList<SocialMedia> data=new ArrayList<>();
        SocialMedia med = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int numOfLike = cursor.getInt(2);
            int numOfComment = cursor.getInt(3);
            med = new SocialMedia(id,name,numOfLike,numOfComment);
            data.add(med);
        }
        return data;
    }
    public static ArrayList<SocialMedia> findMed(DatabaseHelper db, String key){
        String where = FIELD_NAME+" like '%"+key+"%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        ArrayList<SocialMedia> data=new ArrayList<>();
        SocialMedia med = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int numOfLike = cursor.getInt(2);
            int numOfComment = cursor.getInt(3);
            med = new SocialMedia(id,name,numOfLike,numOfComment);
            data.add(med);
        }
        return data;
    }
    public static boolean insertMed(DatabaseHelper db, int id, String name, int
            numOfLike, int numOfComment){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_LIKE, numOfLike);
        contentValues.put(FIELD_COMMENT, numOfComment);
        boolean res = db.insert(TABLE_NAME,contentValues);
        return res;
    }
    public static boolean insertMed(DatabaseHelper db, SocialMedia med){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, med.getId());
        contentValues.put(FIELD_NAME, med.getMediaName());
        contentValues.put(FIELD_LIKE, med.getNumOfLike());
        contentValues.put(FIELD_COMMENT, med.getNumOfComment());
        boolean res = db.insert(TABLE_NAME,contentValues);
        return res;
    }
    public static boolean updateMed(DatabaseHelper db, int id, String name, int
            numOfLike, int numOfComment){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_LIKE,numOfLike);
        contentValues.put(FIELD_COMMENT,numOfComment);
        String where = FIELD_ID +" = "+id;
        boolean res = db.update(TABLE_NAME,contentValues,where);
        return res;
    }
    public static boolean updateMed(DatabaseHelper db, SocialMedia med){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, med.getMediaName());
        contentValues.put(FIELD_LIKE,med.getNumOfLike());
        contentValues.put(FIELD_COMMENT,med.getNumOfComment());
        String where = FIELD_ID +" = "+med.getId();
        boolean res = db.update(TABLE_NAME,contentValues,where);
        return res;
    }
    public static boolean deleteMed (DatabaseHelper db, int id){
        String where = FIELD_ID +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }
}
