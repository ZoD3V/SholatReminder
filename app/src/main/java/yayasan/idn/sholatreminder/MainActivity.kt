package yayasan.idn.sholatreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayasan.idn.sholatreminder.data.DataJakarta
import yayasan.idn.sholatreminder.data.DataSurabaya
import yayasan.idn.sholatreminder.network.Config
import yayasan.idn.sholatreminder.network.Model
import java.util.*

class MainActivity : AppCompatActivity() {

    val dataTanggal = arrayListOf<String>()
    val dataSubuh = arrayListOf<String>()
    val dataDzuhur = arrayListOf<String>()
    val dataAshar = arrayListOf<String>()
    val dataMagrib = arrayListOf<String>()
    val dataIsya = arrayListOf<String>()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvSholat)
        val array = arrayOf("Jakarta","Surabaya")
        val spinner: Spinner = findViewById(R.id.plate_spinner)
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,array)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(spinner.selectedItem == "Jakarta"){
                    Clear()
                   showData(DataJakarta().lat, DataJakarta().lng)
                    Toast.makeText(this@MainActivity, "Jakarta", Toast.LENGTH_LONG).show()
                }else if (spinner.selectedItem == "Surabaya"){
                    Clear()
                    showData(DataSurabaya().lat, DataSurabaya().lng)
                    Toast.makeText(this@MainActivity, "Surabya", Toast.LENGTH_LONG).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
        fun showData(lng: String, lat: String, ev: String = "8", mo: String = "2021-10") {

            Config().getService().getModelWaktu(lat, lng, ev, mo)
                .enqueue(object : Callback<Model> {

                    override fun onResponse(
                        call: Call<Model>,
                        response: Response<Model>
                    ) {
                        val panggil1 = response.body()
                        val panggil2 = panggil1?.results?.datetime

                        for (list in panggil2!!.indices) {
                            val waktuSholat = panggil2[list]?.times
                            val tanggal = panggil2[list]?.date

                            dataTanggal.add(tanggal?.gregorian.toString())
                            dataSubuh.add(waktuSholat?.fajr.toString())
                            dataDzuhur.add(waktuSholat?.dhuhr.toString())
                            dataAshar.add(waktuSholat?.asr.toString())
                            dataMagrib.add(waktuSholat?.maghrib.toString())
                            dataIsya.add(waktuSholat?.isha.toString())

                            recyclerView.adapter = Adapter(
                                dataTanggal,
                                dataSubuh,
                                dataDzuhur,
                                dataAshar,
                                dataMagrib,
                                dataIsya
                            )
                            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                            recyclerView.setHasFixedSize(true)
                        }
                    }

                    override fun onFailure(call: Call<Model>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        fun Clear() {
            dataSubuh.clear()
            dataDzuhur.clear()
            dataAshar.clear()
            dataMagrib.clear()
            dataIsya.clear()
            dataTanggal.clear()
        }

    }

