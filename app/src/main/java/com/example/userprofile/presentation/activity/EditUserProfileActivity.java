package com.example.userprofile.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userprofile.R;
import com.example.userprofile.data.model.City;
import com.example.userprofile.data.model.Ethnicity;
import com.example.userprofile.data.model.Figure;
import com.example.userprofile.data.model.Gender;
import com.example.userprofile.data.model.Picture;
import com.example.userprofile.data.model.Religion;
import com.example.userprofile.data.model.SingleChoiceAttributes;
import com.example.userprofile.data.model.User;
import com.example.userprofile.presentation.Application;
import com.example.userprofile.presentation.adapter.SpinnerDataAdapter;
import com.example.userprofile.presentation.presenter.EditUserProfilePresenter;
import com.example.userprofile.presentation.presenter.UserPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditUserProfileActivity extends AppCompatActivity implements UserPresenter.EditView {

    //private static final String USER_ID = "com.example.userprofile.getUser.id";

    @Inject
    EditUserProfilePresenter presenter;
    @BindView(R.id.ivProfilePicture)
    ImageView ivProfilePicture;
    @BindView(R.id.etDisplayName)
    EditText etDisplayName;
    @BindView(R.id.etBirthday)
    EditText etBirthday;
    @BindView(R.id.sGender)
    Spinner sGender;
    @BindView(R.id.sEthnicity)
    Spinner sEthnicity;
    @BindView(R.id.sReligion)
    Spinner sReligion;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.sFigure)
    Spinner sFigure;
    @BindView(R.id.etAboutMe)
    EditText etAboutMe;
    @BindView(R.id.sLocation)
    Spinner sLocation;
    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.flLoading)
    FrameLayout flLoading;

    private Picture profilePicture;

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, EditUserProfileActivity.class);
        //intent.putExtra(USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        ButterKnife.bind(this);
        ((Application)getApplication()).getComponent().inject(this);
    }

    @Override public void onResume() {
        super.onResume();
        if(this.presenter!=null)
            this.presenter.attachView(this);
    }

    @Override public void onPause() {
        super.onPause();
        if(this.presenter!=null)
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
    public void setUser(User user) {

        etDisplayName.setText(user.getDisplayName());
        etBirthday.setText(user.getBirthday());
        tvHeight.setText(user.getHeight());
        etAboutMe.setText(user.getAboutMe());
        profilePicture = user.getProfilePicture();

        if(!profilePicture.getUrl().isEmpty())
            Picasso.get().load(Uri.parse(profilePicture.getUrl())).into(ivProfilePicture);
    }

    @Override
    public void setAttr(User user, SingleChoiceAttributes atrr, List<City> cities) {
        //ivProfilePicture;

        String test = user.getModel();

        SpinnerDataAdapter mGenderDataAdapter = new SpinnerDataAdapter<Gender>(this, R.layout.spinner_item, atrr.getGender());
        sGender.setAdapter(mGenderDataAdapter);
        sGender.setPrompt(getResources().getString(R.string.spiner_item_placeholder));
        sGender.setSelection(mGenderDataAdapter.getPosition(user.getGender()));

        SpinnerDataAdapter mEthnicityDataAdapter = new SpinnerDataAdapter<Ethnicity>(this, R.layout.spinner_item, atrr.getEthnicity());
        sEthnicity.setAdapter(mEthnicityDataAdapter);
        sEthnicity.setPrompt(getResources().getString(R.string.spiner_item_placeholder));
        sEthnicity.setSelection(mEthnicityDataAdapter.getPosition(user.getEthnicity()));

        SpinnerDataAdapter mReligionDataAdapter = new SpinnerDataAdapter<Religion>(this, R.layout.spinner_item, atrr.getReligion());
        sReligion.setAdapter(mReligionDataAdapter);
        sReligion.setPrompt(getResources().getString(R.string.spiner_item_placeholder));
        sReligion.setSelection(mReligionDataAdapter.getPosition(user.getReligion()));

        SpinnerDataAdapter mFigureDataAdapter = new SpinnerDataAdapter<Figure>(this, R.layout.spinner_item, atrr.getFigure());
        sFigure.setAdapter(mFigureDataAdapter);
        sFigure.setPrompt(getResources().getString(R.string.spiner_item_placeholder));
        sFigure.setSelection(mFigureDataAdapter.getPosition(user.getFigure()));

        SpinnerDataAdapter mLocationDataAdapter = new SpinnerDataAdapter<City>(this, R.layout.spinner_item, cities);
        sLocation.setAdapter(mLocationDataAdapter);
        sLocation.setPrompt(getResources().getString(R.string.spiner_item_placeholder));
        sLocation.setSelection(mLocationDataAdapter.getPosition(user.getLocation()));
    }

    @Override
    public User getUserOnValidation() {
        return new User(etAboutMe.getText().toString(),
                etBirthday.getText().toString(),
                etDisplayName.getText().toString(),
                (Ethnicity)sEthnicity.getSelectedItem(),
                (Figure)sFigure.getSelectedItem(),
                (Gender)sGender.getSelectedItem(),
                tvHeight.getText().toString(),
                (City)sLocation.getSelectedItem(),
                profilePicture,
                (Religion)sReligion.getSelectedItem()
                );
    }

    @Override
    public void error() {
        Toast.makeText(this, getResources().getText(R.string.error_edit_profile), Toast.LENGTH_LONG).show();
    }

    @Override
    public void goBack() {

    }

    @OnClick(R.id.btnSave)
    void pressedSave()
    {
        if(this.presenter==null)return;
        this.presenter.pressedSave();
    }

}
