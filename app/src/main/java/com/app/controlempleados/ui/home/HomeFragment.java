package com.app.controlempleados.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.controlempleados.Empleados.Empleado;
import com.app.controlempleados.R;
import com.app.controlempleados.Transacciones.Procesos;
import com.app.controlempleados.Transacciones.SQLiteConecxion;
import com.app.controlempleados.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    SQLiteConecxion conecxion;
    ListView lstEmpleados;
    ArrayList<Empleado> ListaEmpleados;
    ArrayList<String> arrayEmpleados;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View Reg = inflater.inflate(R.layout.fragment_home, container, false);

        lstEmpleados = (ListView) Reg.findViewById(R.id.ListEmpleados);
        conecxion = new SQLiteConecxion(getContext(), Procesos.DATABASE,null,1);

        getEmpleados();

        return Reg;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getEmpleados(){
        SQLiteDatabase db = conecxion.getWritableDatabase();
        ListaEmpleados = new ArrayList<Empleado>();
        Empleado empl = null;

        Cursor dat = db.rawQuery(Procesos.SELECT_TABLE_EMPLEADOS,null);

        while (dat.moveToNext()){
            empl = new Empleado();
            empl.setID(dat.getInt(0));
            empl.setNOMBRE(dat.getString(1));
            empl.setAPELLIDO(dat.getString(2));
            empl.setEDAD(dat.getInt(3));
            empl.setDIRECCION(dat.getString(4));
            empl.setPUESTO(dat.getString(5));

            ListaEmpleados.add(empl);
        }
        dat.close();

        arrayEmpleados = new ArrayList<String>();

        for (int i=0;i<ListaEmpleados.size(); i++ ){
            arrayEmpleados.add(ListaEmpleados.get(i).getID()+" || " + ListaEmpleados.get(i).getNOMBRE()+ " "+
                               ListaEmpleados.get(i).getAPELLIDO()+ " || " +ListaEmpleados.get(i).getPUESTO());
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,arrayEmpleados);

        lstEmpleados.setAdapter(adapter);
    }
}