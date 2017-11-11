package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Presenter.ListParadasPresenter;
import es.unican.grupo1.tus_santander.R;

/**
 * Created by Adrian on 25/10/2017.
 */
public class ParadasFragment extends ListFragment implements IParadasFragment {
    private DataCommunication dataCommunication;
    private int identifierLinea;
    private ProgressDialog dialog;
    private TextView textViewMensajeError;

    private ListParadasPresenter listParadasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paradas_list, container, false);
        textViewMensajeError = (TextView) view.findViewById(R.id.textViewMensajeError);
        TextView buscarParadas = (TextView) view.findViewById(R.id.editText_search);
        buscarParadas.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, 0, 0);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataCommunication = (DataCommunication) getContext();
        identifierLinea = dataCommunication.getLineaIdentifier();
        this.listParadasPresenter = new ListParadasPresenter(getContext(), this, identifierLinea);
        this.dialog = new ProgressDialog(getContext());
        this.listParadasPresenter.start();
        // TODO
        ((DataCommunication) getContext()).setMostrarBotonActualizar(true);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", "" + position);
        // cambio a la interfaz de estimaciones
        EstimacionesFragment fragmentEstimaciones = new EstimacionesFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutElements, fragmentEstimaciones);
        dataCommunication = (DataCommunication) getContext();
        dataCommunication.setParadaIdentifier(listParadasPresenter.getListParadasBus().get(position).getIdentificador());
        ft.addToBackStack(null);
        ft.commit();
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showList(List<Parada> paradasList) {
        ListParadasAdapter listParadasAdapter = new ListParadasAdapter(getContext(), paradasList);
        getListView().setAdapter(listParadasAdapter);
    }

    @Override
    public void showProgress(boolean state) {
        if (state) {
            dialog.setMessage("Cargando datos...");
            dialog.show();
        } else {
            dialog.cancel();
        }
    }

    public void showErrorMessage() {
        textViewMensajeError.setVisibility(View.VISIBLE);
        textViewMensajeError.setText("Internet no disponible. Inténtalo más tarde.");
    }

    public ProgressDialog getDialog() {
        return dialog;
    }
}