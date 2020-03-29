package com.example.filmapplicatie.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.filmapplicatie.review.Review;

import java.util.LinkedList;
import java.util.List;


public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Reviews.db";
    private static final String TABLE_NAME = "Reviews";
    private static final String KEY_ID = "movie";
    private static final String KEY_NAME = "review";
    private static final String KEY_RATING = "rating";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_RATING};


    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Reviews ( "
                + "movie TEXT, " + "review TEXT, "
                + "rating TEXT, " + "height INTEGER )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(Review review){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, "movie = ?", new String[]{ String.valueOf(review.getMovie())});
    }

    public Review getReview(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Review review = new Review();
        review.setMovie(cursor.getString(0));
        review.setReview(cursor.getString(1));
        review.setRating(cursor.getString(2));


        return review;
    }

    public List<Review> allReviews() {

        List<Review> reviews = new LinkedList<Review>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Review review = null;

        if (cursor.moveToFirst()) {
            do {
                review = new Review();
                review.setMovie(cursor.getString(0));
                review.setReview(cursor.getString(1));
                review.setRating(cursor.getString(2));
                reviews.add(review);
            } while (cursor.moveToNext());
        }

        return reviews;
    }

    public void addReviews(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, review.getReview());
        values.put(KEY_RATING, review.getRating());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public int updateReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, review.getReview());
        values.put(KEY_RATING, review.getRating());


        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "movie = ?", // selections
                new String[] { String.valueOf(review.getMovie()) });

        db.close();

        return i;
    }

    public void removeReviews(){
        String q = "DELETE FROM " + TABLE_NAME;

        getWritableDatabase().execSQL(q);
    }






}
