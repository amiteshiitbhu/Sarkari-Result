package com.nirmallabs.sarkariresult.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nirmallabs.sarkariresult.R;
import com.nirmallabs.sarkariresult.ResultDetailsActivity;
import com.nirmallabs.sarkariresult.model.Result;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultItem> {
    private Context context;
    private List<Result> results;

    public ResultAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public ResultItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ResultItem(inflater.inflate(R.layout.item_result, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ResultItem resultItem, final int position) {
        resultItem.tvResultName.setText(results.get(position).getNameOfPost());
        resultItem.shortInformation.setText(results.get(position).getShortInformation());
        resultItem.lastDate.setText(results.get(position).getLastDate());
        resultItem.applyLink.setText(results.get(position).getApplyLink());

        resultItem.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResultDetailsActivity.class);
                intent.putExtra("result", results.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void addValue(List<Result> types) {
        if (types == null) {
            return;
        }
        results.clear();
        results.addAll(types);
        notifyDataSetChanged();

    }

    public class ResultItem extends RecyclerView.ViewHolder {
        private TextView tvResultName;
        private TextView shortInformation;
        private TextView lastDate;
        private TextView applyLink;

        public ResultItem(@NonNull View itemView) {
            super(itemView);
            tvResultName = itemView.findViewById(R.id.tvResultName);
            shortInformation = itemView.findViewById(R.id.shortInformation);
            lastDate = itemView.findViewById(R.id.lastDate);
            applyLink = itemView.findViewById(R.id.applyLink);
        }
    }
}
