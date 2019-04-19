package anik.cseku.andoridarechitechture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewModel> {
    private List<Note> allNotes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_elements, viewGroup, false);
        return new MyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel myViewModel, int i) {
        Note note = allNotes.get(i);
        myViewModel.title.setText(note.getTitle());
        myViewModel.description.setText(note.getDescription());
        myViewModel.priority.setText(String.valueOf(note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void setNotes(List<Note> note){
        this.allNotes = note;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return allNotes.get(position);
    }

    public class MyViewModel extends RecyclerView.ViewHolder {
        TextView title;
        TextView priority;
        TextView description;

        public MyViewModel(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            priority = itemView.findViewById(R.id.text_view_priority);
            description = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(allNotes.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
