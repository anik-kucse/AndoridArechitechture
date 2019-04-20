package anik.cseku.andoridarechitechture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends ListAdapter<Note, RecyclerViewAdapter.MyViewModel> {
    private OnItemClickListener listener;

    public RecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getTitle().equals(t1.getTitle()) &&
                    note.getDescription().equals(t1.getDescription()) &&
                    note.getPriority() == t1.getPriority();
        }
    };

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_elements, viewGroup, false);
        return new MyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel myViewModel, int i) {
        Note note = getItem(i);
        myViewModel.title.setText(note.getTitle());
        myViewModel.description.setText(note.getDescription());
        myViewModel.priority.setText(String.valueOf(note.getPriority()));
    }

    public Note getNoteAt(int position){
        return getItem(position);
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
                        listener.onItemClick(getItem(position));
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
