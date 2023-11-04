package ie.setu.placemark.views.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import ie.setu.placemark.databinding.ActivityPlacemarkMapsBinding
import ie.setu.placemark.databinding.ContentPlacemarkMapsBinding
import ie.setu.placemark.models.PlacemarkModel

import ie.setu.placemark.views.map.PlacemarkMapPresenter

class PlacemarkMapView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    private lateinit var binding: ActivityPlacemarkMapsBinding
    private lateinit var contentBinding: ContentPlacemarkMapsBinding
    lateinit var map: GoogleMap
    lateinit var presenter: PlacemarkMapPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PlacemarkMapPresenter(this)
        binding = ActivityPlacemarkMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        contentBinding = ContentPlacemarkMapsBinding.bind(binding.root)
        contentBinding.mapView.onCreate(savedInstanceState)

        contentBinding.mapView.getMapAsync {
            map = it
            presenter.initMap(map)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doUpdateCard(marker)
        return false
    }

    override fun onBackPressed() {
        presenter.doOnBackPressed()
        super.onBackPressed()
    }

    fun updateCard(placemark : PlacemarkModel) {
        contentBinding.currentTitle.text = placemark!!.title
        contentBinding.currentDescription.text = placemark!!.description
        Picasso.get().load(placemark.image).into(contentBinding.currentImage)
    }
}