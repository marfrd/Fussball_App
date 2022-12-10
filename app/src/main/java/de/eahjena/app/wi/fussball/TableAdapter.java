package de.eahjena.app.wi.fussball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    // private String[] localDataSet;

    Context context;
    ArrayList<TableTeam> tableTeamList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPlace;
        // private final ImageView ivLogo;
        private final TextView tvTeam;
        private final TextView tvGoals;
        private final TextView tvPoints;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvPlace = (TextView) view.findViewById(R.id.tv_place);
            // ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
            tvTeam = (TextView) view.findViewById(R.id.tv_teams);
            tvGoals = (TextView) view.findViewById(R.id.tv_goals);
            tvPoints = (TextView) view.findViewById(R.id.tv_points);
        }

        public TextView getTvPlace() {
            return tvPlace;
        }

        public TextView getTvTeam() {
            return tvTeam;
        }

        public TextView getTvGoals() {
            return tvGoals;
        }

        public TextView getTvPoints() {
            return tvPoints;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param tableTeamList ArrayList<TableTeam> containing the data to populate views to be used
     * by RecyclerView.
     */
    public TableAdapter(ArrayList<TableTeam> tableTeamList) {
        this.tableTeamList = tableTeamList;
        // this.context = context;
        // this.tableTeamList = tableTeamList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.teamtable_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        // i.	holder.tvPlace.setText(“”+(position+1));
        // ii.	holder.tvTeam.setText(tableTeamList.get(position).teamName);

        viewHolder.getTvPlace().setText("1");
        viewHolder.getTvTeam().setText("2");
        viewHolder.getTvGoals().setText("3");
        viewHolder.getTvPoints().setText("4");

        // viewHolder.getTvPlace().setText(""+(position+1));
        // viewHolder.getTvTeam().setText(tableTeamList.get(position).teamName);
        // viewHolder.getTvGoals().setText(tableTeamList.get(position).goals);
        // viewHolder.getTvPoints().setText(tableTeamList.get(position).points);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tableTeamList.size();
    }
}
