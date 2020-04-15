package com.example.lco;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseDTO implements Parcelable {
    private String exercise_name;
    private String exercise_time;
    private int exercise_image;

    public ExerciseDTO(String exercise_name, String exercise_time, int exercise_image) {
        this.exercise_name = exercise_name;
        this.exercise_time = exercise_time;
        this.exercise_image = exercise_image;
    }

    protected ExerciseDTO(Parcel in) {
        exercise_name = in.readString();
        exercise_time = in.readString();
        exercise_image = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exercise_name);
        dest.writeString(exercise_time);
        dest.writeInt(exercise_image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExerciseDTO> CREATOR = new Creator<ExerciseDTO>() {
        @Override
        public ExerciseDTO createFromParcel(Parcel in) {
            return new ExerciseDTO(in);
        }

        @Override
        public ExerciseDTO[] newArray(int size) {
            return new ExerciseDTO[size];
        }
    };

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getExercise_time() {
        return exercise_time;
    }

    public void setExercise_time(String exercise_time) {
        this.exercise_time = exercise_time;
    }

    public int getExercise_image() {
        return exercise_image;
    }

    public void setExercise_image(int exercise_image) {
        this.exercise_image = exercise_image;
    }
}
