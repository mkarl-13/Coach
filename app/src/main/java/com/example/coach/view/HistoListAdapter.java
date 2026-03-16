package com.example.coach.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach.R;
import com.example.coach.api.ICallbackApi;
import com.example.coach.contract.IHistoView;
import com.example.coach.model.Profil;
import com.example.coach.presenter.HistoPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoListAdapter extends RecyclerView.Adapter<HistoListAdapter.ViewHolder>{
    private List<Profil> profils;
    private IHistoView vue;

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public HistoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_liste_histo, parent, false);
        return new HistoListAdapter.ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getBindingAdapterPosition()} which
     * will have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull HistoListAdapter.ViewHolder holder, int position) {
        Double img = profils.get(position).getImg();
        String img1desimal = String.format("%.01f", img);
        holder.txtListIMG.setText(img1desimal);

        Date dateMesure = profils.get(position).getDateMesure();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String dateFormatee = sdf.format(dateMesure);
        holder.txtListDate.setText(dateFormatee);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return profils.size();
    }

    public HistoListAdapter(List<Profil> profils, IHistoView vue) {
        this.profils = profils;
        this.vue = vue;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtListDate, txtListIMG;
        public final ImageButton btnListSuppr;
        private HistoPresenter presenter;
        private void btnListSuppr_clic() {
            int position = getBindingAdapterPosition();
            presenter.supprProfil(profils.get(position), new ICallbackApi<Void>() {
                @Override
                public void onSuccess(Void result) {
                    profils.remove(position);
                    notifyItemRemoved(position);
                }

                @Override
                public void onError() {

                }
            });
        }

        private void txtListDateOrImg_clic() {
            int position = getBindingAdapterPosition();
            presenter.transfertProfil(profils.get(position));
        }
        private void init() {
            presenter = new HistoPresenter(vue);
            btnListSuppr.setOnClickListener(v -> btnListSuppr_clic());

            txtListDate.setOnClickListener(v -> txtListDateOrImg_clic());
            txtListIMG.setOnClickListener(v -> txtListDateOrImg_clic());
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtListDate = (TextView) itemView.findViewById(R.id.txtListDate);
            txtListIMG = (TextView) itemView.findViewById(R.id.txtListIMG);
            btnListSuppr = (ImageButton) itemView.findViewById(R.id.btnListSuppr);

            init();
        }
    }
}
