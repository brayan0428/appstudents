package brayan0428.appstudent.com.appstudents.Fragments;

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

/**
 * Created by bllanos on 24/04/2018.
 */

public class ListarAudiosFragment extends Fragment{
    ListView listView;

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
                Uri datos = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/audio_clases/" + listView.getItemAtPosition(i).toString());
                MediaPlayer mp = MediaPlayer.create(view.getContext(), datos);
                mp.start();
                Toast.makeText(getContext(),listView.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void listarAudios(View v){
        List<String> list = new ArrayList<>();
        File path =  new File(Environment.getExternalStorageDirectory().getPath(),"/audio_clases");
        File files[] = path.listFiles();
        for(int i=0;i< files.length;i++){
            list.add(files[i].getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(v.getContext(),android.R.layout.simple_list_item_1,list);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
    }
}
