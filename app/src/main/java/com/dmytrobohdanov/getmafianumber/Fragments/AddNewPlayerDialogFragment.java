package com.dmytrobohdanov.getmafianumber.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.dmytrobohdanov.getmafianumber.R;

/**
 * Dialog to add new player
 */
public class AddNewPlayerDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.add_player_dialog_title);

        builder.setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_add_new_player, null));

        builder.setPositiveButton(R.string.add, (dialog, id) -> {
            // User clicked OK button
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
            dismiss();
        });

        return builder.create();
    }

    public interface AddNewPlayerDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
//        public void onDialogNegativeClick(DialogFragment dialog);
    }
}
