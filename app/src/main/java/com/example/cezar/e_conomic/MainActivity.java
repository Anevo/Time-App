package com.example.cezar.e_conomic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    Button Login_second;
    EditText Email;
    EditText Password;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabaseObj;
    DatabaseHelper sqLiteHelper;
    String TempPassword = "NOT_FOUND";
    public static final String UserEmail = "";


    //private CheckBox saveLoginCheckBox;
    //private SharedPreferences loginPreferences;
    //private SharedPreferences.Editor loginPrefsEditor;
    //private Boolean saveLogin;
    //private String usernameSave, passwordSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.ed_email_login);
        Password = (EditText) findViewById(R.id.ed_password);
        Login_second = (Button) findViewById(R.id.bt_login_second);

        Login_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());


                // Calling EditText is empty or no method.
                //CheckEditTextStatus();
                // Calling login method.
                //LoginFunction();
            }
        });
    }

    private void validate (String usrEmail, String userPassword){
        if ((usrEmail.equals("test@test.com")) && (userPassword.equals("test"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }
}




    /*
// Codul de sub e pentru date din db (not working.. yet)
    // Login function starts from here.
    public void LoginFunction() {

        if (EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Column_3_Password));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        } else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this, "Please Enter UserName or Password.", Toast.LENGTH_LONG).show();

        }

    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus() {

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult() {

        if (TempPassword.equalsIgnoreCase(PasswordHolder)) {

            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);

            startActivity(intent);


        } else {

            Toast.makeText(MainActivity.this, "UserName or Password is Wrong, Please Try Again.", Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND";

    }
}

----------------------------------------------------------------------*/

    /*
    public void onClick (View v){
        EditText a = (EditText) findViewById(R.id.ed_username);
        EditText b = (EditText) findViewById(R.id.ed_password);
        String str = a.getText().toString();
        String pass = b.getText().toString();

        String password = helper.searchPass(str);

        if (v.getId() == R.id.bt_login2) {

            if (pass.equals(password)) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                //i.putExtra("Username", str);
                startActivity(i);
            } else {
                Toast temp = Toast.makeText(MainActivity.this, "Username and password does not match!", Toast.LENGTH_LONG);
                temp.show();
            }
        }*/






        //saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        //loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        //loginPrefsEditor = loginPreferences.edit();

        /*
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            Username.setText(loginPreferences.getString("username", ""));
            Password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateCred()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void onClickCheckBox(View view2) {
        if (view2 == Login) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Username.getWindowToken(), 0);

            usernameSave = Username.getText().toString();
            passwordSave = Password.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", usernameSave);
                loginPrefsEditor.putString("password", passwordSave);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
        }
    }


    private boolean validateCred(){
        if(Username.getText().toString().trim().length() <= 0){
            Toast.makeText(MainActivity.this, "Please enter Username", Toast.LENGTH_LONG).show();
            return true;
        }else if(Password.getText().toString().trim().length() <= 0){
            Toast.makeText(MainActivity.this, "Please enter Password", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }*/



