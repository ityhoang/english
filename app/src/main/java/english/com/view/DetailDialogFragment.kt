package english.com.view

import english.com.R
import english.com.base.BaseDialogFragment
import english.com.databinding.EnglishDialogExplainBinding

class DetailDialogFragment : BaseDialogFragment<EnglishDialogExplainBinding>() {
    override val layoutId = R.layout.english_dialog_explain
    override fun initView() {
        super.initView()
        dialog?.setMargin(41, 0, 41, 0)
    }
}