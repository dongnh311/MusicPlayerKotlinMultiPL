package model

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

    @Transient
    var singerModel: SingerModel? = SingerModel()
}

// Dummy data
fun createListDummyMusics() : MutableList<MusicModel> {
    val listMusics = mutableListOf<MusicModel>()

    val musicModel = MusicModel()
    musicModel.id = "134245"
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
    musicModel.singerId = "12312"
    listMusics.add(musicModel)

    val musicModel1 = MusicModel()
    musicModel1.id = "544345"
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
    musicModel1.singerId = "345345"
    listMusics.add(musicModel1)

    val musicModel2 = MusicModel()
    musicModel2.id = "56434"
    musicModel2.name = "Lệ Lưu Ly"
    musicModel2.authorId = ""
    musicModel2.duration = 210.0
    musicModel2.url = "gs://musicplayer-c39cb.appspot.com/musics/NgamHoaLeRoiCover-HoaVinh-5404345.mp3"
    musicModel2.imageUrl = "gs://musicplayer-c39cb.appspot.com/thumbnails/maxresdefault.jpg"
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
    musicModel2.singerId = "3453123"
    listMusics.add(musicModel2)

    return listMusics
}