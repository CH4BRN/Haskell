package com.uldskull.tp_intent_v2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MessagingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagingFragment newInstance(String param1, String param2) {
        MessagingFragment fragment = new MessagingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messaging, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

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

        button_validate = getView().findViewById(R.id.validate_btn);
        button_pickNumber = getView().findViewById(R.id.pick_nb_button);
        button_pickMail = getView().findViewById(R.id.pick_mail_button);
        button_send = getView().findViewById(R.id.send_button);
        initializeButtonSend();
    }
    public  void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getActivity().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initializeTextView(){

        textView_phoneNumber = getView().findViewById(R.id.display_phone_nb_text_view);
        textView_message = getView().findViewById(R.id.display_message);
        textView_mail = getView().findViewById(R.id.display_mail_text_view);
    }

    private void initializeButtonSend(){
        // button_send.setVisibility(View.INVISIBLE);
    }
    private void initializeEditText(){

        editText_message = getView().findViewById(R.id.message_edit_text);
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
                sentIntent.send(getActivity().getApplicationContext(), 0, intent);


            }catch (PendingIntent.CanceledException e){

                return;
            }
        }
        getActivity();

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
        return PendingIntent.getBroadcast(getActivity(), 0, getSendingSmsIntent(), 0);
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

        getActivity().setContentView(R.layout.activity_main);
        Intent loginIntent = new Intent();
        loginIntent.setClassName("com.uldskull.login", "com.uldskull.login.LoginActivity");
        startActivityForResult(loginIntent, LOGIN_REQUEST_CODE);

    }

}
