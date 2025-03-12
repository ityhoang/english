package english.com.utils

import english.com.model.Quiz
import english.com.model.Vocabulary
import english.com.model.WordBook

class DataLocal {
    companion object {
        val listWordBook = listOf<WordBook>(
            WordBook(1, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(2, "Từ Văn", 5, Status.COMPLETED, ""),
            WordBook(3, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(4, "Từ Văn", 5, Status.COMPLETED, ""),
            WordBook(5, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(6, "Từ Văn", 5, Status.COMPLETED, ""),
            WordBook(7, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(8, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(9, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(10, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(11, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(12, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(13, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(14, "Từ Văn", 5, Status.PROGRESS, ""),
            WordBook(15, "Từ Văn", 5, Status.PROGRESS, ""),
        )

        val listVocabulary = listOf<Vocabulary>(
            Vocabulary(1, "Easy", 5, listWordBook),
            Vocabulary(2, "Easy", 5, listWordBook),
            Vocabulary(3, "Easy", 5, listWordBook),
            Vocabulary(4, "Easy", 5, listWordBook),
            Vocabulary(5, "Easy", 5, listWordBook),
            Vocabulary(6, "Easy", 5, listWordBook),
            Vocabulary(7, "Easy", 5, listWordBook),
            Vocabulary(8, "Easy", 5, listWordBook),
            Vocabulary(9, "Easy", 5, listWordBook),
            Vocabulary(10, "Easy", 5, listWordBook),
            Vocabulary(11, "Easy", 5, listWordBook),
            Vocabulary(12, "Easy", 5, listWordBook),
            Vocabulary(13, "Easy", 5, listWordBook),
        )

        val listQuiz = listOf<Quiz>(
            Quiz(1, "Easy"),
            Quiz(2, "Easy"),
            Quiz(3, "Easy"),
            Quiz(4, "Easy"),
        )
    }
}