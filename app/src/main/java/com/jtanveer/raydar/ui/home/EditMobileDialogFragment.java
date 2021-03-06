package com.jtanveer.raydar.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jtanveer.raydar.R;
import com.jtanveer.raydar.validation.InputValidator;

import static android.app.Activity.RESULT_OK;

public class EditMobileDialogFragment extends DialogFragment {

    public EditMobileDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public static EditMobileDialogFragment newInstance(Long id, String mobile) {
        EditMobileDialogFragment fragment = new EditMobileDialogFragment();
        Bundle args = new Bundle();
        args.putString("mobile", mobile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        String mobile = getArguments().getString("mobile", "");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Edit Mobile Number");
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mobile_input, null, false);
        EditText etMobile = layout.findViewById(R.id.et_mobile);
        etMobile.setInputType(InputType.TYPE_CLASS_PHONE);
        etMobile.setText(mobile);
        alertDialogBuilder.setView(layout);
        alertDialogBuilder.setPositiveButton("OK", null);
        alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(dialog -> {
            Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            b.setOnClickListener(view -> {
                InputValidator validator = new InputValidator();
                if (validator.isValidMobile(etMobile.getText().toString())) {
                    Intent intent = new Intent();
                    intent.putExtra("mobile", etMobile.getText().toString());
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                    dismiss();
                } else {
                    etMobile.setError("Mobile number is not valid");
                }
            });
        });
        return alertDialog;
    }
}
