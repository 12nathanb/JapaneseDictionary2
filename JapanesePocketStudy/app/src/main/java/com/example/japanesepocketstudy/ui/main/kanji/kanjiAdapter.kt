package com.example.japanesepocketstudy.ui.main.kanji

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.japanesepocketstudy.database.Database
import com.example.japanesepocketstudy.databinding.KanjiItemBinding
import com.example.japanesepocketstudy.entities.KanjiDictEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class kanjiAdapter(val items: List<KanjiDictEntity>) :
    RecyclerView.Adapter<kanjiAdapter.ViewHolder>() {
    private lateinit var binding: KanjiItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kanjiAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = KanjiItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: kanjiAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
    inner class ViewHolder(itemView: KanjiItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        fun bind(item: KanjiDictEntity) {
            binding.apply {
                kanji.text = item.kanji
                stroke.text = "Stroke Count: " + item.strokeCount.toString()

                if (item.grade != null) {
                    grade.text = "Grade: " + item.grade.toString()
                } else {
                    grade.isVisible = false
                }

                if (item.frequency != null) {
                    frequency.text = "Frequency: " + item.frequency.toString()
                } else {
                    frequency.isVisible = false
                }

                println("meaning" + item.meanings)

                title.text = item.meanings.joinToString(", ")

                onyomi.text = item.onyomi.joinToString("\n")

                kunyomi.text = item.kunyomi.joinToString("\n")

            }
        }
    }
}