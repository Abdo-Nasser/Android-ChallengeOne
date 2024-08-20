package com.example.challengeone

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeone.Adapters.HomeRecyclerAdapter
import com.example.challengeone.UIModels.HomeSectionUIModel
import com.example.challengeone.UIModels.PlaceUIModel
import com.example.challengeone.databinding.ActivityHomeBinding
import com.example.challengeone.databinding.ChipLayoutBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    val items = listOf<String>(
        "Location",
        "Hotels",
        "Food",
        "Adventure",
        "Clubs"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChips()
        setupSearchEditView()
        setupRecyclerView()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                val currentView = currentFocus
                if (currentView is EditText) {
                    val outRect = Rect()
                    currentView.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        currentView.clearFocus()
                        hideKeyboard(currentView)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setupSearchEditView() {
        binding.searchEditText.filters = arrayOf(InputFilter.LengthFilter(5))

        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            Log.d("Nasser", "$before")
        }

        binding.searchEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.searchEditText.clearFocus()
                hideKeyboard(binding.searchEditText)
                true
            } else {
                false
            }
        }
    }

    private fun setupChips() {
        val inflater = LayoutInflater.from(this)
        var isFirstItem = true
        items.forEach {
            var chip: ChipLayoutBinding = ChipLayoutBinding.inflate(inflater)
            chip.root.text = it
            chip.root.isChecked = isFirstItem
            if (isFirstItem) {
                chip.root.setTypeface(null, Typeface.BOLD)
            } else {
                chip.root.setTypeface(null, Typeface.NORMAL)
            }

            binding.chipGroup.addView(chip.root)

            chip.root.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked) {
                    chip.root.setTypeface(null, Typeface.BOLD)
                } else {
                    chip.root.setTypeface(null, Typeface.NORMAL)
                }
                val message = chip.root.text.toString()
                Log.d("Nasser", "$message")
            }

            isFirstItem = false
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.mainRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HomeRecyclerAdapter(prepareModelsData())
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun prepareModelsData(): List<HomeSectionUIModel> {
        return listOf(
            preparePopularSectionModelsData(),
            prepareRecommendationSectionModelsData()
        )
    }

    private fun preparePopularSectionModelsData(): HomeSectionUIModel {
        val items = listOf(
            PlaceUIModel(
                id = "1-1",
                title = "Alley Palace",
                subtitle = "welcome to Alley Palace",
                imageULR = "https://img.veenaworld.com/wp-content/uploads/2023/09/Famous-Places-in-the-World-Checking-off-the-Ultimate-Bucket-List.jpg",
                rate = 4.8,
                isFavourite = true,
                isHotDeal = false,
                nCount = 3,
                dCount = 4
            ),
            PlaceUIModel(
                id = "1-2",
                title = "Coeurdes Alpes",
                subtitle = "welcome to Coeurdes Alpes",
                imageULR = "https://www.tourmyindia.com/blog//wp-content/uploads/2020/11/Taj-Mahal-Agra-feature.jpg",
                rate = 5.0,
                isFavourite = false,
                isHotDeal = true,
                nCount = 3,
                dCount = 4
            )
        )
        return HomeSectionUIModel(
                id = "1",
                title = "Popular",
                isCardView = true,
                hasSeeAll = true,
                items = items
            )
    }

    private fun prepareRecommendationSectionModelsData(): HomeSectionUIModel {
        val items = listOf(
            PlaceUIModel(
                id = "2-1",
                title = "Explore Aspen",
                subtitle = "welcome to Explore Aspen",
                imageULR = "https://www.planetware.com/photos-large/EGY/egypt-cairo-pyramids-of-giza.jpg",
                rate = 4.8,
                isFavourite = true,
                isHotDeal = false,
                nCount = 3,
                dCount = 4
            ),
            PlaceUIModel(
                id = "2-2",
                title = "Luxurious Aspen",
                subtitle = "welcome to Luxurious Aspen",
                imageULR = "https://static.wanderon.in/wp-content/uploads/2023/10/gondola-ride-in-autumn-in-kashmir-2023-10-18t174214.790-min.png",
                rate = 5.0,
                isFavourite = false,
                isHotDeal = true,
                nCount = 3,
                dCount = 4
            )
        )
        return HomeSectionUIModel(
            id = "1",
            title = "Recommendation",
            isCardView = false,
            hasSeeAll = false,
            items = items
        )
    }
}