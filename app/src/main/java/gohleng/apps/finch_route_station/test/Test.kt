package gohleng.apps.finch_route_station.test

import gohleng.apps.finch_route_station.R

const val BASE_URL = "https://myttc.ca/"

class Test {

    companion object {
        fun getRandomDrawable(): Int {
            val rnd = (1..7).random()
            var raw = R.drawable.photo_train_a
            when (rnd) {
                2 -> raw = R.drawable.photo_train_b
                3 -> raw = R.drawable.photo_train_c
                4 -> raw = R.drawable.photo_train_d
                5 -> raw = R.drawable.photo_train_e
                6 -> raw = R.drawable.photo_train_f
                7 -> raw = R.drawable.photo_train_g
            }

            return raw
        }
    }
}