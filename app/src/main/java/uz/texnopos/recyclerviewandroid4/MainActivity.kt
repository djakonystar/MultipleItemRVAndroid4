package uz.texnopos.recyclerviewandroid4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import uz.texnopos.recyclerviewandroid4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PersonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMockData()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter.setOnItemClickListener { id, name ->
            Snackbar.make(binding.recyclerView, "Item $id clicked!", Snackbar.LENGTH_INDEFINITE)
                .show()
        }
    }

    private fun setMockData() {
        val data = mutableListOf<Item>()
        repeat(1000) {
            if (it % 8 == 0 && it != 0) {
                data.add(Ad(R.drawable.img))
            } else {
                data.add(Person(it, "Name $it", "Description $it"))
            }
        }
        adapter.models = data.shuffled()
    }
}
