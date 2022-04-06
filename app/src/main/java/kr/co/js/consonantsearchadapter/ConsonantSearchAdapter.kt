package kr.co.js.consonantsearchadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class ConsonantSearchAdapter(
    private val dataSet: Array<String>
) : RecyclerView.Adapter<ConsonantSearchAdapter.SearchViewHolder>(), Filterable {

    private var filteredList = arrayListOf<String>()

    init {
        filteredList.addAll(dataSet)
    }

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_text_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvTitle.text = filteredList[position]
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()

                if (charString.isEmpty()) {
                    filteredList.clear()
                    filteredList.addAll(dataSet)
                } else {
                    val filteringList: ArrayList<String> = ArrayList()

                    val koreanMatcher = KoreanMatcher()

                    for (name in dataSet) {
                        if (koreanMatcher.matchKoreanAndConsonant(name, charString)) filteringList.add(name)
                        else { // 영어인 경우
                            if (name.lowercase(Locale.getDefault()).contains(charString.lowercase(Locale.getDefault()))) {
                                filteringList.add(name)
                            }
                        }
                    }

                    filteredList.clear()
                    filteredList.addAll(filteringList)
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filteredList = results.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }
}