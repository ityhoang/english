package english.com.view

import android.annotation.SuppressLint
import english.com.R
import english.com.base.BaseDialogFragment
import english.com.databinding.EnglishDialogExplainBinding
import english.com.model.WordBook
import english.com.utils.extensions.getArguments
import english.com.utils.extensions.startAnimation

class DetailDialogFragment : BaseDialogFragment<EnglishDialogExplainBinding>() {
    override val layoutId = R.layout.english_dialog_explain
    override var isCancel = false

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        dialog?.setMargin(41, 0, 41, 0)
        val workBook = getArguments<WordBook>()
        val isShow = arguments?.getInt("isShow")
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }
            when (isShow) {
                1 -> {
                    btnAdd.startAnimation(animation1, false)
                    btnRemove.startAnimation(animation2, true)
                }
                2 -> {
                    btnAdd.startAnimation(animation1, true)
                    btnRemove.startAnimation(animation2, true)
                }
                3 -> {
                    btnAdd.startAnimation(animation1, true)
                    btnRemove.startAnimation(animation2, false)
                }
            }
            tvOriginal.text = "${workBook.name} ${workBook.id}"
        }
    }
}