package brayan0428.appstudent.com.appstudents.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import brayan0428.appstudent.com.appstudents.R;
import brayan0428.appstudent.com.appstudents.Utilidades;

/**
 * Created by bllanos on 24/04/2018.
 */

public class ListarAudiosFragment extends Fragment{
    ListView listView;
    List<String> list;
    public static ArrayAdapter<String> arrayAdapter;
    String ruta = Environment.getExternalStorageDirectory().getPath() + "/audio_clases/";
    int posicion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listaraudios,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listview);
        listarAudios(view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri datos = Uri.parse(ruta + listView.getItemAtPosition(i).toString());
                MediaPlayer mp = MediaPlayer.create(view.getContext(), datos);
                mp.start();
                Toast.makeText(getContext(),listView.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                posicion = i;
                builder.setMessage("Esta seguro que desea eliminar la tarea?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                File file = new File(ruta + listView.getItemAtPosition(posicion).toString());
                                file.delete();
                                list.remove(posicion);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create();
                builder.show();
                return true;
            }
        });
    }

    public void listarAudios(View v){
        list = new ArrayList<>();
        File path =  new File(Environment.getExternalStorageDirectory().getPath(),"/audio_clases");
        File files[] = path.listFiles();
        for(int i=0;i< files.length;i++){
            list.add(files[i].getName());
        }
        arrayAdapter = new ArrayAdapter<>(v.getContext(),android.R.layout.simple_list_item_1,list);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
    }
}
