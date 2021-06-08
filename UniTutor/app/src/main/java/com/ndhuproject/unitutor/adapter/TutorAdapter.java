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
import com.ndhuproject.unitutor.helper.TutorInformation;

import java.util.List;

/**
 * Created by kevin on 5/24/18.
 */

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.TutorViewHolder> {

    private Context mCtx;
    private List<TutorInformation> tutorsList;

    public TutorAdapter(Context mCtx, List<TutorInformation> tutorsList) {
        this.mCtx = mCtx;
        this.tutorsList = tutorsList;
    }

    @NonNull
    @Override
    public TutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tutors, parent, false);
        return new TutorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorViewHolder holder, int position) {
        TutorInformation tutor = tutorsList.get(position);
        if(tutor.gender.equals("Male")){
            holder.imageViewAvatar.setImageResource(R.drawable.icon_male);
        }
        else{
            holder.imageViewAvatar.setImageResource(R.drawable.icon_female);
        }
        holder.textViewName.setText(tutor.fullName);
        holder.textViewDepartment.setText(tutor.department);
        holder.textViewSkill1.setText(tutor.skill1 +"  ");
        holder.textViewFee1.setText(tutor.skillFee1);
        holder.textViewSkill2.setText(tutor.skill2 +"  ");
        holder.textViewFee2.setText(tutor.skillFee2);
        holder.textViewSkill3.setText(tutor.skill3 +"  ");
        holder.textViewFee3.setText(tutor.skillFee3);
        holder.textViewCategory1.setText(tutor.contactType1);
        holder.textViewContact1.setText(tutor.contact1);
        holder.textViewCategory2.setText(tutor.contactType2);
        holder.textViewContact2.setText(tutor.contact2);
    }

    @Override
    public int getItemCount() {
        return tutorsList.size();
    }

    class TutorViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewAvatar;
        TextView textViewName;
        TextView textViewDepartment;
        TextView textViewSkill1;
        TextView textViewSkill2;
        TextView textViewSkill3;
        TextView textViewFee1;
        TextView textViewFee2;
        TextView textViewFee3;
        TextView textViewCategory1;
        TextView textViewContact1;
        TextView textViewCategory2;
        TextView textViewContact2;

        public TutorViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewAvatar = (ImageView) itemView.findViewById(R.id.tutor_avatar);
            textViewName = (TextView) itemView.findViewById(R.id.tutor_name);
            textViewDepartment = (TextView) itemView.findViewById(R.id.tutor_department);
            textViewSkill1 = (TextView) itemView.findViewById(R.id.tutor_skill1);
            textViewSkill2 = (TextView) itemView.findViewById(R.id.tutor_skill2);
            textViewSkill3 = (TextView) itemView.findViewById(R.id.tutor_skill3);
            textViewFee1 = (TextView) itemView.findViewById(R.id.title_fee1);
            textViewFee2 = (TextView) itemView.findViewById(R.id.title_fee2);
            textViewFee3 = (TextView) itemView.findViewById(R.id.title_fee3);
            textViewCategory1 = (TextView) itemView.findViewById(R.id.tutor_contact_category1);
            textViewContact1 = (TextView) itemView.findViewById(R.id.tutor_contact1);
            textViewCategory2 = (TextView) itemView.findViewById(R.id.tutor_contact_category2);
            textViewContact2 = (TextView) itemView.findViewById(R.id.tutor_contact2);

        }
    }
}
