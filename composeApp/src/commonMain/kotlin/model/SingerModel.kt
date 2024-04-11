package model

import const.COUNTRY_CN
import const.COUNTRY_KR
import const.COUNTRY_US
import const.COUNTRY_VN
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
    var yearOfBirth : Int = 0
    var avatar: String = ""
    var favouriteCount = 0
    var country = ""
}

// Dummy data
fun createListDummySinger() : MutableList<SingerModel> {
    val lisSingerModel = mutableListOf<SingerModel>()

    val singerModel = SingerModel()
    singerModel.id = "1"
    singerModel.name = "Hoàng Thùy Linh"
    singerModel.gender = GENDER_FEMALE
    singerModel.yearOfBirth = 1980
    singerModel.avatar = "gs://musicplayer-c39cb.appspot.com/singers/hoang-thuy-linh-xinh-dep-vay-den-768x767-16735946609761489014417.webp"
    singerModel.country = COUNTRY_VN

    val singerModel1 = SingerModel()
    singerModel1.id = "2"
    singerModel1.name = "Hoa Vinh"
    singerModel1.gender = GENDER_MALE
    singerModel1.yearOfBirth = 1990
    singerModel1.avatar = "gs://musicplayer-c39cb.appspot.com/singers/1527044370150_600.jpg"
    singerModel.country = COUNTRY_VN

    val singerModel2 = SingerModel()
    singerModel2.id = "3"
    singerModel2.name = "Vũ Phụng Tiên"
    singerModel2.gender = GENDER_FEMALE
    singerModel2.yearOfBirth = 1999
    singerModel2.avatar = "gs://musicplayer-c39cb.appspot.com/singers/vu-phung-tien-1-1704964190672331744555-151-0-866-1365-crop-170496514998933951472.webp"
    singerModel2.country = COUNTRY_VN

    val singerModel3 = SingerModel()
    singerModel3.id = "4"
    singerModel3.name = "MIN"
    singerModel3.gender = GENDER_FEMALE
    singerModel3.yearOfBirth = 1988
    singerModel3.avatar = "gs://musicplayer-c39cb.appspot.com/singers/min-7-1647921939-52-width640height480.jpg"
    singerModel2.country = COUNTRY_VN

    val singerModel4 = SingerModel()
    singerModel4.id = "5"
    singerModel4.name = "Pháo"
    singerModel4.gender = GENDER_FEMALE
    singerModel4.yearOfBirth = 2003
    singerModel4.avatar = "gs://musicplayer-c39cb.appspot.com/singers/phao-tu-cai-ten-trieu-view-cho-den-nu-chien-binh-ngong-nghenh-tai-king-of-rap-2020-159-5159710.jpg"
    singerModel4.country = COUNTRY_VN

    val singerModel5 = SingerModel()
    singerModel5.id = "6"
    singerModel5.name = "Châu Khải Phong"
    singerModel5.gender = GENDER_MALE
    singerModel5.yearOfBirth = 1986
    singerModel5.avatar = "gs://musicplayer-c39cb.appspot.com/singers/batch-dd346143096-707163824751824-6416013366828335422-n-300.jpg"
    singerModel5.country = COUNTRY_VN

    val singerModel6 = SingerModel()
    singerModel6.id = "7"
    singerModel6.name = "Đức Phúc"
    singerModel6.gender = GENDER_MALE
    singerModel6.yearOfBirth = 1996
    singerModel6.avatar = "gs://musicplayer-c39cb.appspot.com/singers/ntk-0062-660.jpg"
    singerModel6.country = COUNTRY_VN

    val singerModel7 = SingerModel()
    singerModel7.id = "8"
    singerModel7.name = "ERIK"
    singerModel7.gender = GENDER_MALE
    singerModel7.yearOfBirth = 1997
    singerModel7.avatar = "gs://musicplayer-c39cb.appspot.com/singers/352c00e31a53faff535fbc9d58de0657.jpg"
    singerModel7.country = COUNTRY_VN

    lisSingerModel.add(singerModel)
    lisSingerModel.add(singerModel1)
    lisSingerModel.add(singerModel2)
    lisSingerModel.add(singerModel3)
    lisSingerModel.add(singerModel4)
    lisSingerModel.add(singerModel5)
    lisSingerModel.add(singerModel6)
    lisSingerModel.add(singerModel7)

    val singerModel8 = SingerModel()
    singerModel8.id = "9"
    singerModel8.name = "Justin Bieber"
    singerModel8.gender = GENDER_MALE
    singerModel8.yearOfBirth = 1994
    singerModel8.avatar = "gs://musicplayer-c39cb.appspot.com/singers/tieu-su-justin-bieber-3678-167601620270457998666.webp"
    singerModel8.country = COUNTRY_US

    val singerModel9 = SingerModel()
    singerModel9.id = "10"
    singerModel9.name = "Taylor Swift"
    singerModel9.gender = GENDER_FEMALE
    singerModel9.yearOfBirth = 1989
    singerModel9.avatar = "gs://musicplayer-c39cb.appspot.com/singers/Taylor-swift-161023.jpg"
    singerModel9.country = COUNTRY_US

    val singerModel10 = SingerModel()
    singerModel10.id = "11"
    singerModel10.name = "Ariana Grande"
    singerModel10.gender = GENDER_FEMALE
    singerModel10.yearOfBirth = 1993
    singerModel10.avatar = "gs://musicplayer-c39cb.appspot.com/singers/Ariana_Grande_at_the_2020_Grammy_Awards.webp"
    singerModel10.country = COUNTRY_US

    val singerModel13 = SingerModel()
    singerModel13.id = "14"
    singerModel13.name = "Katy Perry"
    singerModel13.gender = GENDER_FEMALE
    singerModel13.yearOfBirth = 1984
    singerModel13.avatar = "gs://musicplayer-c39cb.appspot.com/singers/channels4_profile.jpg"
    singerModel13.country = COUNTRY_US

    lisSingerModel.add(singerModel8)
    lisSingerModel.add(singerModel9)
    lisSingerModel.add(singerModel10)
    lisSingerModel.add(singerModel13)

    val singerModel11 = SingerModel()
    singerModel11.id = "12"
    singerModel11.name = "Se7en"
    singerModel11.gender = GENDER_MALE
    singerModel11.yearOfBirth = 1984
    singerModel11.avatar = "gs://musicplayer-c39cb.appspot.com/singers/20150225000520_0.jpg"
    singerModel11.country = COUNTRY_KR

    val singerModel12 = SingerModel()
    singerModel12.id = "13"
    singerModel12.name = "JENNIE"
    singerModel12.gender = GENDER_FEMALE
    singerModel12.yearOfBirth = 1996
    singerModel12.avatar = "gs://musicplayer-c39cb.appspot.com/singers/blackpink-jennie-1700193660883455457941.webp"
    singerModel12.country = COUNTRY_KR

    val singerModel14 = SingerModel()
    singerModel14.id = "15"
    singerModel14.name = "Lee Hi"
    singerModel14.gender = GENDER_FEMALE
    singerModel14.yearOfBirth = 1996
    singerModel14.avatar = "gs://musicplayer-c39cb.appspot.com/singers/lee-hi-ca-si-e1602767723641.jpeg"
    singerModel14.country = COUNTRY_KR

    val singerModel15 = SingerModel()
    singerModel15.id = "16"
    singerModel15.name = "HyunA"
    singerModel15.gender = GENDER_FEMALE
    singerModel15.yearOfBirth = 1992
    singerModel15.avatar = "gs://musicplayer-c39cb.appspot.com/singers/Hyuna10.jpg"
    singerModel15.country = COUNTRY_KR

    lisSingerModel.add(singerModel11)
    lisSingerModel.add(singerModel12)
    lisSingerModel.add(singerModel14)
    lisSingerModel.add(singerModel15)

    val singerModel16 = SingerModel()
    singerModel16.id = "17"
    singerModel16.name = "Jay Chou"
    singerModel16.gender = GENDER_MALE
    singerModel16.yearOfBirth = 1979
    singerModel16.avatar = "gs://musicplayer-c39cb.appspot.com/singers/1516339159199_600.jpg"
    singerModel16.country = COUNTRY_CN

    val singerModel17 = SingerModel()
    singerModel17.id = "18"
    singerModel17.name = "G.E.M"
    singerModel17.gender = GENDER_FEMALE
    singerModel17.yearOfBirth = 1991
    singerModel17.avatar = "gs://musicplayer-c39cb.appspot.com/singers/1608543270753.jpg"
    singerModel17.country = COUNTRY_CN

    lisSingerModel.add(singerModel16)
    lisSingerModel.add(singerModel17)

    return lisSingerModel
}