package com.example.challengeone

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.health.connect.datatypes.units.Length
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.marginStart
import androidx.core.widget.doOnTextChanged
import com.example.challengeone.databinding.ActivityHomeBinding
import com.example.challengeone.databinding.ChipLayoutBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.zip.Inflater
import kotlin.math.log

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

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}