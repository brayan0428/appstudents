package brayan0428.appstudent.com.appstudents.Fragments;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import brayan0428.appstudent.com.appstudents.R;

/**
 * Created by bllanos on 24/04/2018.
 */

public class AudioFragment extends Fragment{
    TextInputEditText titulo;
    Chronometer cronometro;
    Button grabar,detener;
    MediaPlayer player;
    MediaRecorder recorder;
    File archivo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        grabar = view.findViewById(R.id.grabar);
        detener = view.findViewById(R.id.detener);
        titulo = view.findViewById(R.id.titulo);
        cronometro = view.findViewById(R.id.cronometro);
        File path =  new File(Environment.getExternalStorageDirectory().getPath(),"/audio_clases");
        path.mkdir();
        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titulo.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Debe ingresar un titulo para la grabación",Toast.LENGTH_LONG).show();
                    return;
                }
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                File path =  new File(Environment.getExternalStorageDirectory().getPath(),"/audio_clases");
                try{
                    archivo = File.createTempFile(titulo.getText().toString(),".3gp",path);
                }catch (IOException e){

                }
                recorder.setOutputFile(archivo.getPath());
                try {
                    recorder.prepare();
                }catch (IOException e){

                }
                recorder.start();
                cronometro.setBase(SystemClock.elapsedRealtime());
                cronometro.start();
                detener.setEnabled(true);
            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorder.stop();
                recorder.release();
                player = new MediaPlayer();
                try {
                    player.setDataSource(archivo.getAbsolutePath());
                } catch (IOException e) {
                }
                try {
                    player.prepare();
                } catch (IOException e) {
                }
                cronometro.stop();
                cronometro.setBase(SystemClock.elapsedRealtime());
                detener.setEnabled(false);
                titulo.setText("");
                Toast.makeText(view.getContext(),"Audio guardado exitosamente",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
