package com.example.cezar.e_conomic;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //DatabaseHelper helper = new DatabaseHelper(this);

    EditText Email, Password, Name ;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    DatabaseHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = (Button)findViewById(R.id.bt_register2);

        Name = (EditText)findViewById(R.id.ed_name);
        Email = (EditText)findViewById(R.id.ed_email);
        Password = (EditText)findViewById(R.id.ed_password2);

        sqLiteHelper = new DatabaseHelper(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating SQLite database if doesn't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if doesn't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();
            }
        });
    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    // SQLite table build method.
    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseHelper.TABLE_NAME + "(" + DatabaseHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + DatabaseHelper.Table_Column_1_Name + " VARCHAR, " + DatabaseHelper.Table_Column_2_Email + " VARCHAR, " + DatabaseHelper.Table_Column_3_Password + " VARCHAR);");
    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase() {
        // If editText is not empty then this block will executed.
        if (EditTextEmptyHolder == true) {
            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO " + DatabaseHelper.TABLE_NAME + " (name,email,password) VALUES('" + NameHolder + "', '" + EmailHolder + "', '" + PasswordHolder + "');";
            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            // Closing SQLite database object.
            sqLiteDatabaseObj.close();
            // Printing toast message after done inserting.
            Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
        }
        // This block will execute if any of the registration EditText is empty.
        else {
            // Printing toast message if any of EditText is empty.
            Toast.makeText(RegisterActivity.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }

    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){

        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";

                // Closing cursor.
                cursor.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }

    // Checking result
    public void CheckFinalResult(){

        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found"))
        {

            // If email is exists then toast msg will display.
            Toast.makeText(RegisterActivity.this,"Email Already Exists",Toast.LENGTH_LONG).show();

        }
        else {
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }

        F_Result = "Not_Found" ;

    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = (Button) findViewById(R.id.bt_register2);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_register2) {
            EditText name = (EditText) findViewById(R.id.ed_name);
            EditText email = (EditText) findViewById(R.id.ed_email);
            EditText username = (EditText) findViewById(R.id.ed_username2);
            EditText password = (EditText) findViewById(R.id.ed_password2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String usernamestr = username.getText().toString();
            String passwordstr = password.getText().toString();
            String passwordstr2 = password.getText().toString();

            if(TextUtils.isEmpty(namestr)) {
                name.setError("Name field is required.");
                return;
            }
            if(TextUtils.isEmpty(emailstr)) {
                email.setError("Email field is required.");
                return;
            }
            if(TextUtils.isEmpty(usernamestr)) {
                username.setError("Username field is required.");
                return;
            }
            if(TextUtils.isEmpty(passwordstr)) {
                password.setError("Password field is required.");
                return;
            }
            if(TextUtils.isEmpty(passwordstr2)) {
                password.setError("Password field is required.");
                return;
            }

            if(!passwordstr.equals(passwordstr2)){
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                Contacts c = new Contacts();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUsername(usernamestr);
                c.setPassword(passwordstr);

                helper.insertContact(c);
            }

        }

    }*/

}
