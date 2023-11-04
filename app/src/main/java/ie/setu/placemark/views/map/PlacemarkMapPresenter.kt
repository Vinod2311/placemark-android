package ie.setu.placemark.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ie.setu.placemark.main.MainApp

class PlacemarkMapPresenter (val view: PlacemarkMapView) {


    lateinit var app: MainApp

    fun initMap(map: GoogleMap) {
        map.uiSettings.isZoomControlsEnabled = true
        app = view.application as MainApp
        map.setOnMarkerClickListener(view)
        app.placemarks.findAll().forEach{
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options)?.tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doUpdateCard(marker: Marker) {
        val tag = marker.tag as Long
        val placemark = app.placemarks.findById(tag)
        view.updateCard(placemark!!)
    }

    fun doOnBackPressed() {
        view.finish()
    }

}