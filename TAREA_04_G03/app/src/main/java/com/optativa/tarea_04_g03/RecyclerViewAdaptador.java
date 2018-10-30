package com.optativa.tarea_04_g03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.optativa.pojos.Usuario;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView usuario;
        private TextView nombre;
        private TextView email;
        private TextView celular;
        private ImageView imagen;
        private TextView genero;
        private TextView fecha;
        private TextView asignaturas;
        private TextView becado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usuario = (TextView) itemView.findViewById(R.id.txt_usuario_registro);
            nombre = (TextView) itemView.findViewById(R.id.txt_nombre_registro);
            email = (TextView) itemView.findViewById(R.id.txt_email_registro);
            celular = (TextView) itemView.findViewById(R.id.txt_celular_registro);
            genero = (TextView) itemView.findViewById(R.id.txt_genero_registro);
            fecha = (TextView) itemView.findViewById(R.id.txt_fecha_registro);
            asignaturas = (TextView) itemView.findViewById(R.id.txt_asignaturas_registro);
            becado = (TextView) itemView.findViewById(R.id.txt_becado_registro);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_registro);
        }
    }

    public static List<Usuario> usuarios;

    public static void resetearLista() {
        usuarios.clear();
    }

    public RecyclerViewAdaptador(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.usuario.setText("Usuario: " + usuarios.get(i).getUsuario());
        viewHolder.nombre.setText("Nombre: " + usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellido());
        viewHolder.email.setText("E-mail: " + usuarios.get(i).getEmail());
        viewHolder.celular.setText("Celular: " + usuarios.get(i).getCelular());
        if (usuarios.get(i).getGenero() == 0) {
            viewHolder.genero.setText("Genero: " + "Masculino");
        } else if (usuarios.get(i).getGenero() == 1) {
            viewHolder.genero.setText("Genero: " + "Femenino");
        } else if (usuarios.get(i).getGenero() == 2) {
            viewHolder.genero.setText("Genero: " + "Prefiere no decirlo");
        }
        viewHolder.fecha.setText("Fecha nacimiento: " + new SimpleDateFormat("dd-MM-yyyy").format(usuarios.get(i).getFecha()));
        List<String> materias = usuarios.get(i).getAsignaturas();
        StringBuilder sb = new StringBuilder();
        sb.append("Asignaturas: ");
        for (String s : materias) {
            sb.append(s).append("  ");
        }
        viewHolder.asignaturas.setText(sb);
        if (usuarios.get(i).getBecado() == true) {
            viewHolder.becado.setText("Becado: si");
        } else {
            viewHolder.becado.setText("Becado: no");
        }
        byte[] foto = usuarios.get(i).getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        viewHolder.imagen.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
