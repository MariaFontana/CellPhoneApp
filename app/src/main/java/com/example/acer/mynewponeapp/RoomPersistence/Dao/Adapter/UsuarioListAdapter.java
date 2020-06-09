package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Usuario;
import com.example.acer.mynewponeapp.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class UsuarioListAdapter extends RecyclerView.Adapter<UsuarioListAdapter.UsuarioViewHolder>   {

        class UsuarioViewHolder extends RecyclerView.ViewHolder {
            private final TextView usuarioItemView;
            private final TextView usuarioItemView2;

            private UsuarioViewHolder(View itemView) {
                super(itemView);
                usuarioItemView = itemView.findViewById(R.id.name);
                usuarioItemView2 = itemView.findViewById(R.id.dia);
            }
        }

        private final LayoutInflater mInflater;
        private List<Usuario> mUsuario; // Cached copy of words

        UsuarioListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.activity_main, parent, false);
            return new UsuarioViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(UsuarioViewHolder holder, int position) {
            if (mUsuario != null) {
                Usuario current = mUsuario.get(position);
                holder.usuarioItemView.setText(current.getNombre());
            } else {
                // Covers the case of data not being ready yet.
                holder.usuarioItemView.setText("No Word");
            }
        }

        void setWords(List<Usuario> usuario) {
            mUsuario = usuario;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // mWords has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (mUsuario != null)
                return mUsuario.size();
            else return 0;
        }
}
