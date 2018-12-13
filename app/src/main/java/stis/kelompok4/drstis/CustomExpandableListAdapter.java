package stis.kelompok4.drstis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listJudul;
    private HashMap<String, Reservasi> expandableListDetail;

    public CustomExpandableListAdapter(Context context,
                                       List<String> listJudul,
                                       HashMap<String, Reservasi> expandableListDetail) {
        this.context = context;
        this.listJudul = listJudul;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public int getGroupCount() {
        return listJudul.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listJudul.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetail.get(this.listJudul.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String nomor = (String) getGroup(groupPosition);
        Reservasi reservasi = (Reservasi) getChild(groupPosition,0);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView tanggalTextView = convertView.findViewById(R.id.tanggal_text_view);
        TextView statusSelesai = convertView.findViewById(R.id.status_selesai);

        tanggalTextView.setText(reservasi.getTanggal());
        statusSelesai.setText(reservasi.getStatusSelesai());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Reservasi reservasi = (Reservasi) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView statusDisetujui = convertView.findViewById(R.id.status_disetujui);
        TextView jam = convertView.findViewById(R.id.jam_input);
        TextView keluhan = convertView.findViewById(R.id.keluhan_input);
        TextView dokterName = convertView.findViewById(R.id.dokter_name);

        statusDisetujui.setText(reservasi.getStatusDisetujui());
        jam.setText(reservasi.getJam());
        keluhan.setText(reservasi.getKeluhan());
        dokterName.setText(reservasi.getDokterName());

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        convertView.startAnimation(animation);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
