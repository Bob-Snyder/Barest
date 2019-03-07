package com.qbix.barest;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Map<String, Object> update = new HashMap<>();
            update.put("aaa", "dummy");
            update.put("bbb", 123);

            Log.d(TAG, "performTestAction: testWrite RQST");

            FirebaseFirestore.getInstance().collection("test").document()
                    .set(update).addOnCompleteListener(setTask -> {
                // This completion listener does not fire and data is not written when
                // In-App Messaging is included in build!
                if (setTask.isSuccessful()) {
                    Log.d(TAG, "testWrite: SUCCESS");
                } else {
                    FirebaseFirestoreException fse = (FirebaseFirestoreException) setTask.getException();
                    Log.e(TAG, "testWrite FAILED", fse);
                }
            });
            Log.d(TAG, "performTestAction: testWrite DONE");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d(TAG, "onOptionsItemSelected: logging event");
            //logEvents("testEvent", "test_category", "test_action", this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");

    }
}
