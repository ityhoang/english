package english.com.ui.wordbook.vocabulary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import english.com.databinding.EnglishItemSubBinding
import english.com.model.Vocabulary
import english.com.ui.wordbook.adapter.WordBookAdapter
import english.com.utils.Constants
import english.com.utils.SpaceItemDecoration
import english.com.utils.extensions.baseGridLayoutManager
import english.com.view.DetailDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyAdapter(fm: FragmentManager, private val onClick: (Vocabulary) -> Unit) :
    ListAdapter<Vocabulary, VocabularyAdapter.ViewHolder>(VocabularyDiffCallback()) {
    private val wordBookAdapter by lazy {
        WordBookAdapter { data, isShow ->
            val dialogFragment = DetailDialogFragment()
            dialogFragment.sendData(data, isShow)
            dialogFragment.showAllowingStateLoss(fm)
        }
    }

    inner class ViewHolder(private val binding: EnglishItemSubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vocabulary) {
            with(binding) {
                tvTitle.text = item.title
                tvCountPoint.text = item.vocabularies.size.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    wordBookAdapter.submitList(item.vocabularies)
                }
                itemView.setOnClickListener { onClick.invoke(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnglishItemSubBinding.inflate(inflater, parent, false)
        setupRecyclerView(binding.rcVocabulary, binding.root.context)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, context: Context) {
        recyclerView.apply {
            layoutManager = baseGridLayoutManager(context, 2)
            adapter = wordBookAdapter
            itemAnimator = null
            addItemDecoration(
                SpaceItemDecoration(
                    insetsLeft = Constants.spaceItem,
                    insetsRight = Constants.spaceItem,
                    insetsBottom = Constants.spaceItem * 2,
                    insetsLast = Constants.spaceItem,
                    insetsFirst = Constants.spaceItem
                )
            )
        }
    }

    private class VocabularyDiffCallback : DiffUtil.ItemCallback<Vocabulary>() {
        override fun areItemsTheSame(oldItem: Vocabulary, newItem: Vocabulary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vocabulary, newItem: Vocabulary): Boolean {
            return oldItem == newItem
        }
    }
}