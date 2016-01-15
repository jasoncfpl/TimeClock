package com.excelee.timeclock.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.excelee.timeclock.bean.Clock;
import com.excelee.timeclock.ui.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * clocks adapter
 * Created by lijia on 16/1/3.
 */
public class ClockAdapter extends RecyclerView.Adapter<ClockAdapter.ClockHolder> {

    private List<Clock> clocks;
    private Context context;

    public ClockAdapter(Context context,List<Clock> clocks) {
        if (clocks == null)
            throw new IllegalArgumentException("clocks must not be null");
        this.clocks = clocks;
        this.context = context;
    }

    @Override
    public ClockHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_clocklist,parent,false);
        ClockHolder clockHolder;
        clockHolder = new ClockHolder(view);

        return clockHolder;
    }

    @Override
    public void onBindViewHolder(ClockHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String clockDate = sdf.format(clocks.get(position).getClockTime());
        holder.tv.setText(clockDate);
    }

    @Override
    public int getItemCount() {
        return clocks.size();
    }

    /**
     * ViewHolder
     */
    class ClockHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public ClockHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.adapter_clocklist_tv);
        }
    }
}
