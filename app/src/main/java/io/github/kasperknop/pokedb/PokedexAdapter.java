package io.github.kasperknop.pokedb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.palette.graphics.Target;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {

    private List<Pokemon> pokemonList;
    private Context context;
    private Target someTarget = new Target.Builder()

            .setPopulationWeight(0.64f)
            .setLightnessWeight(1f)
            .setTargetLightness(0.68f)
            .setMinimumLightness(0.1f)
            .setMaximumLightness(0.9f)
            .setSaturationWeight(0.2f)
            .setTargetSaturation(1f)
            .setMinimumSaturation(0.1f)
            .setMaximumSaturation(1f)

            .setExclusive(false)
            .build();

    public PokedexAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokedexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_pokedex, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokedexAdapter.ViewHolder holder, int position) {
        Glide.with(context).asBitmap()
                .load(Uri.parse("file:///android_asset/images/pokemon/" + pokemonList.get(position).image))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.image.setImageBitmap(resource);

                        Palette.from(resource)
                                .addTarget(someTarget)
                                .maximumColorCount(32)
                                .generate(new Palette.PaletteAsyncListener() {
                                    public void onGenerated(Palette p) {
                                        holder.cardView.setCardBackgroundColor(p.getColorForTarget(someTarget, Color.WHITE));
                                    }
                                });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        //holder.name.setText(pokemonList.get(position).getName());
        //holder.number.setText("#" + pokemonList.get(position).getNumber());
        //holder.primaryType.setText(pokemonList.get(position).getPrimaryType());
        //holder.secondaryType.setText(pokemonList.get(position).getSecondaryType());

        holder.name.setText("");
        holder.number.setText("");
        holder.primaryType.setText("");
        holder.secondaryType.setText("");

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        TextView number;
        TextView primaryType;
        TextView secondaryType;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_item_dex_name);
            image = itemView.findViewById(R.id.list_item_dex_image);
            number = itemView.findViewById(R.id.list_item_dex_number);
            primaryType = itemView.findViewById(R.id.list_item_dex_type_primary);
            secondaryType = itemView.findViewById(R.id.list_item_dex_type_secondary);
            cardView = itemView.findViewById(R.id.list_item_pokedex);
        }
    }

}
