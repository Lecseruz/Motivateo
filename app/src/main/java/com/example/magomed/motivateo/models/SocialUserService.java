package com.example.magomed.motivateo.models;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface SocialUserService {
    @POST("/signInSocial")
    Observable<Message> signIn(@Body SocialUser socialUser);

    @POST("/signUpSocial")
    Observable<Message> signUp(@Body SocialUser socialUser);
}
