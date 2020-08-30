package com.example.note;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class adapter extends BaseAdapter {

    private List<note> notes;
    private Context context;
    public static  String Id;
    public adapter(List<note> list, Context cont) {
        this.notes = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.notes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.notes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.list_view, null);

            holder.subject = (TextView) convertView.findViewById(R.id.noteSubject);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final note noteboject = notes.get(position);
        holder.subject.setText(noteboject.getSubject());
       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Details.class);
                Id=notes.get(position).getSubject();
                Log.d("ID", "Id->" + Id);
                intent.putExtra("message", Id);
                v.getContext().startActivity(intent);

            }
        });


        return convertView;

    }

    private static class ViewHolder {
        TextView subject;
    }

}