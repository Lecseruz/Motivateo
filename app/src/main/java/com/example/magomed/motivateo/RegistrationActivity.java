package com.example.magomed.motivateo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.models.UserService;
import com.example.magomed.motivateo.service.ServiceFactory;

import java.util.Objects;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.registration_toolbar);
        myToolbar.setTitle("Motivator");
        setSupportActionBar(myToolbar);
        findViewById(R.id.registration_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText email = (EditText) findViewById(R.id.registration_email);
                final EditText password = (EditText) findViewById(R.id.registration_password);
                final EditText login = (EditText) findViewById(R.id.registration_login);
                final TextView textViewInfo = (TextView) findViewById(R.id.registration_text_view);

                if (isEmpty()) {
                    textViewInfo.setText("Error data");
                } else {
                    UserService service = ServiceFactory.createRetrofitService(UserService.class, UserService.SERVICE_ENDPOINT);

                    service.signUp(new User(email.getText().toString(), login.getText().toString(), password.getText().toString()))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Message>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d("Error", e.getMessage());
                                }

                                @Override
                                public void onNext(Message response) {
                                    if (response.getCode() == 200) {
                                        Intent intent = new Intent(RegistrationActivity.this, TodayActivity.class);
                                        startActivity(intent);
                                    } else {
                                        textViewInfo.setText(" user not found");
                                    }
                                }
                            });

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authorization, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
//                Intent intent = new Intent(RegistrationActivity.this, ActivityAll.class);
//                startActivity(intent);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private boolean isEmpty() {
        final EditText email = (EditText) findViewById(R.id.registration_email);
        final EditText password = (EditText) findViewById(R.id.registration_password);
        final EditText login = (EditText) findViewById(R.id.registration_login);

        return (Objects.equals(email.getText().toString(), "") && Objects.equals(password.getText().toString(), "") && Objects.equals(login.getText().toString(), ""));
    }
}
