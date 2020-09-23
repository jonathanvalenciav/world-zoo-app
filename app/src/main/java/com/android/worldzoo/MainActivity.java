package com.android.worldzoo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.worldzoo.adapter.AnimalAdapter;
import com.android.worldzoo.adapter.OnItemClickListener;
import com.android.worldzoo.entity.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.lvAnimals)
    public ListView listViewAnimals;

    @BindView(R.id.txtSearchAnimal)
    public EditText searchAnimal;

    private AnimalAdapter animalAdapter;
    private HashMap<String, Integer> soundsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configSounds();
        loadAnimals();
    }

    private void loadAnimals(){
        List<Animal> listAnimals = new ArrayList<>();
        listAnimals.add(new Animal(R.drawable.cat, "Gato", "El gato doméstico, llamado popularmente gato, es un mamífero carnívoro de la familia Felidae. Es una subespecie domesticada por la convivencia con el ser humano."));
        listAnimals.add(new Animal(R.drawable.cow, "Vaca", "La vaca, en el caso de la hembra, o toro, en el caso del macho. es un mamífero artiodáctilo de la familia de los bóvidos."));
        listAnimals.add(new Animal(R.drawable.duck, "Pato", "Pato es el nombre común para ciertas aves de la familia Anatidae, principalmente de la subfamilia Anatinae y dentro de ella del género Anas. No son un grupo monofilético, ya que no se incluyen los cisnes ni los gansos."));
        listAnimals.add(new Animal(R.drawable.frog, "Rana", "Rana es un género de anfibios anuros de la familia Ranidae, que habita en Eurasia templada hasta Indochina. Las especies de este género se caracterizan por sus cinturas delgadas y la piel rugosa."));
        listAnimals.add(new Animal(R.drawable.lion, "León", "El león es un mamífero carnívoro de la familia de los félidos y una de las cinco especies del género Panthera."));
        listAnimals.add(new Animal(R.drawable.owl, "Búho", "Búho es el nombre común de aves de la familia Strigidae, del orden de los estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas y presentan una coloración amarilla o naranja en el iris."));
        listAnimals.add(new Animal(R.drawable.pig, "Cerdo", "El cerdo, también denominado puerco, porcino, marrano o cochino, es una subespecie de mamífero artiodáctilo de la familia Suidae. Es un animal doméstico usado en la alimentación humana por muchos pueblos."));
        listAnimals.add(new Animal(R.drawable.wolf, "Lobo", "El lobo es una especie de mamífero placentario del orden de los carnívoros. El perro doméstico se considera miembro de la misma especie según distintos indicios, la secuencia del ADN y otros estudios genéticos."));

        animalAdapter = new AnimalAdapter(this, listAnimals, this);
        listViewAnimals.setAdapter(animalAdapter);
    }

    private void configSounds() {
        this.soundsMap = new HashMap<String, Integer>();
        soundsMap.put("Gato", R.raw.cat);
        soundsMap.put("Vaca", R.raw.cow);
        soundsMap.put("Pato", R.raw.duck);
        soundsMap.put("Rana", R.raw.frog);
        soundsMap.put("León", R.raw.lion);
        soundsMap.put("Búho", R.raw.owl);
        soundsMap.put("Cerdo", R.raw.pig);
        soundsMap.put("Lobo", R.raw.wolf);
    }

    @Override
    public void onItemClick(Animal animal) {
        playAnimalSound(animal);
    }

    public void playAnimalSound(Animal animal) {
        if (animal != null) {
            int soundAnimalSelected = this.soundsMap.get(animal.getName());
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), soundAnimalSelected);
            mediaPlayer.start();
        }
    }
}