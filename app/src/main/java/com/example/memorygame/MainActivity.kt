package com.example.memorygame

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listCardOpenClosed = arrayOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
    )

    val cardIndex = arrayOfNulls<Int>(2)
    val imageId = arrayOfNulls<Int>(2)
    var openImage = 0

    var animationDoing = false

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }



        card1.setOnClickListener {
            imageClick(card1, img1, R.drawable.one, 0)
        }

        card2.setOnClickListener {
            imageClick(card2, img2, R.drawable.nine, 1)
        }

        card3.setOnClickListener {
            imageClick(card3, img3, R.drawable.two, 2)
        }

        card4.setOnClickListener {
            imageClick(card4, img4, R.drawable.eight, 3)
        }

        card5.setOnClickListener {
            imageClick(card5, img5, R.drawable.three, 4)
        }

        card6.setOnClickListener {
            imageClick(card6, img6, R.drawable.seven, 5)
        }

        card7.setOnClickListener {
            imageClick(card7, img7, R.drawable.four, 6)
        }

        card8.setOnClickListener {
            imageClick(card8, img8, R.drawable.six, 7)
        }

        card9.setOnClickListener {
            imageClick(card9, img9, R.drawable.five, 8)
        }

        card10.setOnClickListener {
            imageClick(card10, img10, R.drawable.five, 9)
        }

        card11.setOnClickListener {
            imageClick(card11, img11, R.drawable.six, 10)
        }

        card12.setOnClickListener {
            imageClick(card12, img12, R.drawable.four, 11)
        }

        card13.setOnClickListener {
            imageClick(card13, img13, R.drawable.seven, 12)
        }

        card14.setOnClickListener {
            imageClick(card14, img14, R.drawable.three, 13)
        }

        card15.setOnClickListener {
            imageClick(card15, img15, R.drawable.eight, 14)
        }

        card16.setOnClickListener {
            imageClick(card16, img16, R.drawable.two, 15)
        }

        card17.setOnClickListener {
            imageClick(card17, img17, R.drawable.one, 16)
        }

        card18.setOnClickListener {
            imageClick(card18, img18, R.drawable.nine, 17)
        }
    }

    fun imageClick(cardView: CardView, imageView: ImageView, image: Int, index: Int) {
        if (animationDoing == false) {
            if (listCardOpenClosed[index] == false) {
                openCardView(cardView, imageView, image, index)
            } else {
                closeCardView(cardView, imageView, image, index)
            }
        }
    }

    fun openCardView(cardView: CardView, imageView: ImageView, image: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        cardView.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 =
                    AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_anim2)
                cardView.startAnimation(animation2)
                imageView.setImageResource(image)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listCardOpenClosed[index] = true

                        cardIndex[openImage] = index
                        imageId[openImage] = image
                        openImage++

                        if (openImage == 2) {
                            if (imageId[0] == imageId[1]) {

                                count++

                                val animation3 = AnimationUtils.loadAnimation(
                                    this@MainActivity,
                                    R.anim.combination_anim
                                )

                                searchCardView(cardIndex[0]).startAnimation(animation3)
                                searchCardView(cardIndex[1]).startAnimation(animation3)

                                animation3.setAnimationListener(object :
                                    Animation.AnimationListener {

                                    override fun onAnimationStart(animation: Animation?) {

                                    }

                                    override fun onAnimationEnd(animation: Animation?) {
                                        searchCardView(cardIndex[0]).visibility = View.INVISIBLE
                                        openImage--
                                        searchCardView(cardIndex[1]).visibility = View.INVISIBLE
                                        openImage--

                                        if (count == 9) {

                                            var animation4 = AnimationUtils.loadAnimation(
                                                this@MainActivity,
                                                R.anim.txt_anim
                                            )

                                            txtV.startAnimation(animation4)

                                            animation4.setAnimationListener(object :
                                                Animation.AnimationListener {
                                                override fun onAnimationStart(animation: Animation?) {
                                                    txtV.visibility = View.VISIBLE
                                                }

                                                override fun onAnimationEnd(animation: Animation?) {

                                                }

                                                override fun onAnimationRepeat(animation: Animation?) {

                                                }
                                            })
                                        }
                                    }

                                    override fun onAnimationRepeat(animation: Animation?) {

                                    }
                                })

                            } else {
                                closeCardView(
                                    searchCardView(cardIndex[0]),
                                    imageClose(cardIndex[0]!!),
                                    -1,
                                    cardIndex[0]
                                )
                                closeCardView(
                                    searchCardView(cardIndex[1]),
                                    imageClose(cardIndex[1]!!),
                                    -1,
                                    cardIndex[1]
                                )
                            }
                        }

                        animationDoing = false

                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

    }


    fun closeCardView(cardView: CardView, imageView: ImageView, image: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        cardView.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 =
                    AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_anim2)
                cardView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.star2)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

        listCardOpenClosed[index!!] = false
        openImage--
    }


    fun searchCardView(index: Int?): CardView {

        var cardView: CardView? = null

        when (index) {
            0 -> cardView = card1
            1 -> cardView = card2
            2 -> cardView = card3
            3 -> cardView = card4
            4 -> cardView = card5
            5 -> cardView = card6
            6 -> cardView = card7
            7 -> cardView = card8
            8 -> cardView = card9
            9 -> cardView = card10
            10 -> cardView = card11
            11 -> cardView = card12
            12 -> cardView = card13
            13 -> cardView = card14
            14 -> cardView = card15
            15 -> cardView = card16
            16 -> cardView = card17
            17 -> cardView = card18
        }
        return cardView!!
    }

    fun imageClose(index: Int): ImageView {

        var image: ImageView? = null

        when (index) {
            0 -> image = img1
            1 -> image = img2
            2 -> image = img3
            3 -> image = img4
            4 -> image = img5
            5 -> image = img6
            6 -> image = img7
            7 -> image = img8
            8 -> image = img9
            9 -> image = img10
            10 -> image = img11
            11 -> image = img12
            12 -> image = img13
            13 -> image = img14
            14 -> image = img15
            15 -> image = img16
            16 -> image = img17
            17 -> image = img18
        }
        return image!!
    }
}