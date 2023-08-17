package english.com.utils

import english.com.model.Vocabulary

class DataLocal {
    companion object {
        val listVocabulary = listOf<Vocabulary>(
            Vocabulary(1, "Từ Văn", 5, Status.PROGRESS, ""),
            Vocabulary(2, "Từ Văn", 5, Status.COMPLETED, ""),
            Vocabulary(3, "Từ Văn", 5, Status.PROGRESS, ""),
            Vocabulary(4, "Từ Văn", 5, Status.COMPLETED, ""),
            Vocabulary(5, "Từ Văn", 5, Status.PROGRESS, ""),
            Vocabulary(6, "Từ Văn", 5, Status.COMPLETED, ""),
            Vocabulary(7, "Từ Văn", 5, Status.PROGRESS, ""),
        )
    }
}