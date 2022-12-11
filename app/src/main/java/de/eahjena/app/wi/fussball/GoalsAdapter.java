package de.eahjena.app.wi.fussball;

import static de.eahjena.app.wi.fussball.MainApplication.tableTeamList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {

    // private String[] localDataSet;

    Context context;
    List<Goal> goals;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMatchMinute;
        private final TextView tvGoalGetterName;
        private final TextView tvScoreTeam1;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvMatchMinute = (TextView) view.findViewById(R.id.tv_matchMinute);
            tvGoalGetterName = (TextView) view.findViewById(R.id.tv_goalGetterName);
            tvScoreTeam1 = (TextView) view.findViewById(R.id.tv_scoreTeam1);
        }

        public TextView getTvMatchMinute() {
            return tvMatchMinute;
        }

        public TextView getTvGoalGetterName() {
            return tvGoalGetterName;
        }

        public TextView getTvScoreTeam1() {
            return tvScoreTeam1;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public GoalsAdapter(Context context, List<Goal> goals) {
        this.context = context;
        this.goals = goals;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.goals_row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getTvMatchMinute().setText(goals.get(position).matchMinute + ". min");
        viewHolder.getTvGoalGetterName().setText(goals.get(position).goalGetterName);
        viewHolder.getTvScoreTeam1().setText(goals.get(position).scoreTeam1+":"+goals.get(position).scoreTeam2);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return goals.size();
    }
}
