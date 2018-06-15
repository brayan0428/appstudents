package brayan0428.appstudent.com.appstudents.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import brayan0428.appstudent.com.appstudents.ActivityEditarTarea;
import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.EditarMateriaActivity;
import brayan0428.appstudent.com.appstudents.POJOS.Materias;
import brayan0428.appstudent.com.appstudents.R;
import brayan0428.appstudent.com.appstudents.Utilidades;

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
        if(materiasList.get(position).getNota3() == 0){
            holder.necesitas.setText(Utilidades.retornarNotaMinima(
                    materiasList.get(position).getNota1(),
                    materiasList.get(position).getNota2(),
                    materiasList.get(position).getPorcentaje1(),
                    materiasList.get(position).getPorcentaje2()) + "");
            holder.definitiva.setText("");
        }else{
            holder.necesitas.setText("");
            double definitiva = (((materiasList.get(position).getNota1() * materiasList.get(position).getPorcentaje1()) / 100)
                                    + ((materiasList.get(position).getNota2() * materiasList.get(position).getPorcentaje2()) / 100)
                                    + ((materiasList.get(position).getNota3() * materiasList.get(position).getPorcentaje3()) / 100));
            holder.definitiva.setText(Utilidades.redondearDecimales(definitiva,1) + "");
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditarMateriaActivity.class);
                intent.putExtra("idMateria",materiasList.get(position).getId());
                intent.putExtra("nombreMateria",materiasList.get(position).getNombre());
                intent.putExtra("profesor",materiasList.get(position).getProfesor());
                intent.putExtra("salon",materiasList.get(position).getSalon());
                intent.putExtra("nota1",materiasList.get(position).getNota1());
                intent.putExtra("nota2",materiasList.get(position).getNota2());
                intent.putExtra("nota3",materiasList.get(position).getNota3());
                intent.putExtra("porcentaje1",materiasList.get(position).getPorcentaje1());
                intent.putExtra("porcentaje2",materiasList.get(position).getPorcentaje2());
                intent.putExtra("porcentaje3",materiasList.get(position).getPorcentaje3());
                context.startActivity(intent);
                ((Activity)context).finish();
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
