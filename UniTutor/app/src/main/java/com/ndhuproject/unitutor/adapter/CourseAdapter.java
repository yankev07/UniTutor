package com.ndhuproject.unitutor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.helper.CourseInformation;
import com.ndhuproject.unitutor.helper.TutorInformation;

import java.util.List;

/**
 * Created by kevin on 6/1/18.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private Context mCtx;
    private List<CourseInformation> coursesList;

    public CourseAdapter(Context mCtx, List<CourseInformation> coursesList) {
        this.mCtx = mCtx;
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_courses, parent, false);
        return new CourseAdapter.CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        CourseInformation course = coursesList.get(position);
        if(course.rating == 1){
            holder.imageViewRating.setImageResource(R.drawable.star1);
        }
        else if(course.rating == 2){
            holder.imageViewRating.setImageResource(R.drawable.stars2);
        }
        else if(course.rating == 3){
            holder.imageViewRating.setImageResource(R.drawable.stars3);
        }
        else if(course.rating == 4){
            holder.imageViewRating.setImageResource(R.drawable.stars4);
        }
        else{
            holder.imageViewRating.setImageResource(R.drawable.stars5);
        }
        holder.courseName.setText(course.courseName);
        holder.textViewCourseCategory.setText(course.courseCategory);
        holder.textViewGECategory.setText(course.generalEducationCategory);
        holder.textViewInstructorName.setText(course.instructorName);
        holder.textViewPersonalFeedback.setText(course.personalFeedback);

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewRating;
        TextView courseName;
        TextView textViewCourseCategory;
        TextView textViewGECategory;
        TextView textViewInstructorName;
        TextView textViewPersonalFeedback;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewRating = (ImageView) itemView.findViewById(R.id.course_rating);
            courseName = (TextView) itemView.findViewById(R.id.course_name);
            textViewCourseCategory = (TextView) itemView.findViewById(R.id.course_category);
            textViewGECategory = (TextView) itemView.findViewById(R.id.general_education_category);
            textViewInstructorName = (TextView) itemView.findViewById(R.id.instructor_name);
            textViewPersonalFeedback = (TextView) itemView.findViewById(R.id.personal_feedback);

        }
    }
}
