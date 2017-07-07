package com.br.denis.telas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.br.denis.classesBasicas.Evento;
import com.br.denis.classesBasicas.OnTaskCompletedUpload;
import com.br.denis.classesBasicas.Util;
import com.br.denis.webservice.UploadRespostaProf;
import com.denis.mdulodoprofessor.R;

/**
 * Created by denis on 06/07/17.
 */

public class MyDialogFragment extends DialogFragment {

    private ActionListener actionListener;

    public static MyDialogFragment newInstance(Evento evento) {
        MyDialogFragment f = new MyDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putParcelable("evento",evento);
        f.setArguments(args);

        return f;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public interface ActionListener{
        void onUpdate();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_email_layout, container,
                false);
        getDialog().setTitle("Enviar email");
        final Evento evento = getArguments().getParcelable("evento");

        EditText editEmail = (EditText) rootView.findViewById(R.id.editEmail);
        final EditText editAssunto = (EditText) rootView.findViewById(R.id.editAssunto);
        final EditText editMessage = (EditText) rootView.findViewById(R.id.editMessage);
        final RadioButton radioYes = (RadioButton) rootView.findViewById(R.id.radioYes);
        final RadioButton radioNo = (RadioButton) rootView.findViewById(R.id.radioNo);
        Button buttonEnviarEmail = (Button) rootView.findViewById(R.id.buttonEnviarEmail);

        radioYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessage.setText("Solicitação aceita!\n" +
                        "Dia: "+ Util.dateToStringBR(Util.stringToDateComplete(evento.getdata_inicio())) + "\n" +
                        "Hora: "+Util.stringToDiffDate(evento.getdata_inicio(),evento.getdata_fim()));
            }
        });
        radioNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMessage.setText("Solicitação recusada!\n" +
                        "Dia: "+ Util.dateToStringBR(Util.stringToDateComplete(evento.getdata_inicio())) + "\n" +
                        "Hora: "+Util.stringToDiffDate(evento.getdata_inicio(),evento.getdata_fim()));
            }
        });
        editEmail.setText(evento.getEmail());
        editAssunto.setText(evento.getTitulo());
        editMessage.setText("Solicitação aceita!\n" +
                "Dia: "+ Util.dateToStringBR(Util.stringToDateComplete(evento.getdata_inicio())) + "\n" +
                "Hora: "+Util.stringToDiffDate(evento.getdata_inicio(),evento.getdata_fim()));
        buttonEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioYes.isChecked()){
                    new UploadRespostaProf(getActivity(), new OnTaskCompletedUpload() {
                        @Override
                        public void onTaskCompleted(int integer) {
                            Toast.makeText(getActivity(), "Evento criado com sucesso!", Toast.LENGTH_SHORT).show();
                            // Chamando app para envio de email!
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{evento.getEmail()});
                            email.putExtra(Intent.EXTRA_SUBJECT, editAssunto.getText().toString());
                            email.putExtra(Intent.EXTRA_TEXT, editMessage.getText().toString());
                            email.setType("message/rfc822");
                            startActivity(Intent.createChooser(email, "Choose an Email client :"));
                            dismiss();
                            actionListener.onUpdate();
                        }
                    }).execute("http://ufam-automation.net/marcosmoura/deleteAndInsert.php?Evento_id="+evento.getId());
                } else {
                    new UploadRespostaProf(getActivity(), new OnTaskCompletedUpload() {
                        @Override
                        public void onTaskCompleted(int integer) {
                            Toast.makeText(getActivity(), "Evento deletado com sucesso!", Toast.LENGTH_SHORT).show();
                            // Chamando app para envio de email!
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{evento.getEmail()});
                            email.putExtra(Intent.EXTRA_SUBJECT, editAssunto.getText().toString());
                            email.putExtra(Intent.EXTRA_TEXT, editMessage.getText().toString());
                            email.setType("message/rfc822");
                            startActivity(Intent.createChooser(email, "Choose an Email client :"));
                            dismiss();
                            actionListener.onUpdate();
                        }
                    }).execute("http://ufam-automation.net/marcosmoura/deleteEventTemp.php?Evento_id="+evento.getId());
                }

            }
        });

        return rootView;
    }

}
