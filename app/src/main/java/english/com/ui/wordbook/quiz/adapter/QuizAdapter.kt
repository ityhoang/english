package english.com.ui.wordbook.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import english.com.databinding.EnglishItemQuizBinding
import english.com.model.Quiz

class QuizAdapter(private val onClick: (Quiz) -> Unit) :
    ListAdapter<Quiz, QuizAdapter.ViewHolder>(QuizDiffCallback()) {

    inner class ViewHolder(private val binding: EnglishItemQuizBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Quiz) {
            with(binding) {
                tvAnswer.text = item.answer
                itemView.setOnClickListener { onClick.invoke(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnglishItemQuizBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class QuizDiffCallback : DiffUtil.ItemCallback<Quiz>() {
        override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem == newItem
        }
    }
}