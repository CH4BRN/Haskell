package com.uldskull.tp_intent_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class MessagingActivity extends AppCompatActivity {

    /**
     * Identifiers
     */
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private static final int SELECT_PHONE_NUMBER = 1;
    private static final int SELECT_MAIL_ADDRESS = 3;
    private static final int LOGIN_REQUEST_CODE = 4;


    /**
     * Sending button.
     */
    Button button_send;

    Button button_pickNumber;
    Button button_pickMail;
    Button button_validate;
    String TAG = "MainActivity";

    TextView textView_phoneNumber;
    TextView textView_mail;
    TextView textView_message;
    EditText editText_message;
    String phoneNb;
    String message;
    private String numberToSend;
    private String mailToSend;
    boolean isPicked = false;

    private void validateMessage(){

        message = editText_message.getText().toString();
        textView_message.setText(message);

        hideKeyboard();
    }
    private void initializeBtn(){

        button_validate = findViewById(R.id.validate_btn);
        button_pickNumber = findViewById(R.id.pick_nb_button);
        button_pickMail = findViewById(R.id.pick_mail_button);
        button_send = findViewById(R.id.send_button);
        initializeButtonSend();
    }
    public  void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initializeTextView(){

        textView_phoneNumber = findViewById(R.id.display_phone_nb_text_view);
        textView_message = findViewById(R.id.display_message);
        textView_mail = findViewById(R.id.display_mail_text_view);
    }

    private void initializeButtonSend(){
        // button_send.setVisibility(View.INVISIBLE);
    }
    private void initializeEditText(){

        editText_message = findViewById(R.id.message_edit_text);
    }


    private void initialize(){

        initializeTextView();
        initializeBtn();
        initializeEditText();
        setListener();
    }

    private final String contact_uri = "content://contacts";
    private void pickNumberContact(){


        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse(contact_uri));

        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        startActivityForResult(pickContactIntent, SELECT_PHONE_NUMBER);
        //startActivity(pickContactIntent);
    }

    private void setButtonPickNumberListener(){


        button_pickNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickNumberContact();
            }
        });

    }
    private void setButtonValidateListener(){

        button_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateMessage();
            }
        });
    }
    private void pickMailContact(){

        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse(contact_uri));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);

        startActivityForResult(pickContactIntent, SELECT_MAIL_ADDRESS);

    }

    private void setButtonPickMailListener(){

        button_pickMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickMailContact();
            }
        });
    }
    private void setListener(){

        setButtonListener();
    }
    private void setButtonListener(){

        setButtonValidateListener();
        setButtonPickNumberListener();
        setButtonPickMailListener();
        setButtonSendListener();
    }

    private void sendSmsWithIntent(){
        phoneNb = null;

        if(phoneNb != null)
        {

            // PendingIntent is a description of an Intent and target action to perform with it
            PendingIntent sentIntent = getSentPendingIntent();
            SmsManager sms = SmsManager.getDefault();


            sms.sendTextMessage(
                    //Destination address
                    phoneNb,
                    //scAddress (?)
                    null,
                    //Text
                    message,
                    //Sent intent
                    sentIntent,
                    //Delivery intent
                    null);
        }
        else
        {

            PendingIntent sentIntent = getSentPendingIntent();
            Intent intent = getActionViewSmsIntent();

            try {
                sentIntent.send(this.getApplicationContext(), 0, intent);


            }catch (PendingIntent.CanceledException e){

                return;
            }
        }
        getActivity(this, 0, getActionViewIntent(), 0 );
    }

    private Intent getActionViewIntent(){
        return new Intent("android.intent.action.VIEW");
    }
    private Intent getActionViewSmsIntent(){
        Intent intent = getActionViewIntent();
        intent.putExtra("sms_body", message);
        intent.setData(Uri.parse("sms:"));
        return intent;
    }
    private PendingIntent getSentPendingIntent(){
        return PendingIntent.getBroadcast(this, 0, getSendingSmsIntent(), 0);
    }

    private Intent getSendingSmsIntent(){
        return new Intent("SENDING_SMS");
    }
    private void setButtonSendListener(){

        //button_send = findViewById(R.id.send_button);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsWithIntent();
            }
        });
    }
    private void setMainView(){

        setContentView(R.layout.activity_main);
        Intent loginIntent = new Intent();
        loginIntent.setClassName("com.uldskull.login", "com.uldskull.login.LoginActivity");
        startActivityForResult(loginIntent, LOGIN_REQUEST_CODE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setMainView();
        setContentView(R.layout.activity_messaging);
        initialize();

    }
}
