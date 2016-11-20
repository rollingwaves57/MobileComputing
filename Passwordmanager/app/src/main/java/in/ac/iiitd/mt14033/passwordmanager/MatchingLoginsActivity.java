package in.ac.iiitd.mt14033.passwordmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import in.ac.iiitd.mt14033.passwordmanager.dialog.DialogMatchingLogin;
import in.ac.iiitd.mt14033.passwordmanager.model.MatchingLogin;
import in.ac.iiitd.mt14033.passwordmanager.service.MyAccessibilityService;
/**
 * Created by Madhur on 09/11/16.
 */
public class MatchingLoginsActivity extends AppCompatActivity implements DialogMatchingLogin.DialogMatchingLoginListner{
    private String requesting_package;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_matching_logins);

        requesting_package = getIntent().getExtras().getString(CommonContants.MATCHING_LOGIN_PACKAGE_NAME);
        Bundle bundle = new Bundle();
        bundle.putString(CommonContants.MATCHING_LOGIN_PACKAGE_NAME, requesting_package);

        DialogMatchingLogin dialogMatchingLogin = new DialogMatchingLogin();
        dialogMatchingLogin.setArguments(bundle);
        dialogMatchingLogin.show(getSupportFragmentManager(), CommonContants.DIALOG_MATCHING_LOGIN);
    }

    /**
     * Dialog matching login callback methods implementation
     */
    @Override
    public void onDialogMatchingAddLoginClick(DialogFragment dialog) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(CommonContants.preference_file_key, Context.MODE_PRIVATE);
        String logged_user = sharedPref.getString(CommonContants.LOGGED_IN_USER, null);
        if(logged_user!=null) {
            Intent intent = new Intent(this, AddLoginActivity.class);
            intent.putExtra(CommonContants.MATCHING_LOGIN_PACKAGE_NAME, requesting_package);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.add_login_not_logged_in), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(CommonContants.MATCHING_LOGIN_PACKAGE_NAME, requesting_package);
            startActivity(intent);
        }

    }

    @Override
    public void onDialogMatchingCancelClick() {
        finish();
        MatchingLoginsActivity.this.overridePendingTransition(0,0);
    }

    @Override
    public void onDialogMatchingLoginClick(DialogFragment dialog, MatchingLogin matchingLogin) {
        MyAccessibilityService accessibilityService = MyAccessibilityService.getInstance();
        if(accessibilityService!=null) {
            accessibilityService.onSelectMatchingLogin(matchingLogin);
        }
        else {
            Log.v(getString(R.string.VTAG), "Error. Accessibility Service not avaialble");
        }
        onDialogMatchingCancelClick();
    }
}
