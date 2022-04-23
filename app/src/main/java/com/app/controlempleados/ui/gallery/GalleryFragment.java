package com.app.controlempleados.ui.gallery;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.controlempleados.R;
import com.app.controlempleados.Transacciones.Procesos;
import com.app.controlempleados.Transacciones.SQLiteConecxion;
import com.app.controlempleados.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    EditText txtNombres, txtApellidos, txtdireccion, txtPuesto, txtEdad;
    Button btnSave;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View Reg = inflater.inflate(R.layout.fragment_gallery, container, false);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtNombres = (EditText) Reg.findViewById(R.id.txtNombre);
        txtApellidos = (EditText) Reg.findViewById(R.id.txtApellidos);
        txtdireccion = (EditText) Reg.findViewById(R.id.txtDireccion);
        txtPuesto = (EditText) Reg.findViewById(R.id.txtPuesto);
        txtEdad = (EditText) Reg.findViewById(R.id.txtEdad);

        btnSave = (Button) Reg.findViewById(R.id.btnSave);

        setListener();

        return Reg;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setListener(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegEmpleado();
            }
        });
    }

    private void RegEmpleado(){
        SQLiteConecxion conex = new SQLiteConecxion(getContext(), Procesos.DATABASE, null,1);
        SQLiteDatabase db = conex.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put(Procesos.NOMBRE, txtNombres.getText().toString());
        datos.put(Procesos.APELLIDOS, txtApellidos.getText().toString());
        datos.put(Procesos.EDAD, txtEdad.getText().toString());
        datos.put(Procesos.DIRECCION, txtdireccion.getText().toString());
        datos.put(Procesos.PUESTO, txtPuesto.getText().toString());

        Long resul = db.insert(Procesos.TABLA_EMPLEADOS,Procesos.ID,datos);

        Toast.makeText(getContext(), "!Registro con Exit¡", Toast.LENGTH_SHORT).show();

        db.close();
        limpiar();
    }

    private void limpiar(){
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtdireccion.setText("");
        txtPuesto.setText("");
    }
}