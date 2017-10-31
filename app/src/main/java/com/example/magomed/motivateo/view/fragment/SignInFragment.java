package com.example.magomed.motivateo.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.User;
import com.example.magomed.motivateo.service.UserService;
import com.example.magomed.motivateo.service.ServiceFactory;
import com.example.magomed.motivateo.view.activity.MainActivity;

import java.util.Objects;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SignInFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_sign_in);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.sign_in_toolbar);
//        myToolbar.setTitle("Motivator");
//        setSupportActionBar(myToolbar);
//
//        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText password = (EditText) findViewById(R.id.sign_in_password);
//                final EditText login = (EditText) findViewById(R.id.sign_in_login);
//                final TextView textViewInfo = (TextView) findViewById(R.id.sign_in_text_view);
//
//                if (isEmpty()) {
//                    textViewInfo.setText("Error data");
//                } else {
//                    UserService service = ServiceFactory.createRetrofitService(UserService.class, UserService.SERVICE_ENDPOINT);
//
//                    service.signIn(new User(null, login.getText().toString(), password.getText().toString()))
//                            .subscribeOn(Schedulers.newThread())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Subscriber<Message>() {
//                                @Override
//                                public void onCompleted() {
//
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Log.d("Error", e.getMessage());
//                                }
//
//                                @Override
//                                public void onNext(Message response) {
//                                    if (response.getCode() == 200) {
//                                        Intent intent = new Intent(SignInFragment.this, TodayFragment.class);
//                                        startActivity(intent);
//                                    } else {
//                                        textViewInfo.setText(" user not found");
//                                    }
//                                }
//                            });
//                }
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_authorization, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_back:
//                Intent intent = new Intent(SignInFragment.this, MainActivity.class);
//                startActivity(intent);
//                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
//
//    private boolean isEmpty() {
//        final EditText password = (EditText) findViewById(R.id.sign_in_password);
//        final EditText login = (EditText) findViewById(R.id.sign_in_login);
//
//        return (Objects.equals(password.getText().toString(), "") && Objects.equals(login.getText().toString(), ""));
//    }
}
