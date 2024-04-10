package model

import const.COUNTRY_CN
import const.COUNTRY_KR
import const.COUNTRY_US
import const.COUNTRY_VN
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 11/03/2024.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */
@Serializable
class MusicModel {
    var id = ""
    var name = ""
    var authorId = ""
    var duration: Double = 0.0
    var url = ""
    var imageUrl = ""
    var singerId = ""
    var lyrics: String = ""
    var countPlayed = 0
    var topicId = arrayListOf<String>()
    var country = ""
    var uploadDate: Long = 0

    @Transient
    var singerModel: SingerModel? = SingerModel()

    @Transient
    var authorModel: AuthorModel? = AuthorModel()
}

// Dummy data for new music
fun createListDummyNewMusic() : MutableList<MusicNewModel> {
    val listMusics = mutableListOf<MusicNewModel>()

    listMusics.add(MusicNewModel("1"))
    listMusics.add(MusicNewModel("2"))
    listMusics.add(MusicNewModel("3"))
    listMusics.add(MusicNewModel("4"))
    listMusics.add(MusicNewModel("5"))
    return listMusics
}

// Dummy data
fun createListDummyMusics() : MutableList<MusicModel> {
    val listMusics = mutableListOf<MusicModel>()

    val musicModel = MusicModel()
    musicModel.id = "1"
    musicModel.name = "Để mị nói cho mà nghe"
    musicModel.authorId = ""
    musicModel.duration = 259.0
    musicModel.url = "gs://musicplayer-c39cb.appspot.com/musics/DeMiNoiChoMaNgheCaoRemix-HoangThuyLinh-6011246.mp3"
    musicModel.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/Deminoichomanghehtl.png"
    musicModel.lyrics = "Để Mị nói cho mà nghe\n" +
            "Tâm hồn này chẳng để lặng lẽ\n" +
            "Thương cha xót mẹ\n" +
            "Thôi thì mặc phận đời mình chơi vơi\n" +
            "\n" +
            "Để Mị nói cho mà nghe\n" +
            "Hết năm nay Mị vẫn còn trẻ\n" +
            "Xuân đương tới rồi\n" +
            "Nên Mị cũng muốn đi chơi\n" +
            "\n" +
            "[Chorus]\n" +
            "Này là mình đi theo giấc mơ sớm mai gọi mời\n" +
            "Nơi vòng tay ấm êm chẳng rời\n" +
            "Hoa ban trắng nở đầy con bản nọ\n" +
            "Hương sắc còn chưa phai\n" +
            "\n" +
            "Đời mình đâu có mấy vui cớ sao lại buồn\n" +
            "Biết ngày mai trắng đen hay tròn vuông\n" +
            "Em không bắt quả pao rơi rồi\n" +
            "Tiếc không một đời đơn côi\n" +
            "\n" +
            "[Rap]\n" +
            "Mị còn trẻ Mị còn muốn đi chơi\n" +
            "Thanh xuân sao lại phải nghỉ ngơi\n" +
            "Hoa ban trắng trên nương chớm nở\n" +
            "Đẹp như tâm hồn em còn ngây thơ\n" +
            "\n" +
            "Em làm gì đã có người yêu\n" +
            "Em còn đang sợ ế đây này\n" +
            "Vậy tại sao quả pao không năm trên tay\n" +
            "Để bao trai làng chìm đắm trong mê say\n" +
            "\n" +
            "Mùa xuân này, Mị muốn xúng xính trong váy hoa\n" +
            "Không đi làm sao biết ngoài kia một mai là sương hay nắng toả\n" +
            "Cơ hội này Mị sẽ nắm lấy, Mị chẳng cần một ai dắt tay\n" +
            "Và hết hôm nay, Mị sẽ chuồn khỏi nơi đây!\n" +
            "[Verse]\n" +
            "Để Mị nói cho mà nghe\n" +
            "Tâm hồn này chẳng để lặng lẽ\n" +
            "Thương cha xót mẹ\n" +
            "Thôi thì mặc phận đời mình chơi vơi\n" +
            "\n" +
            "Để Mị nói cho mà nghe\n" +
            "Hết năm nay Mị vẫn còn trẻ\n" +
            "Xuân đương tới rồi\n" +
            "Nên Mị cũng muốn đi chơi\n" +
            "\n" +
            "[Chorus]\n" +
            "Này là mình đi theo giấc mơ sớm mai gọi mời\n" +
            "Nơi vòng tay ấm êm chẳng rời\n" +
            "Hoa ban trắng nở đầy con bản nọ\n" +
            "Hương sắc còn chưa phai\n" +
            "\n" +
            "Đời mình đâu có mấy vui cớ sao lại buồn\n" +
            "Biết ngày mai trắng đen hay tròn vuông\n" +
            "Em không bắt quả pao rơi rồi\n" +
            "Tiếc không một đời đơn côi"
    musicModel.singerId = "1"
    musicModel.country = COUNTRY_VN
    musicModel.topicId = arrayListOf("1", "2", "3")
    musicModel.uploadDate = 1712719256151
    listMusics.add(musicModel)

    val musicModel1 = MusicModel()
    musicModel1.id = "2"
    musicModel1.name = "Ngắm Hoa Lệ Rơi"
    musicModel1.authorId = ""
    musicModel1.duration = 303.0
    musicModel1.url = "gs://musicplayer-c39cb.appspot.com/musics/NgamHoaLeRoiCover-HoaVinh-5404345.mp3"
    musicModel1.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/23dc8f693f107e28fb2ff15ec0f8b111.jpg"
    musicModel1.lyrics = "Dẫu biết đôi ta đã từng trải qua bao nhiêu năm thiết tha yêu như vậy mà\n" +
            "Bây giờ lại xa lạ\n" +
            "Con đường tình giờ anh một mình đành lặng thinh\n" +
            "Nhìn em bước về tay cầm tay vui đùa cùng với ai\n" +
            "Ánh mắt đôi môi ta đã trót trao nhưng tại sao đến hôm nay lúc hiện tại\n" +
            "Em đã không còn được nhẫn nại\n" +
            "Bên cạnh người tình mới em đã quên rồi\n" +
            "Để anh khoác lên thân mình màu đơn côi\n" +
            "Ta đã từng hứa yêu nhau đến muôn đời sau\n" +
            "Anh vẫn luôn khắc sâu nhưng hôm nay ân tình phai màu\n" +
            "Còn gì nữa đâu và đành buông lơi những câu\n" +
            "Ta phải xa rời nhau như hoa kia dần úa màu\n" +
            "Trong lòng buồn mỗi khi anh ngắm hoa nhưng dòng lệ rơi\n" +
            "Em giờ đang khác đi, anh biết chắc chắn sẽ không còn cơ hội\n" +
            "Đành vậy thế thôi, ân tình nay vỡ đôi\n" +
            "Anh chúc em luôn nở nụ cười yên vui\n" +
            "Dẫu biết đôi ta đã từng trải qua bao nhiêu năm thiết tha yêu như vậy mà\n" +
            "Bây giờ lại xa lạ\n" +
            "Con đường tình giờ anh một mình đành lặng thinh\n" +
            "Nhìn em bước về tay cầm tay vui đùa cùng với ai\n" +
            "Ánh mắt đôi môi ta đã trót trao nhưng tại sao đến hôm nay lúc hiện tại\n" +
            "Em đã không còn được nhẫn nại\n" +
            "Bên cạnh người tình mới em đã quên rồi\n" +
            "Để anh khoác lên thân mình màu đơn côi\n" +
            "Ta đã từng hứa yêu nhau đến muôn đời sau\n" +
            "Anh vẫn luôn khắc sâu nhưng hôm nay ân tình phai màu\n" +
            "Còn gì nữa đâu và đành buông lơi những câu\n" +
            "Ta phải xa rời nhau như hoa kia dần úa màu\n" +
            "Trong lòng buồn mỗi khi anh ngắm hoa nhưng dòng lệ rơi\n" +
            "Em giờ đang khác đi, anh biết chắc chắn sẽ không còn cơ hội\n" +
            "Đành vậy thế thôi, ân tình nay vỡ đôi\n" +
            "Anh chúc em luôn nở nụ cười yên vui\n" +
            "Ta đã từng hứa yêu nhau đến muôn đời sau\n" +
            "Anh vẫn luôn khắc sâu nhưng hôm nay ân tình phai màu\n" +
            "Còn gì nữa đâu và đành buông lơi những câu\n" +
            "Ta phải xa rời nhau như hoa kia dần úa màu\n" +
            "Trong lòng buồn mỗi khi anh ngắm hoa nhưng dòng lệ rơi\n" +
            "Em giờ đang khác đi, anh biết chắc chắn sẽ không còn cơ hội\n" +
            "Đành vậy thế thôi, ân tình nay vỡ đôi\n" +
            "Anh chúc em luôn nở nụ cười yên vui\n" +
            "Anh chúc em luôn nở nụ cười yên vui"
    musicModel1.singerId = "2"
    musicModel1.country = COUNTRY_VN
    musicModel1.topicId = arrayListOf("2", "5", "6")
    musicModel1.uploadDate = 1712719256151
    listMusics.add(musicModel1)

    val musicModel2 = MusicModel()
    musicModel2.id = "3"
    musicModel2.name = "Lệ Lưu Ly"
    musicModel2.authorId = ""
    musicModel2.duration = 210.0
    musicModel2.url = "gs://musicplayer-c39cb.appspot.com/musics/nhbycwlelc.mp3"
    musicModel2.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/sddefault.jpg"
    musicModel2.lyrics = "Em có gì đâu ngoài những vết thương sâu\n" +
            "Yêu bao nhiêu lâu mà vẫn cứ thấy đau\n" +
            "Gieo bao tương tư nặng ôm những nỗi buồn\n" +
            "Em trao tim em rồi đem bán linh hồn\n" +
            "\n" +
            "Để đổi lấy đôi phút bình yên\n" +
            "Dù nước mắt gối ướt hằng đêm\n" +
            "Mà em ơi hi sinh vậy có xứng đáng?\n" +
            "Vết bầm trên người em nhiều thêm\n" +
            "\n" +
            "Chẳng có lấy một ngày ấm êm\n" +
            "Chỉ còn tai ương thâu đêm\n" +
            "Không còn tương lai đâu em!\n" +
            "Em ơi dừng lại khi nắng đã phai\n" +
            "\n" +
            "Dừng lại khi em bước sai\n" +
            "Dừng lại khi chẳng có ai\n" +
            "Biết em vẫn đang tồn tại\n" +
            "Em ơi đừng làm lệ vương khóe mi\n" +
            "\n" +
            "Đừng làm thân xác úa si\n" +
            "Tình tàn xin cất bước đi\n" +
            "Chớ lưu luyến thêm làm gì?\n" +
            "Cố yêu được chi\n" +
            "\n" +
            "Lệ mang sầu đau trên từng vết son\n" +
            "Người thương em xưa giờ đây chẳng còn\n" +
            "Đào sương hoa mơ em còn bỡ ngỡ\n" +
            "Thời gian thoi đưa tim em tan vỡ\n" +
            "\n" +
            "Thương khi em yêu có đâu ngờ\n" +
            "Trăng soi thân em cứ xác xơ\n" +
            "Ở đâu người em nhớ."
    musicModel2.singerId = "3"
    musicModel2.country = COUNTRY_VN
    musicModel2.topicId = arrayListOf("7", "8", "1")
    musicModel2.uploadDate = 1712719256151
    listMusics.add(musicModel2)

    val musicModel3 = MusicModel()
    musicModel3.id = "4"
    musicModel3.name = "2 Phút Hơn (CM1X Remix)"
    musicModel3.authorId = ""
    musicModel3.duration = 195.0
    musicModel3.url = "gs://musicplayer-c39cb.appspot.com/musics/HaiPhutHonCM1XRemix-Phao-6216244.mp3"
    musicModel3.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/maxresdefault.jpg"
    musicModel3.lyrics = "Tay em đang run run nhưng anh thì cứ rót đi.\n" +
            "Anh mà không nể em là khi mà anh không hết ly.\n" +
            "Uống thêm và ly vì đời chẳng mấy khi vui.\n" +
            "Nốc thêm vài chai vì anh em chẳng mấy khi gặp lại.\n" +
            "\n" +
            "Nơi đây đang xoay xoay, thế gian đang xoay vòng.\n" +
            "Anh đang ở nơi đâu, biết anh có thay lòng.\n" +
            "Đừng nói chi mà mình uống đi\n" +
            "12342341, hình như anh nói anh say rồi.\n" +
            "\n" +
            "1234231, hình như anh nói anh yêu em rồi.\n" +
            "Đừng nói chi mà mình uống đi (x2)\n" +
            "Tay em đang run run, nhưng anh thì cứ rót đi.\n" +
            "Anh mà không nể em là khi mà anh không hết ly.\n" +
            "\n" +
            "Uống thêm và ly vì đời chẳng mấy khi vui.\n" +
            "Nốc thêm vài chai vì anh em chẳng mấy khi gặp lại.\n" +
            "Nơi đây đang xoay xoay, thế gian đang xoay vòng.\n" +
            "Anh đang ở nơi đâu, biết anh có thay lòng.\n" +
            "\n" +
            "Đừng nói chi mà mình uống đi\n" +
            "12342341, hình như anh nói anh say rồi.\n" +
            "1234231, hình như anh nói anh yêu em rồi.\n" +
            "\n" +
            "Đừng nói chi mà mình uống đi (x2)\n" +
            "(Anh nói anh yêu em mà (x2)\n" +
            "Đừng nói chi mà mình uống đi (x2)"
    musicModel3.singerId = "5"
    musicModel3.country = COUNTRY_VN
    musicModel3.topicId = arrayListOf("1", "4", "5")
    musicModel3.uploadDate = 1712719256151
    listMusics.add(musicModel3)

    val musicModel4 = MusicModel()
    musicModel4.id = "5"
    musicModel4.name = "YÊU"
    musicModel4.authorId = ""
    musicModel4.duration = 266.0
    musicModel4.url = "gs://musicplayer-c39cb.appspot.com/musics/Yeu-MIN-7119302.mp3"
    musicModel4.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/834cdaf8e7552c17ed918a75cbba7ea8_1426588739.jpg"
    musicModel4.lyrics = "Yêu,\n" +
            "Là cùng nhau trong tay đi hết con đường,\n" +
            "Là cùng trao cho nhau ngọt môi hôn\n" +
            "Là vòng tay yêu thương ôm mãi không rời\n" +
            "Từng phút giây tuyệt vời.\n" +
            "\n" +
            "Yêu,\n" +
            "Là ngày em bên anh không chút ưu phiền\n" +
            "Từng buồn lo trôi qua ngày bình yên\n" +
            "Nồng nàn ta trao nhau giấy phút tuyệt vời\n" +
            "Nguyện thề luôn bên nhau mãi\n" +
            "\n" +
            "Dù thời gian trôi qua vẫn luôn bên người\n" +
            "Dù ngày tháng phôi pha ta vẫn không cách rời\n" +
            "Bên nhau suốt đời, cùng xây giấc mơ chung đôi\n" +
            "\n" +
            "Hãy bên em.. Lại gần nhé anh\n" +
            "Để cảm nhận những yêu thương từ trong trái tim này, dành hết cho người.\n" +
            "Hãy trao em nụ hôn đắm say ngọt ngào như phút giây ban đầu\n" +
            "Ngày nắng xanh ngời, mình ước có nhau trọn đời\n" +
            "\n" +
            "Yêu,\n" +
            "Là bình minh mỗi sớm có anh bên mình, là hoàng hôn mênh mang từng con phố\n" +
            "Mình cùng tay trong tay đi giữa cuộc đời, nguyện thề luôn bên nhau mãi\n" +
            "\n" +
            "Dù thời gian trôi qua vẫn luôn bên người\n" +
            "Dù ngày tháng phôi pha ta vẫn không cách rời\n" +
            "Bên nhau suốt đời, cùng xây giấc mơ chung đôi\n" +
            "\n" +
            "Hãy bên em.. Lại gần nhé anh\n" +
            "Để cảm nhận những yêu thương từ trong trái tim này, dành hết cho người.\n" +
            "Hãy trao em nụ hôn đắm say ngọt ngào như phút giây ban đầu\n" +
            "Ngày nắng xanh ngời, mình ước có nhau trọn đời"
    musicModel4.singerId = "4"
    musicModel4.country = COUNTRY_VN
    musicModel4.topicId = arrayListOf("1", "2", "3")
    musicModel4.uploadDate = 1712719256151
    listMusics.add(musicModel4)

    val musicModel5 = MusicModel()
    musicModel5.id = "6"
    musicModel5.name = "Bức Tranh Lem Màu"
    musicModel5.authorId = ""
    musicModel5.duration = 331.0
    musicModel5.url = "gs://musicplayer-c39cb.appspot.com/musics/BucTranhLemMau-KhangVietChauKhaiPhong-13809699.mp3"
    musicModel5.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/78fa0cf557d26165394e9e1ebf2dadf0.jpg"
    musicModel5.lyrics = "Bức tranh vẽ muôn màu mình từng tô cùng nhau\n" +
            "Tranh lem khi có 3 cây tô cùng\n" +
            "Bức tranh ấy ngày nào mình hoạ chung bối rối\n" +
            "Nhưng cớ sao giờ em nỡ thay tranh tô với ai rồi\n" +
            "\n" +
            "Hà ha ha há hà ha ha ha há ha\n" +
            "Đánh mất hy vọng\n" +
            "Giờ anh ngỗn ngang nát lòng\n" +
            "Chiều hoàng hôn giăng lối\n" +
            "Anh như chìm vào bóng tối\n" +
            "Anh biết câu chuyện mình phải xa nhau sớm muộn mà thôi\n" +
            "Hà ha há ha ha hà ha ha há\n" +
            "\n" +
            "Anh biết em đã giấu em điều gì\n" +
            "Hãy nói một lời rồi ta biệt ly\n" +
            "Anh sẽ không phiền, không làm em phải muộn sầu\n" +
            "Đằng này em giấu riêng mình\n" +
            "Lặng thầm ôm bao mối tình\n" +
            "Như đoá hoa lung linh, khiến cho người ta cứ si tình.\n" +
            "\n" +
            "Bao lý do giờ cũng chẳng được gì\n" +
            "Chỉ để che đi những dối lừa vì\n" +
            "Em có yêu anh đâu\n" +
            "Nên giờ đành phải chấp nhận\n" +
            "Mùa hoa đẹp nhất nơi ấy\n" +
            "Bồ Công Anh trắng như giấy\n" +
            "Theo gió mang em đi lu mờ theo tháng ngày\n" +
            "\n" +
            "\n" +
            "Hà ha ha há hà ha ha ha há ha\n" +
            "Duyên kiếp không thành\n" +
            "Giờ như cánh hoa úa tàn\n" +
            "Cuộc tình không viên mãn\n" +
            "Bao ân tình vội sang trang\n" +
            "Anh có trân trọng thì ta như nào cũng vỡ tan\n" +
            "Hà ha há ha ha hà ha ha há\n" +
            "\n" +
            "\"Ngày đó ta xem hoa nhưng giờ chỉ mỗi anh\""
    musicModel5.singerId = "6"
    musicModel5.country = COUNTRY_VN
    musicModel5.topicId = arrayListOf("1", "2", "3")
    musicModel5.uploadDate = 1712719256151
    listMusics.add(musicModel5)

    val musicModel6 = MusicModel()
    musicModel6.id = "7"
    musicModel6.name = "Hơn Cả Yêu (Entidi Remix)"
    musicModel6.authorId = ""
    musicModel6.duration = 320.0
    musicModel6.url = "gs://musicplayer-c39cb.appspot.com/musics/HonCaYeuEntidiRemix-DucPhuc-6229851.mp3"
    musicModel6.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/artworks-KJbCFx1RZ4flwQHa-A0L9yg-t500x500.jpg"
    musicModel6.lyrics = "Em hay hỏi anh rằng anh yêu em nhiều không?\n" +
            "Anh không biết phải nói thế nào\n" +
            "Để đúng với cảm xúc trong lòng\n" +
            "Khi anh nhìn em là anh thấy cuộc đời anh\n" +
            "\n" +
            "Là quá khứ và cả tương lai\n" +
            "Là hiện tại không bao giờ phai\n" +
            "Tình yêu trong anh vẫn luôn thầm lặng\n" +
            "Nhưng không có nghĩa không rộng lớn\n" +
            "\n" +
            "Chỉ là anh đôi khi khó nói nên lời\n" +
            "Mong em hãy cảm nhận thôi\n" +
            "Cao hơn cả núi, dài hơn cả sông\n" +
            "Rộng hơn cả đất, xanh hơn cả trời\n" +
            "\n" +
            "Anh yêu em, anh yêu em nhiều thế thôi\n" +
            "Vượt qua ngọn gió, vượt qua đại dương\n" +
            "Vượt qua cả những áng mây thiên đường\n" +
            "Dẫu có nói bao điều\n" +
            "\n" +
            "Cảm giác trong anh bây giờ có lẽ hơn cả yêu\n" +
            "Anh vẫn còn nhớ lần đầu tiên ta gặp nhau\n" +
            "Chẳng biết trước lần đó sẽ là\n" +
            "Lần cuối anh yêu một ai trên đời\n" +
            "\n" +
            "Anh không còn mơ\n" +
            "Gặp và yêu ai được nữa\n" +
            "Giờ anh đã có em đây rồi\n" +
            "Cùng em đi hết quãng đường đời\n" +
            "\n" +
            "Tình yêu trong anh vẫn luôn thầm lặng\n" +
            "Nhưng không có nghĩa không rộng lớn\n" +
            "Chỉ là anh đôi khi khó nói nên lời\n" +
            "Mong em hãy cảm nhận thôi\n" +
            "\n" +
            "Cao hơn cả núi, dài hơn cả sông\n" +
            "Rộng hơn cả đất, xanh hơn cả trời\n" +
            "Anh yêu em, anh yêu em nhiều thế thôi\n" +
            "Vượt qua ngọn gió, vượt qua đại dương\n" +
            "\n" +
            "Vượt qua cả những áng mây thiên đường\n" +
            "Dẫu có nói bao điều\n" +
            "Cảm giác trong anh bây giờ có lẽ hơn cả yêu\n" +
            "Cao hơn cả núi, dài hơn cả sông\n" +
            "\n" +
            "Rộng hơn cả đất, xanh hơn cả trời\n" +
            "Anh yêu em, anh yêu em nhiều thế thôi\n" +
            "Vượt qua ngọn gió, vượt qua đại dương\n" +
            "Vượt qua cả những áng mây thiên đường\n" +
            "\n" +
            "Dẫu có nói bao điều\n" +
            "Cảm giác trong anh bây giờ có lẽ hơn cả yêu\n" +
            "Cảm giác trong anh bây giờ có lẽ hơn cả yêu"
    musicModel6.singerId = "7"
    musicModel6.country = COUNTRY_VN
    musicModel6.topicId = arrayListOf("1", "6", "9")
    musicModel6.uploadDate = 1712719256151
    listMusics.add(musicModel6)

    val musicModel7 = MusicModel()
    musicModel7.id = "8"
    musicModel7.name = "Yêu Đương Khó Quá Thì Chạy Về Khóc Với Anh"
    musicModel7.authorId = ""
    musicModel7.duration = 224.0
    musicModel7.url = "gs://musicplayer-c39cb.appspot.com/musics/YeuDuongKhoQuaThiChayVeKhocVoiAnh-ERIK-7128950.mp3"
    musicModel7.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/yeuduongkhoqua.jpg"
    musicModel7.lyrics = "Mùi hương hoa Diên Vĩ hay là hương tóc mềm\n" +
            "Ngàn vì sao chẳng sáng hơn đôi mắt của em\n" +
            "Thật đẹp đến trăng thẹn thùng phải nấp sau mây\n" +
            "Vậy thì cớ sao a đây lại nỡ buông tay\n" +
            "ừ thì anh không muốn phải nhìn thấy em buồn\n" +
            "Chuyện tình yêu nếu như không thể nắm thì buông\n" +
            "Tự nhiên không biết vì lẽ gì\n" +
            "Cứ lại tự kìm lòng suốt thôi\n" +
            "\n" +
            "Chorus:\n" +
            "Cây không muốn lá rời cành\n" +
            "Khi lá vẫn còn xanh\n" +
            "Yêu đương khó quá thì chạy về khóc với anh\n" +
            "Một người luôn yêu em nhất\n" +
            "Chắc chắn sẽ không bỏ đi\n" +
            "Khiến em phải buồn được đâu\n" +
            "Phong ba sóng gió phủ đầu\n" +
            "Anh vẫn đứng đằng sau\n" +
            "Tình yêu có duyên thì tự tìm đến với nhau\n" +
            "Dù sao anh cũng vui lòng\n" +
            "Làm người anh trai đến suốt đời này được không?\n" +
            "\n" +
            "Verse2:\n" +
            "ừ thì cây chỉ muốn giữ chặt lá bên đời\n" +
            "Từng sợ 1 cơn gió kia sẽ đi đến dạo chơi\n" +
            "Sợ rằng cơn gió chợt vô tình\n" +
            "Cây ngậm ngùi phải nhìn lá rơi\n" +
            "\n" +
            "Chorus:\n" +
            "Cây không muốn lá rời cành\n" +
            "Khi lá vẫn còn xanh\n" +
            "Yêu đương khó quá thì chạy về khóc với anh\n" +
            "Một người luôn yêu em nhất\n" +
            "Chắc chắn sẽ không bỏ đi\n" +
            "Khiến em phải buồn được đâu\n" +
            "Phong ba sóng gió phủ đầu\n" +
            "Anh vẫn đứng đằng sau\n" +
            "Tình yêu có duyên thì tự tìm đến với nhau\n" +
            "Dù sao anh cũng vui lòng\n" +
            "Làm người anh trai đến suốt đời này được không?\n" +
            "\n" +
            "END:\n" +
            "Dù sao a cũng đây rồi\n" +
            "Làm người theo em đến hết đời này mà thôi"
    musicModel7.singerId = "8"
    musicModel7.country = COUNTRY_VN
    musicModel7.topicId = arrayListOf("1", "2", "3")
    musicModel7.uploadDate = 1712719256151
    listMusics.add(musicModel7)

    val musicModel8 = MusicModel()
    musicModel8.id = "9"
    musicModel8.name = "What Do You Mean?"
    musicModel8.authorId = ""
    musicModel8.duration = 331.0
    musicModel8.url = "gs://musicplayer-c39cb.appspot.com/musics/Justin-Bieber-What-Do-You-Mean.mp3"
    musicModel8.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/Justin_Bieber_-_What_Do_You_Mean_(Cover).png"
    musicModel8.lyrics = "What do you mean?\n" +
            "When you nod your head yes\n" +
            "But you wanna say no\n" +
            "What do you mean?\n" +
            "When you don’t want me to move\n" +
            "But you tell me to go\n" +
            "What do you mean?\n" +
            "Said we’re running out of time\n" +
            "Trying to catch the beat make up your mind\n" +
            "What do you mean?\n" +
            "Better make up your mind\n" +
            "What do you mean?\n" +
            "You’re so indecisive of what I’m saying\n" +
            "Don’t want us to end where do I start\n" +
            "First you wanna go left and you want to turn right\n" +
            "First you up and you’re down and then between\n" +
            "\n" +
            "Ohh I really want to know...\n" +
            "\n" +
            "When you nod your head yes\n" +
            "But you wanna say no\n" +
            "What do you mean?\n" +
            "What do you mean?\n" +
            "But you tell me to go\n" +
            "Said we’re running out of time\n" +
            "What do you mean?\n" +
            "Better make up your mind\n" +
            "What do you mean?\n" +
            "You’re overprotective when I’m leaving\n" +
            "Trying to compromise but I can’t win\n" +
            "You wanna make a point but you keep preaching\n" +
            "You had me from the start won’t let this end\n" +
            "First you wanna go left and you want to turn right\n" +
            "Wanna argue all day make love all night\n" +
            "First you up and you’re down and then between\n" +
            "Ohh I really want to know...\n" +
            "What do you mean?\n" +
            "When you nod your head yes\n" +
            "What do you mean?\n" +
            "When you don’t want me to move\n" +
            "But you tell me to go\n" +
            "What do you mean?\n" +
            "Said we’re running out of time\n" +
            "What do you mean?\n" +
            "Better make up your mind\n" +
            "What do you mean?"
    musicModel8.singerId = "9"
    musicModel8.country = COUNTRY_US
    musicModel8.topicId = arrayListOf("1", "2", "3")
    musicModel8.uploadDate = 1712719256151
    listMusics.add(musicModel8)

    val musicModel9 = MusicModel()
    musicModel9.id = "10"
    musicModel9.name = "Shake It Off"
    musicModel9.authorId = ""
    musicModel9.duration = 242.0
    musicModel9.url = "gs://musicplayer-c39cb.appspot.com/musics/Taylor-Swift-Shake-It-Off.mp3"
    musicModel9.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/artworks-000094034173-xldk11-t500x500.jpg"
    musicModel9.lyrics = "[Intro]\n" +
            "Yeah, ayy\n" +
            "\n" +
            "[Chorus]\n" +
            "I work hard every mother***in' day-ay-ay-ayy\n" +
            "I work hard, I work hard every day-ay-ay-ayy, yeah\n" +
            "But today is my day, it's my day\n" +
            "And no matter what they say, it's my day\n" +
            "La-la-la-la-la-la, yeah, yeah\n" +
            "\n" +
            "[Verse 1]\n" +
            "Roll up to the spot, feelin' real good\n" +
            "Think you gon' talk ***, you better not, my homies real hood\n" +
            "They say, \"Logic, why you do that?\" I don't know, I don't know\n" +
            "Yeah, they used to be like, \"Who that?\" I don't know, I don't know\n" +
            "Now they know my name wherever I go\n" +
            "Used to think that's what I wanted, but now, just don't know\n" +
            "No, I can't *** with that, nook if you buckin' back\n" +
            "Yeah, I been workin' but I ain't get nothin' back\n" +
            "Tell me the dealy now, hold up, wait, really now\n" +
            "All of that *** you been talkin' just silly now\n" +
            "\n" +
            "[Pre-Chorus]\n" +
            "Just as quick as you rise\n" +
            "Just as quick as you could fall\n" +
            "Oh, no no no, I can't *** with that at all\n" +
            "Can't *** with that at all\n" +
            "\n" +
            "[Chorus]\n" +
            "I work hard every mother***in' day-ay-ay-ayy\n" +
            "I work hard, I work hard every day-ay-ay-ayy, yeah\n" +
            "But today is my day, it's my day\n" +
            "And no matter what they say, it's my day\n" +
            "La-la-la-la-la-la, yeah\n" +
            "\n" +
            "[Verse 2]\n" +
            "All, all she ever wanted was attention\n" +
            "And a bunch of other *** I shouldn't mention\n" +
            "'Cause she got daddy issues for days, for days and days\n" +
            "But today, she ain't got *** to do, her right along with you\n" +
            "So we gon' *** around and vibe and vibe and vibe and vibe\n" +
            "I'm tryna live my life, but am I doing it right?\n" +
            "Hey, I'm tryna live my life, but am I doing it right?\n" +
            "'Cause they tell me I'm the man\n" +
            "You the man right now, you the man right now\n" +
            "With the whole wide world in the palm of your hand right now\n" +
            "*** the lights and the cameras and the money and the fame\n" +
            "I'ma do it for the fam right now\n" +
            "I'ma get it for the 301 and the R-A-double T-P-A-C 'cause you know\n" +
            "\n" +
            "[Chorus]\n" +
            "I work hard every mother***in' day-ay-ay-ayy\n" +
            "I work hard, I work hard every day-ay-ay-ayy, yeah\n" +
            "But today is my day, it's my day\n" +
            "And no matter what they say, it's my day\n" +
            "La-la-la-la-la-la, yeah, yeah"
    musicModel9.singerId = "10"
    musicModel9.country = COUNTRY_US
    musicModel9.topicId = arrayListOf("1", "2", "3")
    musicModel9.uploadDate = 1712719256151
    listMusics.add(musicModel9)

    val musicModel10 = MusicModel()
    musicModel10.id = "11"
    musicModel10.name = "One Last Time"
    musicModel10.authorId = ""
    musicModel10.duration = 249.0
    musicModel10.url = "gs://musicplayer-c39cb.appspot.com/musics/Ariana-Grande-One-Last-Time-Official.mp3"
    musicModel10.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/artworks-000115250163-kc16dx-t500x500.jpg"
    musicModel10.lyrics = "I was a liar\n" +
            "I gave into the fire\n" +
            "I know I should've fought it\n" +
            "At least I'm being honest\n" +
            "Feel like a failure\n" +
            "'Cause I know that I failed you\n" +
            "I should've done you better\n" +
            "'Cause you don't want a liar (come on)\n" +
            "\n" +
            "And I know, and I know, and I know\n" +
            "She gives you everything but, boy, I couldn't give it to you\n" +
            "And I know, and I know, and I know\n" +
            "That you got everything\n" +
            "But I got nothing here without you\n" +
            "\n" +
            "So one last time\n" +
            "I need to be the one who takes you home\n" +
            "One more time\n" +
            "I promise after that, I'll let you go\n" +
            "Baby, I don't care if you got her in your heart\n" +
            "All I really care is you wake up in my arms\n" +
            "One last time\n" +
            "I need to be the one who takes you home\n" +
            "\n" +
            "I don't deserve it\n" +
            "I know I don't deserve it\n" +
            "But stay with me a minute\n" +
            "I swear I'll make it worth it\n" +
            "Can't you forgive me?\n" +
            "At least just temporarily\n" +
            "I know that this is my fault\n" +
            "I should've been more careful (come on)\n" +
            "\n" +
            "And I know, and I know, and I know\n" +
            "She gives you everything but, boy, I couldn't give it to you\n" +
            "And I know, and I know, and I know\n" +
            "That you got everything\n" +
            "But I got nothing here without you, baby\n" +
            "\n" +
            "So one last time\n" +
            "I need to be the one who takes you home\n" +
            "One more time\n" +
            "I promise after that, I'll let you go\n" +
            "Baby, I don't care if you got her in your heart\n" +
            "All I really care is you wake up in my arms\n" +
            "One last time\n" +
            "I need to be the one who takes you home\n" +
            "\n" +
            "I know I shouldn't fight it\n" +
            "At least I'm being honest\n" +
            "But stay with me a minute\n" +
            "I swear I'll make it worth it\n" +
            "'Cause I don't want to be without you\n" +
            "\n" +
            "So one last time\n" +
            "I need to be the one who takes you home\n" +
            "One more time\n" +
            "I promise after that, I'll let you go\n" +
            "Baby, I don't care if you got her in your heart\n" +
            "All I really care is you wake up in my arms\n" +
            "One last time\n" +
            "I need to be the one who takes you home\n" +
            "\n" +
            "One last time\n" +
            "I need to be the one who takes you home"
    musicModel10.singerId = "11"
    musicModel10.country = COUNTRY_US
    musicModel10.topicId = arrayListOf("1", "2", "3")
    musicModel10.uploadDate = 1712719256151
    listMusics.add(musicModel10)

    val musicModel11 = MusicModel()
    musicModel11.id = "12"
    musicModel11.name = "Crazy"
    musicModel11.authorId = ""
    musicModel11.duration = 224.0
    musicModel11.url = "gs://musicplayer-c39cb.appspot.com/musics/IMGoingCrazy-Se7en-6292746.mp3"
    musicModel11.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/ab67616d0000b2731d40e925190f6fa291940029.jpeg"
    musicModel11.lyrics = "Naega mianhadaneun mareun jeoldaemeonje mothaneun motnan naneun\n" +
            "Ohiryeo sorijilleo deo kkeuge geureohke neowaye haruga shijakdwae (here go again)\n" +
            "Jeodjapeum sudo eobshi michyeobeorineun uri I don’t know what to do\n" +
            "Jigeum wae datuneun jido nan molla\n" +
            "\n" +
            "Naega eoddeon mareun handa haedo jigeum mankkeumeun\n" +
            "Neon deujil anha (you never listen)\n" +
            "Nal midji anha no~\n" +
            "Cheo-eum uri mannasseul daeye geu ma-eumeun eodiro gan geonji byeonhan geonji\n" +
            "Neomu meolli wa beorin geolkka\n" +
            "\n" +
            "Ije sangcheoppunin sarangeun shireo nan\n" +
            "Mae-il banbokdweneun datume jichyeo nan\n" +
            "Honja-il ttae boda wiro-un ggeuteoneun shigan soke gachin geot gata\n" +
            "Neoreul tathago shipji anha urin gatchi michyeo gana bwa\n" +
            "I'm going crazy crazy\n" +
            "I sarang soke we just going crazy\n" +
            "\n" +
            "Ijen ggeutiraneun nae mareun jinshim anin jinshinin naye mameun\n" +
            "Sunshikgane neol jujeo anke hae sokeuro nan babocheoreom huhehae (that I did you wrong)\n" +
            "Ireohke mae-ilgatchi apaya haneun uri\n" +
            "I don’t know what to do\n" +
            "Dodaeche wa mannaya haneunji molla\n" +
            "\n" +
            "Naega eoddeon mareun handa haedo jigeum mankkeumeun\n" +
            "Neon deujil anha (you never listen)\n" +
            "Nal midji anha no~\n" +
            "Cheo-eum uri mannasseul daeye geu ma-eumeun eodiro gan geonji byeonhan geonji\n" +
            "Neomu meolli wa beorin geolkka\n" +
            "\n" +
            "Ije sangcheoppunin sarangeun shireo nan\n" +
            "Mae-il banbokdweneun datume jichyeo nan\n" +
            "Honja-il ttae boda wiro-un ggeuteoneun shigan soke gachin geot gata\n" +
            "Neoreul tathago shipji anha urin gatchi michyeo gana bwa\n" +
            "I'm going crazy crazy\n" +
            "I sarang soke we just going crazy\n" +
            "\n" +
            "Naega halsu itneunge\n" +
            "Amugeotdo eobdaneunge nal michige hae\n" +
            "Ajik neoreul saranghaneunde\n" +
            "Nae nunmullon bujokhanga bwa\n" +
            "Neomuna apado ije na hollo\n" +
            "Nae jashineul chajeuryeogo hae good bye~\n" +
            "\n" +
            "Ije sangcheoppunin sarangeun shireo nan\n" +
            "Mae-il banbokdweneun datume jichyeo nan\n" +
            "Honja-il ttae boda wiro-un ggeuteoneun shigan soke gachin geot gata\n" +
            "Neoreul tathago shipji anha urin gatchi michyeo gana bwa\n" +
            "i'm going crazy crazy"
    musicModel11.singerId = "12"
    musicModel11.country = COUNTRY_KR
    musicModel11.topicId = arrayListOf("1", "2", "3")
    musicModel11.uploadDate = 1712719256151
    listMusics.add(musicModel11)

    val musicModel12 = MusicModel()
    musicModel12.id = "13"
    musicModel12.name = "You & Me"
    musicModel12.authorId = ""
    musicModel12.duration = 179.0
    musicModel12.url = "gs://musicplayer-c39cb.appspot.com/musics/YouMe-JENNIE-11876844.mp3"
    musicModel12.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/tumblr_940b5f7214f318a5fd385bae99381ca4_cbc05ce2_1280.jpg"
    musicModel12.lyrics = "You know I gotcha\n" +
            "Ain't nobody got you like that\n" +
            "Ain't nobody gon' have your back\n" +
            "Like the way I do\n" +
            "You love it, just say you do\n" +
            "You know you got me\n" +
            "Everything you do, everything you did\n" +
            "Everything I wish I was with\n" +
            "Makes me feel alright\n" +
            "I'm just saying, so\n" +
            "I really like it\n" +
            "Nothing in the world can make me feel\n" +
            "The way you do the things you do\n" +
            "I really like it\n" +
            "Nothing in the world can make me feel\n" +
            "The way you do the things you do\n" +
            "I love you and me\n" +
            "Dancing in the moonlight\n" +
            "Nobody can see\n" +
            "It's just you and me tonight\n" +
            "I love you and me\n" +
            "Dancing in the moonlight\n" +
            "Nobody can see\n" +
            "It's just you and me tonight\n" +
            "Look at you, now look at me\n" +
            "How you ever\n" +
            "Evеr gonna find someone like this\n" +
            "Look at you, now look at mе\n" +
            "How you ever\n" +
            "Ever gonna find someone like this\n" +
            "You're the reason my heart skips, drops\n" +
            "Just a little touch, my world stops\n" +
            "Finally, I know that you're mine\n" +
            "I don't wanna fall\n" +
            "Don't wanna play\n" +
            "This game of love, oh-eh-oh\n" +
            "There's nowhere to hide\n" +
            "I really like it\n" +
            "Nothing in the world could make me feel\n" +
            "The way you do, the things you do\n" +
            "I really like it\n" +
            "Nothing in the world could make me feel\n" +
            "The way you do, the things you do\n" +
            "I love you and me\n" +
            "Dancing in the moonlight\n" +
            "Nobody can see\n" +
            "It's just you and me tonight\n" +
            "I love you and me\n" +
            "Dancing in the moonlight\n" +
            "Nobody can see\n" +
            "It's just you and me tonight\n" +
            "Look at you, now look at me\n" +
            "How you ever\n" +
            "Ever gonna find someone like this\n" +
            "Look at you, now look at me\n" +
            "How you ever\n" +
            "Ever gonna find someone like this\n" +
            "Ever, ever gonna find someone like this\n" +
            "Someone like this\n" +
            "I don't care 'bout your first love\n" +
            "This should be your last one\n" +
            "Looking like your love is won\n" +
            "You look better on me that's fashion\n" +
            "Vogue walk, kill shots, lights, camera, action\n" +
            "Never been in love\n" +
            "Or never love me now, eh\n" +
            "Never tell him, better tell him\n" +
            "Better not change\n" +
            "I love you, I love me\n" +
            "And my way\n" +
            "Which one I love better\n" +
            "Better off not saying"
    musicModel12.singerId = "13"
    musicModel12.country = COUNTRY_KR
    musicModel12.topicId = arrayListOf("1", "2", "3")
    musicModel12.uploadDate = 1712719256151
    listMusics.add(musicModel12)

    val musicModel13 = MusicModel()
    musicModel13.id = "14"
    musicModel13.name = "Dark Horse"
    musicModel13.authorId = ""
    musicModel13.duration = 225.0
    musicModel13.url = "gs://musicplayer-c39cb.appspot.com/musics/Katy-Perry-Dark-Horse.mp3"
    musicModel13.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/Dark-Horse.jpeg"
    musicModel13.lyrics = "I knew you were\n" +
            "You were gonna come to me\n" +
            "& here you are\n" +
            "But you better choose carefully\n" +
            "Cause I...\n" +
            "I'm capable of anything\n" +
            "Of anything\n" +
            "& everything\n" +
            "\n" +
            "Make me your Aphrodite\n" +
            "Make me your one and only\n" +
            "But don't make me your enemy\n" +
            "Your enemy\n" +
            "Your enemy\n" +
            "\n" +
            "So you wanna play with magic\n" +
            "Boy you should know what you're fallin' for\n" +
            "Baby do you dare to do this\n" +
            "Cause I'm comin' at you like a dark horse\n" +
            "\n" +
            "Are you ready for\n" +
            "Ready for\n" +
            "A perfect storm\n" +
            "Perfect storm\n" +
            "Cause once you're mine\n" +
            "Once you're mine\n" +
            "There's no goin' back\n" +
            "\n" +
            "Mark my words\n" +
            "This love will make you levitate\n" +
            "Like a bird\n" +
            "Like a bird without a cage\n" +
            "We're down to earth\n" +
            "If you choose to walk away\n" +
            "Don't walk away\n" +
            "\n" +
            "It's in the palm of your hand now baby\n" +
            "It's a yes or a no\n" +
            "No maybe\n" +
            "So just be sure\n" +
            "Before you give it up to me\n" +
            "Up to me\n" +
            "Give it up to me\n" +
            "\n" +
            "So you wanna play with magic\n" +
            "Boy you should know what you're fallin' for\n" +
            "Baby do you dare to do this\n" +
            "Cause I'm comin' at you like a dark horse\n" +
            "\n" +
            "Are you ready for\n" +
            "Ready for\n" +
            "A perfect storm\n" +
            "Perfect storm\n" +
            "Cause once you're mine\n" +
            "Once you're mine\n" +
            "There's no goin' back\n" +
            "\n" +
            "She's a beast\n" +
            "I call her Karma\n" +
            "She eat your heart out\n" +
            "Like Jeffrey Dahmer\n" +
            "Be careful\n" +
            "Try not to lead her on\n" +
            "Shawty's heart was on steroids\n" +
            "Cause her love was so strong\n" +
            "You may fall in love\n" +
            "When you meet her\n" +
            "If you get the chance you better keep her\n" +
            "She swears by it but if you break her heart\n" +
            "She turn cold as a freezer\n" +
            "That fairy tale ending with a knight in shining armor\n" +
            "She can be my Sleeping Beauty\n" +
            "I'm gon' put her in a coma\n" +
            "Woo!\n" +
            "Damn I think I love her\n" +
            "Shawty so bad\n" +
            "I'm sprung and I don't care\n" +
            "She got me like a roller coaster\n" +
            "Turn the bedroom into a fair\n" +
            "Her love is like a drug\n" +
            "I was tryna hit it and quit it\n" +
            "But lil'mama so dope\n" +
            "I messed around and got addicted\n" +
            "\n" +
            "So you wanna play with magic\n" +
            "Boy you should know what you're fallin' for\n" +
            "(You should know)\n" +
            "Baby do you dare to do this\n" +
            "Cause I'm comin' at you like a dark horse\n" +
            "(Like a dark horse)\n" +
            "\n" +
            "Are you ready for\n" +
            "Ready for (Ready for)\n" +
            "A perfect storm\n" +
            "Perfect storm (A perfect storm)\n" +
            "Cause once you're mine\n" +
            "Once you're mine (ohhhh)\n" +
            "There's no goin' back"
    musicModel13.singerId = "14"
    musicModel13.country = COUNTRY_US
    musicModel13.topicId = arrayListOf("1", "2", "3")
    musicModel13.uploadDate = 1712719256151
    listMusics.add(musicModel13)

    val musicModel14 = MusicModel()
    musicModel14.id = "15"
    musicModel14.name = "Rose"
    musicModel14.authorId = ""
    musicModel14.duration = 222.0
    musicModel14.url = "gs://musicplayer-c39cb.appspot.com/musics/Rose-LEEHI-6292043.mp3"
    musicModel14.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/d5zttki-b4eedf74-24c3-46f5-9726-d7a9719a91ff.jpg"
    musicModel14.lyrics = "nae sarangeun saeppalgan Rose\n" +
            "jigeumeun areumdapgetjiman\n" +
            "nalkaroun gasiro neol apeuge halgeol\n" +
            "nae sarangeun saeppalgan Rose\n" +
            "geurae nan hyanggiropgetjiman\n" +
            "gakkai halsurok neol dachige halgeol\n" +
            "\n" +
            "geureon gabyeoun nunbicheuro nal chyeodaboji marajwoyo\n" +
            "hamburo sarangeul swipge yaegihajima\n" +
            "nae mameul gatgo sipdamyeon nae apeumdo gajyeoya haeyo\n" +
            "eonjenga bandeusi gasie jjillil tenikka\n" +
            "\n" +
            "nal neomu mitjima\n" +
            "neon nal ajik jal molla\n" +
            "So just run away run away\n" +
            "I said ooh ooh ooh\n" +
            "\n" +
            "nal saranghajima\n" +
            "neon nal ajik jal molla\n" +
            "I said run away just run away\n" +
            "dagaojima\n" +
            "\n" +
            "nae sarangeun saeppalgan Rose\n" +
            "jigeumeun areumdapgetjiman\n" +
            "nalkaroun gasiro neol apeuge halgeol\n" +
            "\n" +
            "nae sarangeun saeppalgan Rose\n" +
            "geurae nan hyanggiropgetjiman\n" +
            "gakkai halsurok neol dachige halgeol\n" +
            "\n" +
            "jasingame chan ni moseubi\n" +
            "nae nunen geujeo ansseureowo\n" +
            "nal hyanghan ssikssikhan balgeoreumi\n" +
            "oneulttara chorahae boyeo\n" +
            "gamjeong, sachiya naegen\n" +
            "sarang, jipchagui Best friend\n" +
            "So run away just run away\n" +
            "Cuz you and I must come to an end\n" +
            "\n" +
            "Every rose has its thorn\n" +
            "Every rose has its thorn\n" +
            "Every rose has its thorn\n" +
            "\n" +
            "nal neomu mitjima\n" +
            "neon nal ajik jal molla\n" +
            "So just run away run away\n" +
            "I said ooh ooh ooh\n" +
            "\n" +
            "nal saranghajima\n" +
            "neon nal ajik jal molla\n" +
            "I said run away just run away\n" +
            "dagaojima\n" +
            "\n" +
            "nae sarangeun saeppalgan Rose\n" +
            "jigeumeun areumdapgetjiman\n" +
            "nalkaroun gasiro neol apeuge halgeol\n" +
            "\n" +
            "nae sarangeun saeppalgan Rose\n" +
            "geurae nan hyanggiropgetjiman\n" +
            "gakkai halsurok neol dachige halgeor"
    musicModel14.singerId = "15"
    musicModel14.country = COUNTRY_KR
    musicModel14.topicId = arrayListOf("1", "2", "3")
    musicModel14.uploadDate = 1712719256151
    listMusics.add(musicModel14)

    val musicModel15 = MusicModel()
    musicModel15.id = "16"
    musicModel15.name = "Flower Shower"
    musicModel15.authorId = ""
    musicModel15.duration = 188.0
    musicModel15.url = "gs://musicplayer-c39cb.appspot.com/musics/FlowerShower-HyunA-6133649.mp3"
    musicModel15.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/80057265.jpeg"
    musicModel15.lyrics = ""
    musicModel15.singerId = "16"
    musicModel15.country = COUNTRY_KR
    musicModel15.topicId = arrayListOf("1", "2", "3")
    musicModel15.uploadDate = 1712719256151
    listMusics.add(musicModel15)

    val musicModel16 = MusicModel()
    musicModel16.id = "17"
    musicModel16.name = "Dạ Khúc (Nocturne) / 夜曲"
    musicModel16.authorId = ""
    musicModel16.duration = 229.0
    musicModel16.url = "gs://musicplayer-c39cb.appspot.com/musics/DaKhucNocturne-ChauKietLuan_4536u.mp3"
    musicModel16.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/1681981323981_640.jpg"
    musicModel16.lyrics = "一群嗜血的螞蟻 被腐肉所吸引 我面無表情 看孤獨的風景\n" +
            "失去妳 愛恨開始分明 失去妳 還有什麼事好關心\n" +
            "當鴿子不再象徵和平 我終於被提醒 廣場上餵食的是禿鷹\n" +
            "我用漂亮的押韻 形容被掠奪一空的愛情\n" +
            "啊 烏雲開始遮蔽 夜色不乾淨 公園裡 葬禮的回音 在漫天飛行\n" +
            "送妳的 白色玫瑰 在純黑的環境凋零 烏鴉在樹枝上詭異的很安靜\n" +
            "靜靜聽 我黑色的大衣 想溫暖妳 日漸冰冷的回憶 走過的 走過的 生命\n" +
            "啊 四周瀰漫霧氣 我在空曠的墓地 老去後還愛妳\n" +
            "為妳彈奏蕭邦的夜曲 紀念我死去的愛情\n" +
            "跟夜風一樣的聲音 心碎的很好聽\n" +
            "手在鍵盤敲很輕 我給的思念很小心\n" +
            "妳埋葬的地方叫幽冥\n" +
            "為妳彈奏蕭邦的夜曲 紀念我死去的愛情\n" +
            "而我為妳隱姓埋名 在月光下彈琴\n" +
            "對妳心跳的感應 還是如此溫熱親近\n" +
            "懷念妳那鮮紅的唇印\n" +
            "♪\n" +
            "那些斷翅的蜻蜓 散落在這森林 而我的眼睛 沒有絲毫同情\n" +
            "失去妳 淚水混濁不清 失去妳 我連笑容都有陰影\n" +
            "風在長滿青苔的屋頂 嘲笑我的傷心 像一口沒有水的枯井\n" +
            "我用淒美的字型 描繪後悔莫及的那愛情\n" +
            "為妳彈奏蕭邦的夜曲 紀念我死去的愛情\n" +
            "跟夜風一樣的聲音 心碎的很好聽\n" +
            "手在鍵盤敲很輕 我給的思念很小心\n" +
            "妳埋葬的地方叫幽冥\n" +
            "為妳彈奏蕭邦的夜曲 紀念我死去的愛情\n" +
            "而我為妳隱姓埋名 在月光下彈琴\n" +
            "對妳心跳的感應 還是如此溫熱親近\n" +
            "懷念妳那鮮紅的唇印\n" +
            "一群嗜血的螞蟻 被腐肉所吸引 我面無表情 看孤獨的風景\n" +
            "失去妳 愛恨開始分明 失去妳 還有什麼事好關心\n" +
            "當鴿子不再象徵和平 我終於被提醒 廣場上餵食的是禿鷹\n" +
            "我用漂亮的押韻 形容被掠奪一空的愛情"
    musicModel16.singerId = "17"
    musicModel16.country = COUNTRY_CN
    musicModel16.topicId = arrayListOf("1", "2", "3")
    musicModel16.uploadDate = 1712719256151
    listMusics.add(musicModel16)

    val musicModel17 = MusicModel()
    musicModel17.id = "18"
    musicModel17.name = "Tik Tok / 倒數"
    musicModel17.authorId = ""
    musicModel17.duration = 229.0
    musicModel17.url = "gs://musicplayer-c39cb.appspot.com/musics/TikTok-DangTuKyGEM-6009966.mp3"
    musicModel17.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/artworks-000398019819-nadrbo-t500x500.jpg"
    musicModel17.lyrics = "还没到的樱花季\n" +
            "Hái méi de yīnghuā\n" +
            "Mùa hoa anh đào còn chưa đến\n" +
            "\n" +
            "还没用的照相机\n" +
            "Hái méi yòng de zhàoxiàngjī\n" +
            "Chiếc máy ảnh còn chưa dùng\n" +
            "\n" +
            "还没光临的餐厅\n" +
            "Hái méi guānglín de cāntīng\n" +
            "Nhà hàng chưa từng ghé qua\n" +
            "\n" +
            "还在期待有着你的旅行\n" +
            "Hái zài qídài yǒuzhe nǐ de lǚxíng\n" +
            "Chuyến du lịch vẫn đang đợi sẽ có anh đồng hành\n" +
            "\n" +
            "等待日落的巴黎\n" +
            "Děngdài rìluò de bālí\n" +
            "Như Paris chung thủy đợi hoàng hôn\n" +
            "\n" +
            "铁塔之下牵着你\n" +
            "Tiětǎ zhī xià qiānzhe nǐ\n" +
            "Nắm tay anh dưới ngọn tháp\n" +
            "\n" +
            "等待说着我愿意\n" +
            "Děngdài shuōzhe wǒ yuànyì\n" +
            "Chờ đợi để nói với anh rằng em tình nguyện\n" +
            "\n" +
            "等待未来每天身边有你\n" +
            "Děngdài wèilái měitiān shēnbiān yǒu nǐ\n" +
            "Chờ đợi mỗi ngày trong tương lai sẽ có anh bên cạnh\n" +
            "\n" +
            "一点一滴每一天珍惜\n" +
            "Yī diǎn yī dī měi yītiān zhēnxī\n" +
            "Từng chút từng chút một, mỗi ngày em đều trân trọng\n" +
            "\n" +
            "怕突然来不及好好的爱你\n" +
            "Pá túrán láibují hǎo hǎo de ài nǐ\n" +
            "Em lo sợ rằng sẽ quá muộn để yêu anh\n" +
            "\n" +
            "时针一直到数着\n" +
            "Shízhēn yīzhí dào shùzhe\n" +
            "Kim đồng hồ gõ nhịp đếm ngược\n" +
            "\n" +
            "我们剩下的快乐\n" +
            "Wǒmen shèng xià de kuàilè\n" +
            "Hạnh phúc tàn dư của đôi ta\n" +
            "\n" +
            "此刻相拥的狂热\n" +
            "Cǐkè xiāng yōng de kuángrè\n" +
            "Khoảnh khắc cuồng nhiệt ôm lấy nhau\n" +
            "\n" +
            "却永远都深刻\n" +
            "Què yǒngyuǎn dōu shēnkè\n" +
            "Sẽ mãi mãi khắc ghi\n" +
            "\n" +
            "心跳一直到数着\n" +
            "Xīntiào yīzhí dào shùzhe\n" +
            "Nhịp tim cứ thế đếm ngược\n" +
            "\n" +
            "生命剩下的温热\n" +
            "Shèngmìng shèng xià de wēn rè\n" +
            "Ấm áp tàn dư của sinh mệnh\n" +
            "\n" +
            "至少用力地爱着\n" +
            "Zhìshǎo yònglì de àizhe\n" +
            "Ít nhất đã dốc hết mình để yêu\n" +
            "\n" +
            "还乌黑的头发\n" +
            "Hái wūhēi de tóufǎ\n" +
            "Mái tóc vẫn còn đen\n" +
            "\n" +
            "有你就不怕白了\n" +
            "Yǒu nǐ jiù bùpà báile\n" +
            "Có anh chẳng sợ bạc mái đầu\n" +
            "\n" +
            "漆黑过后是旭日\n" +
            "Qīhēi guòhòu shì xùrì\n" +
            "Tối tăm trôi qua sẽ là ánh bình minh\n" +
            "\n" +
            "泪流以后是坚持\n" +
            "Lèi liú yǐhòu shì jiānchí\n" +
            "Rơi nước rồi sẽ hóa kiên trì\n" +
            "\n" +
            "真的爱是日复日\n" +
            "Zhēn de ài shì rì fù rì\n" +
            "Tình yêu là ngày lại ngày qua, chưa từng bỏ cuộc\n" +
            "\n" +
            "從不放棄重复说你愿意\n" +
            "Cóng bù fàngqì chóngfù shuō nǐ yuànyì\n" +
            "Vẫn lặp lại câu em bằng lòng\n" +
            "\n" +
            "还没退化的眼睛\n" +
            "Hái méi tuìhuà de yǎnjīng\n" +
            "Đôi mắt còn chưa suy yếu\n" +
            "\n" +
            "抓紧时间看看你\n" +
            "Zhuājǐn shíjiān kàn kàn nǐ\n" +
            "Nắm chặt thời gian nhìn anh thật kỹ\n" +
            "\n" +
            "爱是從来不止息\n" +
            "Ài shì cónglái bu zhǐxī\n" +
            "Tình yêu là chưa từng ngừng lại\n" +
            "\n" +
            "一个风景每天新的生命\n" +
            "Yīgè fēngjǐng měitiān xīn de shēngmìng\n" +
            "Mỗi một phong cảnh, mỗi ngày đều là một sinh mệnh mới\n" +
            "\n" +
            "一点一滴每一天珍惜\n" +
            "Yī diǎn yī dī měi yītiān zhēnxī\n" +
            "Từng chút từng chút một, mỗi ngày đều trân trọng\n" +
            "\n" +
            "用尽每一口气好好的爱你\n" +
            "Yòng jìn měi yī kǒuqì hǎo hǎo de ài nǐ\n" +
            "Dốc hết từng hơi thở để yêu anh dài lâu\n" +
            "\n" +
            "时针一直到数着\n" +
            "Shízhēn yīzhí dào shùzhe\n" +
            "Kim đồng hồ gõ nhịp đếm ngược\n" +
            "\n" +
            "我们剩下的快乐\n" +
            "Wǒmen shèng xià de kuàilè\n" +
            "Hạnh phúc tàn dư của đôi ta\n" +
            "\n" +
            "此刻相拥的狂热\n" +
            "Cǐkè xiāng yòng de kuángrè\n" +
            "Khoảnh khắc cuồng nhiệt ôm lấy nhau\n" +
            "\n" +
            "却永远都深刻\n" +
            "Què yǒngyuǎn dōu shēnkè\n" +
            "Sẽ mãi mãi khắc ghi\n" +
            "\n" +
            "心跳一直到数着\n" +
            "Xīntiào yīzhí dào shùzhe\n" +
            "Nhịp tim cứ thế đếm ngược\n" +
            "\n" +
            "生命剩下的温热\n" +
            "Shèngmìng shèng xià de wēn rè\n" +
            "Ấm áp tàn dư của sinh mệnh\n" +
            "\n" +
            "至少用力地爱着\n" +
            "Zhìshǎo yònglì de àizhe\n" +
            "Ít nhất đã dốc hết mình để yêu\n" +
            "\n" +
            "还乌黑的头发\n" +
            "Hái wūhēi de tóufǎ\n" +
            "Mái tóc vẫn còn đen\n" +
            "\n" +
            "有你就不怕白了\n" +
            "yǒu nǐ jiù bùpà báile\n" +
            "Có anh chẳng sợ bạc mái đầu\n" +
            "\n" +
            "咖啡再不喝就酸了\n" +
            "Kāfēi zàibu hē jiù suānle\n" +
            "Cà phê còn không uống sẽ hóa chua\n" +
            "\n" +
            "晚餐再不吃就冷了\n" +
            "Wǎncān zàibu chī jiù lěngle\n" +
            "Bữa tối còn không ăn sẽ nguội lạnh\n" +
            "\n" +
            "爱着为什么不说呢\n" +
            "Àizhe wèishénme bù shuō ne\n" +
            "Đã yêu tại sao không nói ra\n" +
            "\n" +
            "难道错过了才来后悔着\n" +
            "Nándào cuòguòle cái lái hòuhuǐzhe\n" +
            "Chẳng lẽ đợi bỏ lỡ rồi mới đến hối hận\n" +
            "\n" +
            "谁梦未实现就醒了\n" +
            "Shéi mèng wèi shíxiàn jiù xǐngle\n" +
            "Giấc mộng của ai chưa thực hiện đã tỉnh\n" +
            "\n" +
            "谁心没开过就灰了\n" +
            "Shéi xīn méi kāiguò jiù huīle\n" +
            "Trái tim ai chưa nở rộ đã hóa tro tàn\n" +
            "\n" +
            "追逐爱的旅途曲折\n" +
            "Zhuīzhú ài de lǚtú qūzhé\n" +
            "Hành trình theo đuổi tình yêu quanh co khúc khuỷu\n" +
            "\n" +
            "就算再曲折为你都值得\n" +
            "Jiùsuàn zài qūzhé wèi nǐ dōu zhídé\n" +
            "Dẫu khúc khuỷu thế nào, vì anh đều xứng đáng\n" +
            "\n" +
            "一点一滴每一天珍惜\n" +
            "Yī diǎn yī dī měi yītiān zhēnxī\n" +
            "Từng chút từng chút một,mỗi ngày đều trân trọng\n" +
            "\n" +
            "用尽每一口气好好的爱你\n" +
            "Yòng jìn měi yī kǒuqì hǎo hǎo de ài nǐ\n" +
            "Dốc hết từng hơi thở để yêu anh dài lâu\n" +
            "\n" +
            "时针一直到数着\n" +
            "Shízhēn yīzhí dào shùzhe\n" +
            "Kim đồng hồ gõ nhịp đếm ngược\n" +
            "\n" +
            "我们剩下的快乐\n" +
            "Wǒmen shèng xià de kuàle\n" +
            "Hạnh phúc tàn dư của đôi ta\n" +
            "\n" +
            "此刻相拥的狂热\n" +
            "Cǐkè xiāng yòng de kuángrè\n" +
            "Khoảnh khắc cuồng nhiệt ôm lấy nhau\n" +
            "\n" +
            "却永远都深刻\n" +
            "Què yǒngyuǎn dōu shēnkè\n" +
            "Sẽ mãi mãi khắ ghi\n" +
            "\n" +
            "心跳一直到数着\n" +
            "Xīntiào yīzhí dào shùzhe\n" +
            "Nhịp tim cứ thế đếm ngược\n" +
            "\n" +
            "生命剩下的温热\n" +
            "Shēngmìng shēng xià de wēnrè\n" +
            "Ấm áp tàn dư của sinh mệnh\n" +
            "\n" +
            "至少痛并快乐着\n" +
            "Zhìshǎo tòng bìng kuàilezhe\n" +
            "Ít nhất đau cũng hạnh phúc\n" +
            "\n" +
            "爱过才算活着\n" +
            "Àiguò cái suàn huózhe\n" +
            "Tình yêu mới xem như đang sống\n" +
            "\n" +
            "有你别无所求了\n" +
            "Yǒu nǐ bié wú suǒ qiúle\n" +
            "Có anh em chẳng mong gì hơn nữa\n" +
            "\n" +
            "有你别无所求了\n" +
            "Yǒu nǐ bié wú suǒ qiúle\n" +
            "Có anh em chẳng mong gì hơn nữa\n" +
            "\n" +
            "有你别无所求了\n" +
            "Yǒu nǐ bié wú suǒ qiúle\n" +
            "Có anh em chẳng mong gì hơn nữa\n" +
            "\n" +
            "有你别无所求了\n" +
            "Yǒu nǐ bié wú suǒ qiúle\n" +
            "Có anh em chẳng mong gì hơn nữa\n" +
            "\n" +
            "有你别无所求了\n" +
            "Yǒu nǐ bié wú suǒ qiúle\n" +
            "Có anh em chẳng mong gì hơn nữa\n" +
            "\n" +
            "有你别无所求了\n" +
            "Yǒu nǐ bié wú suǒ qiúle\n" +
            "Có anh em chẳng mong gì hơn nữa"
    musicModel17.singerId = "18"
    musicModel17.country = COUNTRY_CN
    musicModel17.topicId = arrayListOf("1", "2", "3")
    musicModel17.uploadDate = 1712719256151
    listMusics.add(musicModel17)

    val musicModel18 = MusicModel()
    musicModel18.id = "19"
    musicModel18.name = "See Tình (Cucak Remix) (Single)"
    musicModel18.authorId = ""
    musicModel18.duration = 170.0
    musicModel18.url = "gs://musicplayer-c39cb.appspot.com/musics/SeeTinhCucakRemix-HoangThuyLinh-7208579.mp3"
    musicModel18.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/Coverseetinh.jpeg"
    musicModel18.lyrics = "[Verse 1]\n" +
            "Uầy uầy uây uây\n" +
            "Sao mới gặp lần đầu mà đầu mình quay quay\n" +
            "Anh ơi anh à\n" +
            "Anh bỏ bùa gì mà lại làm em yêu vậy?\n" +
            "Bae bae bae bae\n" +
            "Em nói từ đầu baby can you stay\n" +
            "Mai đi coi ngày\n" +
            "Xem cưới ngày nào thì nhà mình đông con vậy?\n" +
            "\n" +
            "[Pre-chorus 1]\n" +
            "Nếu như một câu nói có thể khiến anh vui\n" +
            "Sẽ suốt ngày luôn nói không ngừng để anh cười\n" +
            "Nếu em làm như thế trông em có hâm không?\n" +
            "Đem ngay vô nhà thương, đem ngay vô nhà thương\n" +
            "Đem ngay vô nhà anh để thương!\n" +
            "\n" +
            "[Chorus]\n" +
            "Giây phút em gặp anh là\n" +
            "Em biết em see tình\n" +
            "Tình tình tình tang tang tính\n" +
            "Tang tình tình tình tang tang tang\n" +
            "Giây phút em gặp anh là\n" +
            "Em biết em see tình\n" +
            "Tình đừng tình toan toan tính\n" +
            "Toang tình mình tình tan tan tan tình\n" +
            "\n" +
            "[Verse 2]\n" +
            "Tới đâu thì tới\n" +
            "Tới đâu thì tới\n" +
            "Em cũng chẳng biết tới đâu\n" +
            "Biết yêu là khó\n" +
            "Không yêu cũng khó\n" +
            "Em cũng chẳng biết thế nào\n" +
            "Hôm nay tia cực tím xuyên qua trời đêm\n" +
            "Anh như tia cực hiếm xuyên ngay vào tim\n" +
            "Ấy ấy ấy chết em rồi\n" +
            "Ấy ấy chết thật thôi\n" +
            "\n" +
            "[Pre-chorus 2]\n" +
            "Nếu như một câu nói có thể khiến anh vui\n" +
            "Nói thêm một câu nữa có khi khiến anh buồn\n" +
            "Nếu em làm như thế trông em có hâm không?\n" +
            "Đem ngay vô nhà thương, đem ngay vô nhà thương\n" +
            "Đem ngay vô nhà anh để thương!\n" +
            "\n" +
            "[Chorus]\n" +
            "Giây phút em gặp anh là\n" +
            "Em biết em see tình\n" +
            "Tình tình tình tang tang tính\n" +
            "Tang tình tình tình tang tang tang\n" +
            "Giây phút em gặp anh là\n" +
            "Em biết em see tình\n" +
            "Tình đừng tình toan toan tính\n" +
            "Toang tình mình tình tan tan tan tình"
    musicModel18.singerId = "1"
    musicModel18.country = COUNTRY_VN
    musicModel18.topicId = arrayListOf("1", "2", "3")
    musicModel18.uploadDate = 1712719256151
    listMusics.add(musicModel18)

    val musicModel19 = MusicModel()
    musicModel19.id = "20"
    musicModel19.name = "Duyên Âm (Quavu Remix)"
    musicModel19.authorId = ""
    musicModel19.duration = 214.0
    musicModel19.url = "gs://musicplayer-c39cb.appspot.com/musics/DuyenAmQuavuRemix-HoangThuyLinh-6265164.mp3"
    musicModel19.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/1682011313889_640.jpg"
    musicModel19.lyrics = "Từng đêm mơ, con tim em luôn đợi chờ\n" +
            "Chàng nhà thơ, làm cho lòng em vui ngẩn ngơ\n" +
            "Mùa thu sang, đông qua xuân ngang hạ tàn\n" +
            "Tình tình tang, tình yêu nào đâu dễ dàng\n" +
            "\n" +
            "[Pre-chorus]\n" +
            "Người ta hoài đưa đón\n" +
            "Trong khi em chẳng hề yêu\n" +
            "Đừng nên làm như thế\n" +
            "À í a anh à\n" +
            "Tại sao phiền như thế?\n" +
            "Yêu em anh được gì đâu?\n" +
            "Điều anh hằng mong muốn\n" +
            "Ba má em sẽ buồn\n" +
            "Anh ơi anh về đi\n" +
            "\n" +
            "[Chorus]\n" +
            "Thiên linh linh ơ địa linh linh\n" +
            "Anh đừng linh tinh xin hãy đi về\n" +
            "Hai bên thông gia nhà em la\n" +
            "Em vừa vu quy sáng nay rồi mà\n" +
            "Anh ơi anh gì ơi anh đi về đi\n" +
            "Anh ơi đứng đây làm gì\n" +
            "Bao lâu em chưa hề yêu anh\n" +
            "Anh về đi anh quay đầu là bờ!\n" +
            "\n" +
            "[Rap]\n" +
            "Em có chồng rồi anh ơi\n" +
            "Đây nắm xôi cộng thêm con gà em mời anh xơi\n" +
            "Nhà em có thổ địa rồi nên anh về đi nhé\n" +
            "Đâu phải muốn gì cũng được nên anh đừng làm như thế\n" +
            "Nhà em không còn dư ghế cho anh ngồi đâu\n" +
            "Chó dữ đã vào tư thế anh ơi anh về mau\n" +
            "Từ nay và về sau anh đừng ghé nữa nhé\n" +
            "Ba hồn bảy vía xin anh đừng ghé nữa nhé\n" +
            "\n" +
            "[Pre-chorus]\n" +
            "Người ta thường hay nói\n" +
            "Khó quá không được thì thôi\n" +
            "Nhiều khi tình duyên tới\n" +
            "Là ý do ông trời\n" +
            "Đừng nên hoài trông ngóng\n" +
            "Theo em không được gì đâu\n" +
            "Điều anh hằng mong muốn\n" +
            "Ba má em sẽ buồn\n" +
            "Anh ơi anh về đi\n" +
            "\n" +
            "[Chorus]\n" +
            "Thiên linh linh ơ địa linh linh\n" +
            "Anh đừng linh tinh xin hãy đi về\n" +
            "Hai bên thông gia nhà em la\n" +
            "Em vừa vu quy sáng nay rồi mà\n" +
            "Anh ơi anh gì ơi anh đi về đi\n" +
            "Anh ơi đứng đây làm gì\n" +
            "Bao lâu em chưa hề yêu anh\n" +
            "Anh về đi anh quay đầu là bờ"
    musicModel19.singerId = "1"
    musicModel19.country = COUNTRY_VN
    musicModel19.topicId = arrayListOf("1", "2", "3")
    musicModel19.uploadDate = 1712719256151
    listMusics.add(musicModel19)

    val musicModel20 = MusicModel()
    musicModel20.id = "21"
    musicModel20.name = "Lưu Số Em Đi"
    musicModel20.authorId = ""
    musicModel20.duration = 342.0
    musicModel20.url = "gs://musicplayer-c39cb.appspot.com/musics/LuuSoEmDiDaiMeoRemix-HuynhVanVuPhungTien-7202629.mp3"
    musicModel20.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/fb2esk1dkww2a_600.jpg"
    musicModel20.lyrics = "Lưu số em đi\n" +
            "Khi nào má có cần con dâu\n" +
            "Thì gọi cho em\n" +
            "Đêm dài lắm lúc phải cô đơn\n" +
            "Một mình trước thềm\n" +
            "Em sẽ đến trong êm đềm\n" +
            "Bonus thêm đôi môi mềm\n" +
            "Anh hỡi anh à\n" +
            "Khi nào tía có cần con dâu\n" +
            "Thì gọi em nha\n" +
            "Em đã chán suốt ngày rong chơi\n" +
            "Một mình la cà\n" +
            "Chỉ muốn nên đôi\n" +
            "Và cùng thức giấc chung một nhà\n" +
            "Thật ra tôi đã thích anh lâu rồi\n" +
            "Chỉ là ngại nói ra mà thôi\n" +
            "Chuyện của anh và tôi\n" +
            "Vẫn rất xa và xôi\n" +
            "Chạm mặt nhưng chẳng *** nói câu gì\n" +
            "Lặng nhìn anh thoáng qua vội đi\n" +
            "Rồi phút chốc nghĩ suy\n" +
            "Không biết phải làm chi\n" +
            "Nuôi hy vọng dù đã bấy lâu\n" +
            "Mà sao không thể nói dẫu mấy câu\n" +
            "Là anh ơi\n" +
            "Là anh ơi\n" +
            "Lưu số em đi\n" +
            "Khi nào má có cần con dâu\n" +
            "Thì gọi cho em\n" +
            "Đêm dài lắm lúc phải cô đơn\n" +
            "Một mình trước thềm\n" +
            "Em sẽ đến trong êm đềm\n" +
            "Bonus thêm đôi môi mềm\n" +
            "Anh hỡi anh à\n" +
            "Khi nào tía có cần con dâu\n" +
            "Thì gọi em nha\n" +
            "Em đã chán suốt ngày rong chơi\n" +
            "Một mình la cà\n" +
            "Chỉ muốn nên đôi\n" +
            "Và cùng thức giấc chung một nhà\n" +
            "Vốn dĩ là thiên thần\n" +
            "Nhưng sao khi đến đây\n" +
            "Em lại làm điều ác\n" +
            "Đó là bóp nát con tim anh\n" +
            "Ngay từ lần đầu tiên gặp mặt\n" +
            "Thay vì làm điều khác\n" +
            "Việc ngơ ngác trước vài cô gái lạ\n" +
            "Từ khi em xuất hiện\n" +
            "Thì anh đã say no\n" +
            "Vì em đã khiến tâm tư này xơ xác\n" +
            "Suy nghĩ luôn man mác\n" +
            "Về em ở trong đầu\n" +
            "Anh không *** hứa\n" +
            "Là sẽ yêu em\n" +
            "Suốt đời không thay đổi\n" +
            "Cũng chẳng *** hứa\n" +
            "Tình cảm ngọt ngào\n" +
            "Không lúc nào phai phôi\n" +
            "Sẽ không thề non hẹn biển\n" +
            "Hứa hẹn mai sau em ơi\n" +
            "Chỉ hứa ngày nào còn nhau\n" +
            "Là yêu hết lòng ngày đó thôi em ơi\n" +
            "Nuôi hy vọng dù đã bấy lâu\n" +
            "Mà sao không thể nói dẫu mấy câu\n" +
            "Là anh ơi\n" +
            "Là anh ơi\n" +
            "Lưu số em đi\n" +
            "Khi nào má có cần con dâu\n" +
            "Thì gọi cho em\n" +
            "Đêm dài lắm lúc phải cô đơn\n" +
            "Một mình trước thềm\n" +
            "Em sẽ đến trong êm đềm\n" +
            "Bonus thêm đôi môi mềm\n" +
            "Anh hỡi anh à\n" +
            "Khi nào tía có cần con dâu\n" +
            "Thì gọi em nha\n" +
            "Em đã chán suốt ngày rong chơi\n" +
            "Một mình la cà\n" +
            "Chỉ muốn nên đôi\n" +
            "Và cùng thức giấc chung một nhà"
    musicModel20.singerId = "3"
    musicModel20.country = COUNTRY_VN
    musicModel20.topicId = arrayListOf("1", "2", "3")
    musicModel20.uploadDate = 1712719256151
    listMusics.add(musicModel20)

    val musicModel21 = MusicModel()
    musicModel21.id = "22"
    musicModel21.name = "Ơ Sao Cơ "
    musicModel21.authorId = ""
    musicModel21.duration = 192.0
    musicModel21.url = "gs://musicplayer-c39cb.appspot.com/musics/OSaoCo-VuPhungTien-8062797.mp3"
    musicModel21.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/64b96562-ce3f-4516-b1cd-0c7777fc36d3.jpg"
    musicModel21.lyrics = "Một ngày bình thường, bộ đồ ngủ màu hường\n" +
            "Tạo dáng trước gương đôi ba tư thế không giống ai\n" +
            "Xoay tới, xoay lui lại ăn sáng một mình\n" +
            "Phải chi, có ai kia bên mình\n" +
            "\n" +
            "Em thường mơ mộng về những điều xa vời\n" +
            "Nơi chỉ có hai ta cùng tới...\n" +
            "Tâm hồn yên bình, mình tâm tình dưới ánh trăng\n" +
            "Phút chốc vẽ nên thơ em ngỡ rằng\n" +
            "\n" +
            "Lắm lúc thấy.. ôi mình đáng yêu ghê\n" +
            "Cớ sao chưa ai rước về\n" +
            "Ơ sao cơ? Anh cũng thích em ư\n" +
            "Thật không? Chẳng lừa người ta đấy chứ?\n" +
            "Em chỉ muốn một tình yêu bình thường\n" +
            "Chỉ cần anh trao trọn lòng thương..\n" +
            "Tay em đây, nhanh nắm lấy đi anh\n" +
            "Mình cùng xây nên tình yêu ngát xanh\n" +
            "\n" +
            "Anh chưa có người yêu\n" +
            "Em hông có người yêu\n" +
            "Bao lâu chưa gặp nhau\n" +
            "Trong vườn cây trái\n" +
            "Tiêu điều yếu xìu\n" +
            "\n" +
            "Anh chưa có người yêu (chưa nha)\n" +
            "Em hông có người yêu (chưa luôn)\n" +
            "Khi hai ta gặp nhau\n" +
            "Mới nhìn một cái yêu nhiều yêu nhiều...\n" +
            "\n" +
            "Anh không sến súa\n" +
            "Không hay văn vở\n" +
            "Nhưng sẽ đến múa\n" +
            "Khi em trăn trở\n" +
            "Khùng khùng điên điên\n" +
            "Anh là chúa hề\n" +
            "Dắt e đi chơi\n" +
            "Khi có lúa về\n" +
            "Sao em cứ tìm kiếm chi xa\n" +
            "Nhà anh nó sát bên\n" +
            "Khi em buồn anh có guitar\n" +
            "Anh đàn rồi anh hát lên\n" +
            "Yêu em là phép màu may mắn\n" +
            "Yêu em vì em giống cô tiên\n" +
            "Răng em vàng khiến anh say nắng\n" +
            "Yêu anh vì anh quá vô duyên."
    musicModel21.singerId = "3"
    musicModel21.country = COUNTRY_VN
    musicModel21.topicId = arrayListOf("1", "2", "3")
    musicModel21.uploadDate = 1712719256151
    listMusics.add(musicModel21)

    val musicMode22 = MusicModel()
    musicMode22.id = "23"
    musicMode22.name = "Kết Thúc Không Vui"
    musicMode22.authorId = ""
    musicMode22.duration = 263.0
    musicMode22.url = "gs://musicplayer-c39cb.appspot.com/musics/KetThucKhongVui-ChauKhaiPhong-2746549.mp3"
    musicMode22.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/f301ea0db5dde29b5b5db4ff6953014b_1379053529.jpg"
    musicMode22.lyrics = "Đời ai không một lần biết yêu\n" +
            "Và anh cũng đã từng biết yêu\n" +
            "Người mà anh yêu chính là em\n" +
            "Người làm anh khóc cũng là em.\n" +
            "\n" +
            "Ngày em buôn hàng ngàn lý do\n" +
            "Rồi em đi chẳng cần đắn đo\n" +
            "Là ngày anh hiểu trong trái tim\n" +
            "Em chưa từng tồn tại bao giờ.\n" +
            "\n" +
            "[ĐK:]\n" +
            "Khi em đi mới biết bao năm tháng qua\n" +
            "Từng lời yêu thương em trao cho anh chỉ là lời dối gian\n" +
            "Nước mắt anh tuôn rơi, bao lần anh níu với\n" +
            "Nhưng em đã không cần đến anh nữa rồi.\n" +
            "\n" +
            "Em như mây gió cuốn mây trôi thế thôi\n" +
            "Đành để cho anh nơi đây chơi vơi sống từng ngày lẻ loi\n" +
            "Kết thúc không yên vui tim này đau nhức nhói\n" +
            "Bên tình mới em nỡ đành quên sao người ơi."
    musicMode22.singerId = "2"
    musicMode22.country = COUNTRY_VN
    musicMode22.topicId = arrayListOf("1", "2", "3")
    musicMode22.uploadDate = 1712719256151
    listMusics.add(musicMode22)

    val musicModel23 = MusicModel()
    musicModel23.id = "24"
    musicModel23.name = "Một Ngày Chẳng Nắng"
    musicModel23.authorId = ""
    musicModel23.duration = 193.0
    musicModel23.url = "gs://musicplayer-c39cb.appspot.com/musics/MotNgayChangNang-Phao-9400644.mp3"
    musicModel23.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/9b70ab0d91a617068b169149c4ada7ea.jpg"
    musicModel23.lyrics = "Một ngày chẳng nắng chẳng mưa chẳng gì\n" +
            "Ngồi lại một chút tai phone thầm thì\n" +
            "Chuyện kể về chú voi con lầm lì ngay tại Bản Đôn ohhh\n" +
            "Ngày xửa ngày xưa này thì là tránh xa ra voi này\n" +
            "\n" +
            "Voi biết voi không có ngà, voi khóc lóc với ông bà\n" +
            "Ông bà bảo là thêm vài năm nữa con sẽ khác thôi khác thôi\n" +
            "Xuân hạ đông xuân xuân xuân hạ đông\n" +
            "Qua qua qua voi voi đã lớn dần\n" +
            "\n" +
            "Chẳng hiểu sao tay chân vẫn bé tí teo\n" +
            "Chẳng biết sao nhưng voi đang đang rất buồn\n" +
            "Bạn bè nói không không không thèm chơi\n" +
            "Sông cũng không không không không thèm lội\n" +
            "\n" +
            "Voi con ta đã buồn nay còn nát hơn\n" +
            "Mong ước sao như hoa để được ngát hương\n" +
            "Tôi tên là hổ nhưng tôi không đủ ranh mãnh\n" +
            "Ăn uống thì thanh cảnh, tính không đủ lưu manh\n" +
            "\n" +
            "Còn tôi tên là thỏ tôi không ở xa lắm\n" +
            "Sống ở bên kia đồi, bên kia đồi xanh xanh\n" +
            "Voi con ơi bạn tôi sao lại trông suy thế\n" +
            "Đi theo hội mình nào, mình cùng hội chung phe\n" +
            "\n" +
            "Cho voi con tôi xin một giây suy nghĩ\n" +
            "Ok nào được rồi hội mình cùng đi thôi\n" +
            "Mình cùng hát vang ca giữa trời\n" +
            "Đầu hất nghênh ngang đôi lời\n" +
            "\n" +
            "Có chú voi con không buồn\n" +
            "Cùng chị hổ đi sau này, bạn thỏ đi mau\n" +
            "Và ta cứ bước đều bước đều bước đều\n" +
            "Phía trước ta là một cánh đồng, cánh diều bất tận\n" +
            "\n" +
            "Voi con có đang nghe thấy gì, voi con ta nghe thấy gì\n" +
            "Tiếng khóc than khóc than khóc than\n" +
            "Voi con có thấy một chú rùa bé xinh rất xinh\n" +
            "Tuy nhiên một mình thì hơi buồn, đi một mình thì hơi chậm"
    musicModel23.singerId = "5"
    musicModel23.country = COUNTRY_VN
    musicModel23.topicId = arrayListOf("1", "2", "3")
    musicModel23.uploadDate = 1712719256151
    listMusics.add(musicModel23)

    val musicModel24 = MusicModel()
    musicModel24.id = "25"
    musicModel24.name = "Kìa Bóng Dáng Ai"
    musicModel24.authorId = ""
    musicModel24.duration = 164.0
    musicModel24.url = "gs://musicplayer-c39cb.appspot.com/musics/KiaBongDangAi-Phao-8544353.mp3"
    musicModel24.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/maxresdefaultkiabongdangaivuaquaday.jpeg"
    musicModel24.lyrics = "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức cả đêm ngày cả đêm ngày\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức tôi không muốn nói là mê say .\n" +
            "Ờ thì là mình thì vẫn cứ lướt ,anh thì ngồi phía trước\n" +
            "Giọng ngoài đời chua chát ,nhưng mà khi gặp anh sẽ khác\n" +
            "Vì chỉ hát vang , làm nát tan , con tim này trở nên yếu mềm\n" +
            "Chỉ muốn nói ra hết thôi , từng phút ,từng giây , tôi yêu anh nhiều thêm.\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức cả đêm ngày cả đêm ngày\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức tôi không muốn nói là mê say .\n" +
            "Rap: Mẹ căn dặn là cần phải về nhà ,và không đường dề dà trước lúc 10 giờ tối\n" +
            "Vậy nên là ta tăng ga và tăng tốc nhưng mà em vẫn có nhiều điều để nói\n" +
            "Đưa về ngõ nhưng mà không *** vào vì mẹ anh khó nên rất khó bày tỏ\n" +
            "Ba em khó nên rất khó về khuya nên thời gian cho nhau đừng cãi nhau lia lịa.\n" +
            "Bà em bắt em phải đi ngủ sớm này em đang trùm chăn để mà chờ tin nhắt\n" +
            "Cô em bảo là đang tuổi ăn tuổi lớn cho nên mình gặp nhau thì mới là may mắn\n" +
            "Giận hờn đừng vô cớ , Lỡ không thương đừng gieo thương nhớ\n" +
            "Nắm tay nhau chẳng để va vào giai điệu vì ta biết câu chuyện này chẳng ai hiểu .\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức cả đêm ngày cả đêm ngày\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức tôi không muốn nói là mê say .\n" +
            "Nói thương nhau đến khi đầu bạc\n" +
            "Thì đừng vì nhau bạc cả mái đầu\n" +
            "Em đâu muốn đôi mình phải sầu\n" +
            "như là cực bắc phương nam trái nhau\n" +
            "Về câu chuyện mà chẳng phải đi tới đâu ..\n" +
            "Vì em không muốn bên nhau bạc mái đầu\n" +
            "Em cũng không muốn đôi mình phải sầu\n" +
            "Như là cực bắc phương nam trái nhau\n" +
            "Về câu chuyện mà chẳng phải đi tới đâu\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức cả đêm ngày cả đêm ngày\n" +
            "Kìa bóng dáng ai vụt qua đây ,vụt qua đây\n" +
            "Vì ai thao thức tôi không muốn nói là mê say"
    musicModel24.singerId = "5"
    musicModel24.country = COUNTRY_VN
    musicModel24.topicId = arrayListOf("1", "2", "3")
    musicModel24.uploadDate = 1712719256151
    listMusics.add(musicModel24)

    val musicModel25 = MusicModel()
    musicModel25.id = "26"
    musicModel25.name = "Nói Dối"
    musicModel25.authorId = ""
    musicModel25.duration = 199.0
    musicModel25.url = "gs://musicplayer-c39cb.appspot.com/musics/NoiDoi-PhaoHIEUTHUHAI-7124737.mp3"
    musicModel25.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/be7135b945a28212f6940326408bde6f.jpg"
    musicModel25.lyrics = "Ohh! Này baby anh ơi anh đang làm chi\n" +
            "Thì thâu đêm , ting ting instagram anh vẫn sáng chói\n" +
            "Mà đáng nói, là gì?\n" +
            "Là có một cô người yêu đang đợi chờ\n" +
            "Đúng là yêu vì yêu nên dại khờ\n" +
            "\n" +
            "Nói dối làm tim tan nát\n" +
            "Nói dối làm trái tim đau\n" +
            "Nhưng mà ai biết được sâu trong thâm tâm anh vẫn luôn\n" +
            "Keep it lowkey, show me\n" +
            "Là đôi dòng tin nhắn anh luôn yêu em\n" +
            "Anh thề anh hứa không yêu ai thêm\n" +
            "\n" +
            "Này baby anh ơi anh đang làm chi\n" +
            "Thì thâu đêm , ting ting instagram anh vẫn sáng chói\n" +
            "Mà đáng nói , là gì ?\n" +
            "Là có một cô người yêu đang đợi chờ\n" +
            "Đúng là yêu vì yêu nên dại khờ\n" +
            "\n" +
            "(Saxophone)\n" +
            "\n" +
            "Tình chạy tình đi rồi tình tàn\n" +
            "No flex và chill như Bình Vàng\n" +
            "Không care rằng ta sẽ đến đâu\n" +
            "Anh nghĩ rằng tôi sẽ buồn rầu\n" +
            "\n" +
            "Oh no my baby (x2)\n" +
            "\n" +
            "Đáng lẽ chị phải là người khóc cho đúng kịch bản\n" +
            "Trò chơi này là chị thầu chị là đạo diễn\n" +
            "Ai mới là kẻ thất bại trong chuyện tình cảm\n" +
            "Gục ngã đứng lên như người lính tráng\n" +
            "Giờ thì hãy cố chứng tỏ đi này real man\n" +
            "Chỉ được có thế thôi à real man\n" +
            "Tại sao anh luôn phải cố gắng chứng tỏ trong khi bản thân anh vốn không phải real man\n" +
            "Okay!! Okay!!!\n" +
            "Suốt bao nhiêu đêm không ngủ\n" +
            "Cô ta chê anh không đủ\n" +
            "Trằn trọc không ngủ\n" +
            "Bóng ai không chủ\n" +
            "Vừa đi qua, yêu kiều và bậc nhất (diva)\n" +
            "Nhớ về quá khứ và đôi ta\n" +
            "Nhớ từng kỉ niệm đã trôi xa\n" +
            "Lúc còn bội bạc và phôi pha\n" +
            "Oh no my baby\n" +
            "\n" +
            "Này baby anh ơi anh đang làm chi\n" +
            "Thì thâu đêm, ting ting instagram anh vẫn sáng chói\n" +
            "Mà đáng nói, là gì?\n" +
            "Là có một cô người yêu đang đợi chờ\n" +
            "Đúng là yêu vì yêu nên dại khờ\n" +
            "\n" +
            "Người nào biết điều\n" +
            "Sẽ không nói anh mũi dài\n" +
            "Vì chỉ cần là Thúy Kiều\n" +
            "Thì Thúc Sinh sẽ theo đuổi hoài\n" +
            "Nên là đừng ngồi trách, thế không hay đâu\n" +
            "Khi nào anh dối, đó là ta không hợp\n" +
            "Nên thôi bây giờ ra đi\n" +
            "Mau ôm mơ cho vào vali\n" +
            "Tiếng guitar kia của Chapi\n" +
            "Cũng không thể níu lại hai đường ta đi\n" +
            "Nói dối gì? vì một người tuyệt vời như anh\n" +
            "Xứng đáng được chọn nhiều người yêu\n" +
            "Nếu nói điêu là vì người anh xiêu, on the real\n" +
            "có phải là anh làm vì yêu\n" +
            "Babe em không đầu tiên, như là nguyên tố hydro\n" +
            "Càng không phải người đặc biệt như José Mourinho\n" +
            "Thì thôi ta không cần tiếp, đừng\n" +
            "Kêu anh là thằng *beep* yeah\n" +
            "Thứ duy nhất mà anh luôn nói dối đó là yêu em, you don't say\n" +
            "Baby ơi đây không phải dối lừa, it's the ugly truth\n" +
            "But here we go again, yeah, just like fruity loops\n" +
            "Thì thôi minhf không cần tiếp, yea\n" +
            "Mình còn bận vi vu, đừng kêu anh là thằng *beep* (ha)\n" +
            "Just the ugly truth!!!!\n" +
            "\n" +
            "Ohh! Này baby anh ơi anh đang làm chi\n" +
            "Thì thâu đêm, ting ting instagram anh vẫn sáng chói\n" +
            "Mà đáng nói, là gì?\n" +
            "Là có một cô người yêu đang đợi chờ\n" +
            "Đúng là yêu vì yêu nên dại khờ"
    musicModel25.singerId = "5"
    musicModel25.country = COUNTRY_VN
    musicModel25.topicId = arrayListOf("1", "2", "3")
    musicModel25.uploadDate = 1712719256151
    listMusics.add(musicModel25)

//    val musicModel100 = MusicModel()
//    musicModel100.id = ""
//    musicModel100.name = ""
//    musicModel100.authorId = ""
//    musicModel100.duration = 331.0
//    musicModel100.url = ""
//    musicModel100.imageUrl = ""
//    musicModel100.lyrics = ""
//    musicModel100.singerId = ""
//    musicModel100.country = COUNTRY_VN
//    musicModel100.topicId = arrayListOf("1", "2", "3")
//    musicModel100.uploadDate = 1712719256151
//    listMusics.add(musicModel100)

    return listMusics
}