package stis.kelompok4.drstis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ActivityJadwalAdapter extends RecyclerView.Adapter<ActivityJadwalAdapter.ViewHolder>
{

    private String[] mData;
    private HashMap<String, String> mHash = new HashMap<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ActivityJadwalAdapter(Context context, String[] data)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    ActivityJadwalAdapter(Context context, HashMap<String, String> data)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mHash = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.activity_jadwal_listitem, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.myTextView.setText(mData[position]);
    }

    // total number of cells
    @Override
    public int getItemCount()
    {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView myTextView;

        ViewHolder(View itemView)
        {
            super(itemView);
            myTextView = itemView.findViewById(R.id.jadwal_waktu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id)
    {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener)
    {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener
    {
        void onItemClick(View view, int position);
    }
}