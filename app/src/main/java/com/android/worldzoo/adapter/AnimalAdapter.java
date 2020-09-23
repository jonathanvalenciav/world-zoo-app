package com.android.worldzoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.worldzoo.R;
import com.android.worldzoo.entity.Animal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalAdapter extends BaseAdapter {
    private List<Animal> listAnimalsOut;
    private OnItemClickListener listener;
    private final LayoutInflater inflater;

    public AnimalAdapter(Context context, List<Animal> listAnimals, OnItemClickListener listener){
        this.listAnimalsOut = listAnimals;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    public void updateBasedOnSearch(ArrayList<Animal> searchResult) {
        this.listAnimalsOut = new ArrayList<>();
        this.listAnimalsOut.addAll(searchResult);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listAnimalsOut.size();
    }

    @Override
    public Animal getItem(int position) {
        return listAnimalsOut.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Animal animal = listAnimalsOut.get(position);

        if (view != null ) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.animal_item_layout, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.setListener(animal, listener);
        viewHolder.imageAnimal.setImageResource(animal.getImage());
        viewHolder.animalName.setText(animal.getName());
        viewHolder.animalDescription.setText(animal.getDescription());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.imgAnimalPhoto)
        ImageView imageAnimal;
        @BindView(R.id.txtAnimalName)
        TextView animalName;
        @BindView(R.id.txtAnimalDescription)
        TextView animalDescription;
        @BindView(R.id.container_main)
        RelativeLayout containerMain;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        void setListener(Animal animal, OnItemClickListener listener) {
            containerMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(animal);
                }
            });
        }
    }
}
