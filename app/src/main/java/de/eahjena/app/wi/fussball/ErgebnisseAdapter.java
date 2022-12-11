package de.eahjena.app.wi.fussball;

import static de.eahjena.app.wi.fussball.MainApplication.getTeamIconByTeamName;
import static de.eahjena.app.wi.fussball.MainApplication.matchList;
import static de.eahjena.app.wi.fussball.MainApplication.tableTeamList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ErgebnisseAdapter extends RecyclerView.Adapter<ErgebnisseAdapter.ViewHolder> {

    // private String[] localDataSet;

    Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTeamName1;
        private final ImageView ivTeamLogo1;
        private final TextView tvFinalResult;
        private final ImageView ivTeamLogo2;
        private final TextView tvTeamName2;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvTeamName1 = (TextView) view.findViewById(R.id.tv_teamName1);
            ivTeamLogo1 = (ImageView) view.findViewById(R.id.iv_teamLogo1);
            tvFinalResult = (TextView) view.findViewById(R.id.tv_finalResult);
            ivTeamLogo2 = (ImageView) view.findViewById(R.id.iv_teamLogo2);
            tvTeamName2 = (TextView) view.findViewById(R.id.tv_teamName2);
        }

        public TextView getTvTeamName1() {
            return tvTeamName1;
        }

        public ImageView getIvTeamLogo1() {
            return ivTeamLogo1;
        }

        public TextView getTvFinalResult() {
            return tvFinalResult;
        }

        public ImageView getIvTeamLogo2() {
            return ivTeamLogo2;
        }

        public TextView getTvTeamName2() {
            return tvTeamName2;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public ErgebnisseAdapter(Context context) {
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ergebnisse_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.itemView.setTag(matchList.get(position).matchId);
        viewHolder.itemView.setOnClickListener(globalClickListener);

        viewHolder.getTvTeamName1().setText(matchList.get(position).team1);
        viewHolder.getIvTeamLogo1().setImageDrawable(getTeamIconByTeamName(matchList.get(position).team1));
        viewHolder.getTvFinalResult().setText(matchList.get(position).finalResult);
        viewHolder.getIvTeamLogo2().setImageDrawable(getTeamIconByTeamName(matchList.get(position).team2));
        viewHolder.getTvTeamName2().setText(matchList.get(position).team2);

        // viewHolder.getIvLogo().setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.a_1_fc_nuernberg));
        // viewHolder.getIvLogo().setImageResource(context.getResources().getIdentifier(tableTeamList.get(position).teamIconUrl, "drawable", context.getPackageName()));
        // viewHolder.getIvLogo().setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.spvgg_greuther_fuerth));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return matchList.size();
    }

    private View.OnClickListener globalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int matchId = (int) v.getTag();
            Intent intent= new Intent(context, ActivitySpiel.class);
            intent.putExtra("matchId",matchId);
            context.startActivity(intent);
        }
    };
}
