package brayan0428.appstudent.com.appstudents.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import brayan0428.appstudent.com.appstudents.POJOS.Tareas;
import brayan0428.appstudent.com.appstudents.R;

/**
 * Created by bllanos on 25/04/2018.
 */

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {
    Context context;
    List<Tareas> tareasList;

    public TareasAdapter(Context context,List<Tareas> tareasList){
        this.context = context;
        this.tareasList = tareasList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.titulo.setText(tareasList.get(position).getTitulo());
        holder.fecha.setText(tareasList.get(position).getFecha());
        String Hora = tareasList.get(position).getHoraIni() + " - " + tareasList.get(position).getHoraFin();
        holder.hora.setText(Hora);
        holder.deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tareasList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tareasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo,fecha,hora;
        ImageView deleteTask;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardTarea);
            titulo = itemView.findViewById(R.id.Titulo);
            fecha = itemView.findViewById(R.id.Fecha);
            hora = itemView.findViewById(R.id.Hora);
            deleteTask = itemView.findViewById(R.id.deleteTask);
        }
    }

}
