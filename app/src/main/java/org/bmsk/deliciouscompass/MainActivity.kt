package org.bmsk.deliciouscompass

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import org.bmsk.deliciouscompass.data.model.SearchResult
import org.bmsk.deliciouscompass.data.repository.SearchRepository
import org.bmsk.deliciouscompass.databinding.ActivityMainBinding
import org.bmsk.deliciouscompass.ui.adapter.RestaurantListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var isMapInit = false

    private var restaurantListAdapter = RestaurantListAdapter {
        // 카메라 움직임
        moveCamera(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapView.onCreate(savedInstanceState)

        binding.mapView.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.bottomSheetLayout.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantListAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query?.isNotEmpty() == true) {
                    SearchRepository.getGoodRestaurant(query)
                        .enqueue(object : Callback<SearchResult> {
                            override fun onResponse(
                                call: Call<SearchResult>,
                                response: Response<SearchResult>
                            ) {
                                val searchItemList = response.body()?.items.orEmpty()
                                if (searchItemList.isEmpty()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        getString(R.string.search_result_is_empty),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return
                                } else if (isMapInit.not()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        getString(R.string.error_map_init),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return
                                }

                                val markers = searchItemList.map {
                                    Marker(
                                        Tm128(
                                            it.mapx.toDouble(),
                                            it.mapy.toDouble()
                                        ).toLatLng()
                                    ).apply {
                                        captionText = it.title
                                        map = naverMap
                                    }
                                }

                                restaurantListAdapter.setData(searchItemList)

                                moveCamera(markers.first().position)
                            }

                            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                                t.printStackTrace()
                            }

                        })
                    false
                } else
                    true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        ) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    private fun moveCamera(position: LatLng) {
        if (isMapInit.not()) return

        val cameraUpdate = CameraUpdate.scrollTo(position)
            .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    // 비동기적으로 초기화되기 때문에, isMapInit이 true로 되어있는 경우에 사용합니다.
    override fun onMapReady(mapObject: NaverMap) {
        naverMap = mapObject
        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        isMapInit = true

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5666102, 126.9783881))
            .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}