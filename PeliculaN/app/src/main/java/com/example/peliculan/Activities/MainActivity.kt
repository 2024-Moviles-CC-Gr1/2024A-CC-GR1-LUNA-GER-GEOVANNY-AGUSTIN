package com.example.peliculan.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.peliculan.Adapters.SliderAdapters
import com.example.peliculan.Domian.SlidersItems
import com.example.peliculan.R
import com.example.peliculan.R.drawable.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapterBestMovies: RecyclerView.Adapter<*>
    private lateinit var adapterUpComming: RecyclerView.Adapter<*>
    private lateinit var adapterCategory: RecyclerView.Adapter<*>

    private lateinit var recyclerViewBestMovies: RecyclerView
    private lateinit var recyclerviewComming: RecyclerView
    private lateinit var recyclerviewCategory: RecyclerView


    // Para gestionar el progreso de la carga en la interfaz de usuario
    private lateinit var loading1: ProgressBar
    private lateinit var loading2: ProgressBar
    private lateinit var loading3: ProgressBar



    private lateinit var viewPager2: ViewPager2
    private val slideHandler = Handler(Looper.getMainLooper())

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initView()
        banners()
        inicializarRecyclerView()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_mainActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun inicializarRecyclerView(){
        val recyclerView= findViewById<RecyclerView>(R.id.View1)
        val adaptador = adapterBestMovies
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
    fun banners() {
        val sliderItems = mutableListOf<SlidersItems>()
        sliderItems.add(SlidersItems(R.drawable.walk2))
        sliderItems.add(SlidersItems(R.drawable.house))
        sliderItems.add(SlidersItems(R.drawable.army))

        viewPager2.adapter = SliderAdapters(sliderItems, viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren= false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Crea un CompositePageTransformer para transformar las páginas
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })
        viewPager2.setPageTransformer(compositePageTransformer)
        // Establecer la página inicial
        viewPager2.currentItem = 1

        // Registrar el callback para cambiar de página
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                slideHandler.removeCallbacks(sliderRunnable)
                slideHandler.postDelayed(sliderRunnable, 3000) // Cambiar de página cada 3 segundos
            }
        })
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(sliderRunnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        slideHandler.removeCallbacks(sliderRunnable)
    }


    fun initView(){
        viewPager2=findViewById(R.id.viewpagerSlider)
        recyclerViewBestMovies = findViewById(R.id.View1)
        recyclerViewBestMovies.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        recyclerviewComming= findViewById(R.id.View2)
        recyclerviewComming.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        recyclerviewCategory = findViewById(R.id.View3)
        recyclerviewCategory.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))

        loading1 = findViewById(R.id.progressBar1)
        loading2 = findViewById(R.id.progressBar2)
        loading3 = findViewById(R.id.progressBar4)
    }
















    fun recycler(){
        adapterBestMovies = adapterBestMovies
        adapterCategory = adapterCategory
        adapterUpComming = adapterUpComming
    }
}