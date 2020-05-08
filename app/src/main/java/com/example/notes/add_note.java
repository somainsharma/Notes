package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class add_note extends AppCompatActivity {

    final ParseUser appuser = ParseUser.getCurrentUser();
    String currentuser;


    EditText edttitle, edtcontext;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edttitle = findViewById(R.id.edtnoteTitle);
        edtcontext = findViewById(R.id.edtnotecontent);
        currentuser = getIntent().getStringExtra("CurrentUser");

        edttitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getSupportActionBar().setTitle("Add the Title");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.length() > 0){
                    getSupportActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.in_note_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itmsave:

                 ParseObject note = new ParseObject("note");
                note.put("Title",edttitle.getText().toString());
                note.put("Context", edtcontext.getText().toString());
                note.put("ownerusername", ParseUser.getCurrentUser().getUsername());

                note.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){
                            Toast.makeText(add_note.this,"Saved successfully",Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(add_note.this,"An error occured \n" + e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                break;

            case R.id.itmdelete:
                break;

        }
        return super.onOptionsItemSelected(item);
    }





    }
