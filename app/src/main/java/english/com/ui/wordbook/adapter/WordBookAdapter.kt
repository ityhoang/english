package english.com.ui.wordbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import english.com.R
import english.com.databinding.EnglishItemWordbookBinding
import english.com.model.Vocabulary
import english.com.utils.Status
import english.com.utils.extensions.setStroke

class WordBookAdapter(private val onClick: (Vocabulary) -> Unit) :
    ListAdapter<Vocabulary, WordBookAdapter.ViewHolder>(
        AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<Vocabulary>() {
            override fun areItemsTheSame(
                oldItem: Vocabulary, newItem: Vocabulary
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Vocabulary, newItem: Vocabulary
            ): Boolean {
                return oldItem == newItem
            }

        }).build()
    ) {
    class ViewHolder(private val binding: EnglishItemWordbookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onClick: (Vocabulary) -> Unit, item: Vocabulary) {

            binding.vocabulary = item
            itemView.setOnClickListener {
                onClick.invoke(item)
            }
            binding.container.background = itemView.setStroke(
                R.drawable.english_bg_item_vb,
                R.dimen.dip_2,
                when (item.status) {
                    Status.COMPLETED -> R.color.english_green
                    else -> R.color.english_yellow
                }
            )
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EnglishItemWordbookBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onClick, getItem(position))
    }
}