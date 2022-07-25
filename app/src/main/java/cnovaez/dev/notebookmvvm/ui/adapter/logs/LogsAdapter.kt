package cnovaez.dev.notebookmvvm.ui.adapter.logs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.domain.model.Log

class LogsAdapter(private val logs: List<Log>, private val onClickListener: (Log) -> Unit) :
    RecyclerView.Adapter<LogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LogsViewHolder(layoutInflater.inflate(R.layout.log_card, parent, false))
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        val item = logs[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = logs.size
}