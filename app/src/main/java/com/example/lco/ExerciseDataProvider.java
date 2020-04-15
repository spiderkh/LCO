package com.example.lco;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDataProvider {

    private static ArrayList<ExerciseDTO> data = new ArrayList();
/*    Squat  1 min
    Lunge  1 min
    plank  30 second
    burpee  1 min
    puspup  20
    crunch  30 second*/

    static
    {
        data.add(new ExerciseDTO("Squat","(1 min)",R.drawable.push_up));
        data.add(new ExerciseDTO("Lunge","(1 min)",R.drawable.push_up));
        data.add(new ExerciseDTO("Plank","(30 sec)",R.drawable.push_up));
        data.add(new ExerciseDTO("Burpee","(1 min)",R.drawable.push_up));
        data.add(new ExerciseDTO("Puspup","(1 min)",R.drawable.push_up));
        data.add(new ExerciseDTO("abc","(30 sec)",R.drawable.push_up));
        data.add(new ExerciseDTO("def","(30 sec)",R.drawable.push_up));
        data.add(new ExerciseDTO("fgh","(30 sec)",R.drawable.push_up));
        data.add(new ExerciseDTO("hig","(30 sec)",R.drawable.push_up));




    }

    public ArrayList<ExerciseDTO> getData(){return data;}
}
