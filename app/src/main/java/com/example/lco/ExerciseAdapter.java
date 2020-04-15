package com.example.lco;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolderClass> {
ArrayList<ExerciseDTO> excersieList;
    private Context fContext;

    private static final String TAG = "ExerciseAdapter";

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"ExerciseAdapter onCreateViewHolder");
        View itemView=LayoutInflater.from(fContext).inflate(R.layout.card_view_exercise_layout,parent,false);

        return new ViewHolderClass(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        ExerciseDTO exerciseDTO=excersieList.get(position);
        holder.exercise_name.setText(exerciseDTO.getExercise_name());
        holder.excercise_time.setText(exerciseDTO.getExercise_time());
       holder.exercise_image.setImageResource(exerciseDTO.getExercise_image());

    }

    @Override
    public int getItemCount() {
        return excersieList.size();
    }


    public ExerciseAdapter(ArrayList<ExerciseDTO> excersieList,Context fContext) {
        this.excersieList = excersieList;
        this.fContext=fContext;
    }

    class ViewHolderClass extends RecyclerView.ViewHolder{
        ImageView exercise_image;
        TextView exercise_name,excercise_time;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            exercise_image=itemView.findViewById(R.id.card_image_id);
            exercise_name=itemView.findViewById(R.id.card_name_id);
            excercise_time=itemView.findViewById(R.id.card_name_time);
        }
    }


}
