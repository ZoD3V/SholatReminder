package yayasan.idn.sholatreminder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataTanggal: ArrayList<String>,
              private val dataSubuh: ArrayList<String>,
              private val dataDzuhur: ArrayList<String>,
              private val dataAshar: ArrayList<String>,
              private val dataMagrib: ArrayList<String>,
              private val dataIsya: ArrayList<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Waktu: TextView = view.findViewById(R.id.waktu)
        val Subuh: TextView = view.findViewById(R.id.subuh)
        val Dzuhur: TextView = view.findViewById(R.id.dzuhur)
        val Ashar: TextView = view.findViewById(R.id.ashar)
        val Maghrib: TextView = view.findViewById(R.id.magrib)
        val Isya: TextView = view.findViewById(R.id.isya)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_model, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.Waktu.text = dataTanggal[position]
        viewHolder.Subuh.text = dataSubuh[position]
        viewHolder.Dzuhur.text = dataDzuhur[position]
        viewHolder.Ashar.text = dataAshar[position]
        viewHolder.Maghrib.text = dataMagrib[position]
        viewHolder.Isya.text = dataIsya[position]
    }

    override fun getItemCount() = dataTanggal.size

}