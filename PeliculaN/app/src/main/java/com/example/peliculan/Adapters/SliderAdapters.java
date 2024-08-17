package com.example.peliculan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import com.example.peliculan.Domian.SlidersItems;
import com.example.peliculan.R;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {
    private List<SlidersItems> slidersItems;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapters(List<SlidersItems> slidersItems, ViewPager2 viewPager2) {
        this.slidersItems = slidersItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapters.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapters.SliderViewHolder holder, int position) {
        holder.setImage(slidersItems.get(position));
        if(position==slidersItems.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return slidersItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlide);
        }
        void setImage(SlidersItems slidersItems) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(60));

            Glide.with(context)
                    .load(slidersItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            slidersItems.addAll(slidersItems);
            notifyDataSetChanged();
        }
    };


}
