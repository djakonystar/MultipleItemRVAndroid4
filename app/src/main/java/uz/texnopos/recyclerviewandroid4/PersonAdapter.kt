package uz.texnopos.recyclerviewandroid4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.texnopos.recyclerviewandroid4.databinding.ItemAdBinding
import uz.texnopos.recyclerviewandroid4.databinding.ItemPersonBinding

class PersonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val PERSON = 0
        private const val AD = 1
    }

    var models = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PersonViewHolder(private val binding: ItemPersonBinding) :
        ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.tvName.text = person.name
            binding.tvDescription.text = person.description

            binding.root.setOnClickListener {
                this@PersonAdapter.onItemClick.invoke(person.id, person.name)
            }
        }
    }

    inner class AdViewHolder(private val binding: ItemAdBinding) : ViewHolder(binding.root) {
        fun bind(ad: Ad) {
            binding.image.setImageResource(ad.image)
        }
    }

    private var onItemClick: (id: Int, name: String) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (id: Int, name: String) -> Unit) {
        this.onItemClick = onItemClick
    }

    override fun getItemCount(): Int = models.size

    override fun getItemViewType(position: Int): Int {
        return if (models[position] is Person) PERSON else AD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            PERSON -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
                val binding = ItemPersonBinding.bind(view)
                PersonViewHolder(binding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ad, parent, false)
                val binding = ItemAdBinding.bind(view)
                AdViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            PERSON -> {
                (holder as PersonViewHolder).bind(models[position] as Person)
            }
            AD -> {
                (holder as AdViewHolder).bind(models[position] as Ad)
            }
        }
    }
}
