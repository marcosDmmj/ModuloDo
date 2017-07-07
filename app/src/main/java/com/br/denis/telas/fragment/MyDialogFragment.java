package com.br.denis.telas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.br.denis.classesBasicas.Evento;
import com.example.exempleswipetab.R;

/**
 * Created by denis on 06/07/17.
 */

public class MyDialogFragment extends DialogFragment {

    public static MyDialogFragment newInstance(Evento evento) {
        MyDialogFragment f = new MyDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putParcelable("evento",evento);
        f.setArguments(args);

        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_email_layout, container,
                false);
        getDialog().setTitle("Enviar email");
        Evento evento = getArguments().getParcelable("evento");

        EditText editEmail = (EditText) rootView.findViewById(R.id.editEmail);
        EditText editAssunto = (EditText) rootView.findViewById(R.id.editAssunto);
        EditText editSubject = (EditText) rootView.findViewById(R.id.editSubject);
        final RadioButton radioYes = (RadioButton) rootView.findViewById(R.id.radioYes);
        Button buttonEnviarEmail = (Button) rootView.findViewById(R.id.buttonEnviarEmail);

        if (getArguments() != null) {
        //if (evento != null){
            editEmail.setText(evento.getEmail());
            editAssunto.setText(evento.getTitulo());
            editSubject.setText("Solicitação aceita!\n" +
                    "Data: "+evento.getdata_inicio());
            buttonEnviarEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (radioYes.isChecked()){

                    } else {

                    }
                    // Chamando app para envio de email!
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"youremail@yahoo.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "subject");
                    email.putExtra(Intent.EXTRA_TEXT, "message");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                }
            });
        }

        return rootView;
    }

}
