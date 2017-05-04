package com.dmytrobohdanov.getmafianumber.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.dmytrobohdanov.getmafianumber.R;
import com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DataModels.PlayerDataModel;
import com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DatabaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Dialog to add new player
 */
public class AddNewPlayerDialogFragment extends DialogFragment {
    @BindView(R.id.add_player_dialog_player_name)
    EditText etName;

    @BindView(R.id.add_player_dialog_player_alias)
    EditText etAlias;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.add_player_dialog_title);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_new_player, null);
        ButterKnife.bind(this, view);
        builder.setView(view);

        builder.setPositiveButton(R.string.add, (dialog, id) -> {
            // User clicked OK button
            DatabaseUtils.addNewUser(new PlayerDataModel(etName.getText().toString(), etAlias.getText().toString()));
        });

        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
            dismiss();
        });

        return builder.create();
    }
}
