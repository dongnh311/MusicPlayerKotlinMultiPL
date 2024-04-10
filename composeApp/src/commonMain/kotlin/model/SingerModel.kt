package model

import const.GENDER_FEMALE
import const.GENDER_MALE
import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 9/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class SingerModel {
    var id: String = ""
    var name: String = ""
    var gender: Int = 0
    var yearOfBrith : Int = 0
    var avatar: String = ""
    var favouriteCount = 0
}

// Dummy data
fun createListDummySinger() : MutableList<SingerModel> {
    val lisSingerModel = mutableListOf<SingerModel>()

    val singerModel = SingerModel()
    singerModel.id = "12312"
    singerModel.name = "Hoàng Thùy Linh"
    singerModel.gender = GENDER_FEMALE
    singerModel.yearOfBrith = 1980
    singerModel.avatar = "gs://musicplayer-c39cb.appspot.com/singers/hoang-thuy-linh-xinh-dep-vay-den-768x767-16735946609761489014417.webp"

    val singerModel1 = SingerModel()
    singerModel1.id = "345345"
    singerModel1.name = "Hoa Vinh"
    singerModel1.gender = GENDER_MALE
    singerModel1.yearOfBrith = 1990
    singerModel1.avatar = "gs://musicplayer-c39cb.appspot.com/singers/1527044370150_600.jpg"

    val singerModel2 = SingerModel()
    singerModel2.id = "3453123"
    singerModel2.name = "Vũ Phụng Tiên"
    singerModel2.gender = GENDER_FEMALE
    singerModel2.yearOfBrith = 1999
    singerModel2.avatar = "gs://musicplayer-c39cb.appspot.com/singers/vu-phung-tien-1-1704964190672331744555-151-0-866-1365-crop-170496514998933951472.webp"

    lisSingerModel.add(singerModel)
    lisSingerModel.add(singerModel1)
    lisSingerModel.add(singerModel2)

    return lisSingerModel
}