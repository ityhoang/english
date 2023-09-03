package english.com.ui.wordbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import english.com.R
import english.com.databinding.EnglishItemWordbookBinding
import english.com.model.WordBook
import english.com.utils.Status
import english.com.utils.extensions.setStroke

class WordBookAdapter(private val onClick: (WordBook, Int) -> Unit) :
    ListAdapter<WordBook, WordBookAdapter.ViewHolder>(
        AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<WordBook>() {
            override fun areItemsTheSame(
                oldItem: WordBook, newItem: WordBook
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WordBook, newItem: WordBook
            ): Boolean {
                return oldItem == newItem
            }

        }).build()
    ) {
    inner class ViewHolder(private val binding: EnglishItemWordbookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WordBook, position: Int) {
            with(binding) {
                vocabulary = item
                itemView.setOnClickListener {
                    onClick.invoke(
                        item,
                        when (position) {
                            0 -> 1
                            itemCount - 1 -> 3
                            else -> 2
                        }
                    )
                }
                container.background = itemView.setStroke(
                    R.drawable.english_bg_item_vb,
                    R.dimen.dip_2,
                    when (item.status) {
                        Status.COMPLETED -> R.color.english_green
                        else -> R.color.english_yellow
                    }
                )
            }
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
        holder.bind(getItem(position), position)
    }
}