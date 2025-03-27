package com.mkarshnas6.karenstudio.kidscanvas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivityColoringBinding
import com.mkarshnas6.karenstudio.kidscanvas.databinding.ActivitySelecteLearningBinding

class SelecteLearningActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySelecteLearningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelecteLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ......... change color status bar and navigation mob ..............................
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_green)


        // Binding initialization
        binding = ActivitySelecteLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Creating a list of ImageItems (with Persian and English names)
        val imageList = listOf(
            ImageItem("مرد عنکبوتی 1", "spiderman1"),
            ImageItem("مرد عنکبوتی 2", "spiderman2"),
            ImageItem("مرد عنکبوتی 3", "spiderman3"),
            ImageItem("مرد عنکبوتی 4", "spiderman4"),
            ImageItem("مرد عنکبوتی 5", "spiderman5"),
            ImageItem("مرد عنکبوتی 6", "spiderman6"),
            ImageItem("بت من 1", "batman3"),
            ImageItem("بت من 2", "batman1"),
            ImageItem("بت من 3", "batman5"),
            ImageItem("بت من 4", "batman4"),
            ImageItem("بت من 5", "batman2"),
            ImageItem("کاپیتان امریکا 1", "captainamerica1"),
            ImageItem("کاپیتان امریکا 2", "captainamerica2"),
            ImageItem("کاپتان امریکا 3", "captainamerica3"),
            ImageItem("ددپول 1", "deadpool1"),
            ImageItem("ددپول 2", "deadpool2"),
            ImageItem("ددپول 3", "deadpool3"),
            ImageItem("ددپول 4", "deadpool4"),
            ImageItem("دکتر استرنج 1", "drstrang1"),
            ImageItem("دکتر استرنج 2", "drstrang2"),
            ImageItem("دکتر استرنج 3", "drstrang3"),
            ImageItem("دکتر استرنج 4", "drstrang4"),
            ImageItem("لاک پشت های نینجا 1", "ninjaturtles1"),
            ImageItem("لاک پشت های نینجا 2", "ninjaturtles2"),
            ImageItem("لاک پشت های نینجا 3", "ninjaturtles3"),
            ImageItem("لاک پشت های نینجا 4", "ninjaturtles4"),
            ImageItem("لاک پشت های نینجا 5", "ninjaturtles5"),
            ImageItem("سوپر من 1", "superman1"),
            ImageItem("سوپر من 2", "superman2"),
            ImageItem("سوپر من 3", "superman3"),
            ImageItem("سوپر من 4", "superman4"),
            ImageItem("سوپر من 5", "superman5"),
            ImageItem("ونوم 1", "venom1"),
            ImageItem("ونوم 2", "venom2"),
            ImageItem("ثور 1", "thor1"),
            ImageItem("ثور 2", "thor2"),
            ImageItem("ثور 3", "thor3"),
            ImageItem("داستان اسباب بازی ها 1", "toystore1"),
            ImageItem("داستان اسباب بازی ها 2", "toystore2"),
            ImageItem("داستان اسباب بازی ها 3", "toystore3"),
            ImageItem("داستان اسباب بازی ها 4", "toystore4"),
            ImageItem("داستان اسباب بازی ها 5", "toystore5"),
            ImageItem("داستان اسباب بازی ها 6", "toystore6"),
            ImageItem("داستان اسباب بازی ها 7", "toystore7"),
            ImageItem("داستان اسباب بازی ها 8", "toystore8"),
            ImageItem("باب اسفنجی 1", "spongebob1"),
            ImageItem("باب اسفنجی 2", "spongebob2"),
            ImageItem("باب اسفنجی 3", "spongebob3"),
            ImageItem("فروزن 1", "frozen1"),
            ImageItem("فروزن 2", "frozen2"),
            ImageItem("فروزن 3", "frozen3"),
            ImageItem("فروزن 4", "frozen4"),
            ImageItem("گیسو کمند 1", "tangled1"),
            ImageItem("گیسو کمند 2", "tangled2"),
            ImageItem("مینیون 1", "minion1"),
            ImageItem("مینیون 2", "minion2"),
            ImageItem("دختر کفشدوزکی 1", "ladybug1"),
            ImageItem("دختر کفشدوزکی 2", "ladybug2"),
            ImageItem("پری دریایی 1", "mermaid1"),
            ImageItem("پری دریایی 2", "mermaid2"),
            ImageItem("مونا 1", "mona1"),
            ImageItem("مونا 2", "mona2"),
            ImageItem("کارخانه هیولا ها 1", "monsters1"),
            ImageItem("کارخانه هیولا ها 2", "monsters2"),
            ImageItem("کارخانه هیولا ها 3", "monsters3"),
            ImageItem("کیتی 1", "kitty1"),
            ImageItem("کیتی 2", "kitty2"),
            ImageItem("کیتی 3", "kitty3"),
            ImageItem("کیتی 4", "kitty4"),
            ImageItem("سیندرلا 1", "cinderella1"),
            ImageItem("سیندرلا 2", "cinderella2"),
            ImageItem("سیندرلا 3", "cinderella3"),
            ImageItem("سیندرلا 4", "cinderella4"),
            ImageItem("گروت 1", "groot1"),
            ImageItem("ونلوپ 1", "vanellope1"),
            ImageItem("ایرون من 1", "ironman1"),
            ImageItem("ایرون من 2", "ironman2"),
            ImageItem(" سونیک 1", "sonic1"),
            ImageItem("سونیک 2", "sonic2"),
            ImageItem("سونیک 3", "sonic3"),
            ImageItem("میکی موس 1", "mickeymouse1"),
            ImageItem("میکی موس 2", "mickeymouse2"),
            ImageItem("سوپر ماریو 1", "supermario1"),
            ImageItem("اساسین کرید 1", "assassinscreed1"),
            ImageItem("ریک اند مورتی 1", "rickandmorty1"),
            ImageItem("اسنوپی 1", "snoopy1"),
            ImageItem("اسنوپی 2", "snoopy2"),
            ImageItem("اسنوپی 3", "snoopy3"),
            ImageItem("پاندا 1", "panda1"),
            ImageItem("پاندا 2", "panda2"),
        )

        // Set the LayoutManager for the RecyclerView
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // Creating the adapter and setting onClick action
        val adapter = ColoringAdapter(imageList) { item ->
            val intent = Intent(this, LearningActivity::class.java)
            intent.putExtra("image_name", item.englishName)
            startActivity(intent)
        }

        // Setting the adapter to the RecyclerView
        binding.recyclerview.adapter = adapter

    }
}