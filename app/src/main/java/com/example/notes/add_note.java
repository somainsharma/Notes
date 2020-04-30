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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class add_note extends AppCompatActivity {

    final ParseUser appuser = ParseUser.getCurrentUser();

    EditText edttitle, edtcontext;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edttitle = findViewById(R.id.edtnoteTitle);
        edtcontext = findViewById(R.id.edtnotecontent);





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

       if(item.getItemId() == R.id.itmsave){

        appuser.put("Title",edttitle.getText().toString());
        appuser.put("Context",edtcontext.getText().toString());

        appuser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){

                    Toast.makeText(add_note.this, "Saved", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(add_note.this, "An error occurred" + "\n" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

       }else if(item.getItemId() == R.id.itmdelete){

       }

        return super.onOptionsItemSelected(item);
    }
}
