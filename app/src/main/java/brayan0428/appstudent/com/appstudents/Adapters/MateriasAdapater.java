package brayan0428.appstudent.com.appstudents.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.POJOS.Materias;
import brayan0428.appstudent.com.appstudents.R;

public class MateriasAdapater extends RecyclerView.Adapter<MateriasAdapater.ViewHolder> {
    Context context;
    List<Materias> materiasList;
    Procesos procesos;
    String msn;

    public MateriasAdapater(Context context, List<Materias> materiasList){
        this.context = context;
        this.materiasList = materiasList;
        this.procesos = new Procesos(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nombre.setText(materiasList.get(position).getNombre());
        holder.profesor.setText(materiasList.get(position).getProfesor());
        holder.salon.setText(materiasList.get(position).getSalon());
        holder.nota1.setText(returnNumber(materiasList.get(position).getNota1()));
        holder.nota2.setText(returnNumber(materiasList.get(position).getNota2()));
        holder.nota3.setText(returnNumber(materiasList.get(position).getNota3()));
        holder.deleteMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Esta seguro que desea eliminar la tarea?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                procesos.eliminarMateria(materiasList.get(position).getId());
                                materiasList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }

    public String returnNumber(double n){
        if(n==0){
            return "";
        }
        return String.valueOf(n);
    }
    @Override
    public int getItemCount() {
        return materiasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nombre,profesor,salon,nota1,nota2,nota3,necesitas,definitiva;
        ImageView deleteMateria;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardMateria);
            nombre = itemView.findViewById(R.id.Nombre);
            profesor = itemView.findViewById(R.id.Profesor);
            salon = itemView.findViewById(R.id.Salon);
            nota1 = itemView.findViewById(R.id.Nota1);
            nota2 = itemView.findViewById(R.id.Nota2);
            nota3 = itemView.findViewById(R.id.Nota3);
            necesitas = itemView.findViewById(R.id.Necesitas);
            definitiva = itemView.findViewById(R.id.Definitiva);
            deleteMateria = itemView.findViewById(R.id.deleteMateria);
        }
    }
}
