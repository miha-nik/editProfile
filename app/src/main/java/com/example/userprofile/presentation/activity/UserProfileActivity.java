package com.example.userprofile.presentation.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userprofile.R;
import com.example.userprofile.data.model.User;
import com.example.userprofile.presentation.Application;
import com.example.userprofile.presentation.presenter.UserPresenter;
import com.example.userprofile.presentation.presenter.UserProfilePresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends AppCompatActivity implements UserPresenter.ShowView {

    @Inject
    UserProfilePresenter presenter;

    @BindView(R.id.ivProfilePicture)
    ImageView ivProfilePicture;
    @BindView(R.id.tvDisplayName)
    TextView tvDisplayName;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvEthnicity)
    TextView tvEthnicity;
    @BindView(R.id.tvReligion)
    TextView tvReligion;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvFigure)
    TextView tvFigure;
    @BindView(R.id.tvAboutMe)
    TextView tvAboutMe;
    @BindView(R.id.tvLocation)
    TextView tvLocation;

    @BindView(R.id.flLoading)
    FrameLayout flLoading;

    @BindView(R.id.flRetry)
    FrameLayout flRetry;

    @BindView(R.id.btnEdit)
    Button btnEdit;
    @BindView(R.id.btnRetry)
    Button btnRetry;

    private CountingIdlingResource countingIdlingResource;
    public IdlingResource getIdlingResource() {
        return countingIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((Application)getApplication()).getComponent().inject(this);

        countingIdlingResource = new CountingIdlingResource("Data loading");
        presenter.setIdlingResource(countingIdlingResource);
    }

    @Override public void onResume() {
        super.onResume();
        if(this.presenter==null)return;

        this.presenter.attachView(this);
        this.presenter.start();
    }

    @Override public void onPause() {
        super.onPause();
        this.presenter.detachView();
    }

    @Override
    public void showLoading() {
        flLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        flLoading.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        flRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        flRetry.setVisibility(View.GONE);
    }

    @Override
    public void set(User user) {
        if(user==null)return;

       tvDisplayName.setText(user.getDisplayName());
       tvBirthday.setText(user.getBirthday());
       tvGender.setText(user.getGender().getName());
       tvEthnicity.setText(user.getEthnicity().getName());
       tvReligion.setText(user.getReligion().getName());
       tvHeight.setText(user.getHeight());
       tvFigure.setText(user.getFigure().getName());
       tvAboutMe.setText(user.getAboutMe());
       tvLocation.setText(user.getLocation().getCity());

       if(!user.getProfilePicture().getUrl().isEmpty())
           Picasso.get().load(Uri.parse(user.getProfilePicture().getUrl())).into(ivProfilePicture);
    }



    @OnClick(R.id.btnEdit)
    void editProfile(){
        startActivity(EditUserProfileActivity.getIntent(this, 0));
    }
    @OnClick(R.id.btnRetry)
    void retry(){
        this.presenter.start();
    }
}
