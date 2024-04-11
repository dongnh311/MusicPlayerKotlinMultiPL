package model

import kotlinx.serialization.Serializable

/**
 * Project : MusicPlayerKotlinMultiPL
 * Created by DongNH on 10/4/24.
 * Email : hoaidongit5@gmail.com or hoaidongit5@dnkinno.com.
 * Phone : +84397199197.
 */

@Serializable
class TopicModel {
    var id : String = ""
    var name : String = ""
    var urlImage : String = ""
}

// Dummy for topic
fun createListDummyTopicModel() : MutableList<TopicModel> {
    val listTopics = mutableListOf<TopicModel>()

    val topic = TopicModel()
    topic.id = "1"
    topic.name = "ToDay"
    topic.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/09/16/1/c/e/0/1600239007932_org.jpg"
    listTopics.add(topic)

    val topic1 = TopicModel()
    topic1.id = "2"
    topic1.name = "Singers"
    topic1.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/09/03/4/5/9/8/1599120572819_org.jpg"
    listTopics.add(topic1)

    val topic2 = TopicModel()
    topic2.id = "3"
    topic2.name = "Travel"
    topic2.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/06/17/7/d/8/9/1592361216469_org.jpg"
    listTopics.add(topic2)

    val topic3 = TopicModel()
    topic3.id = "4"
    topic3.name = "Gaming"
    topic3.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2022/12/05/7/0/8/c/1670221887775_org.jpg"
    listTopics.add(topic3)

    val topic4 = TopicModel()
    topic4.id = "5"
    topic4.name = "Classic"
    topic4.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/09/11/4/f/0/7/1599819318161_org.jpg"
    listTopics.add(topic4)

    val topic5 = TopicModel()
    topic5.id = "6"
    topic5.name = "Chill"
    topic5.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2021/03/09/2/9/3/f/1615284927743_org.jpg"
    listTopics.add(topic5)

    val topic6 = TopicModel()
    topic6.id = "7"
    topic6.name = "Piano"
    topic6.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2022/11/24/6/e/2/d/1669262775156_org.jpg"
    listTopics.add(topic6)

    val topic7 = TopicModel()
    topic7.id = "8"
    topic7.name = "Lofi"
    topic7.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/06/15/b/1/e/6/1592214676121_org.jpg"
    listTopics.add(topic7)

    val topic8 = TopicModel()
    topic8.id = "9"
    topic8.name = "EDM"
    topic8.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/09/03/4/5/9/8/1599120670684_org.jpg"
    listTopics.add(topic8)

    val topic9 = TopicModel()
    topic9.id = "10"
    topic9.name = "Acoustic"
    topic9.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/08/13/f/d/9/1/1597291906852_org.jpg"
    listTopics.add(topic9)

    val topic10 = TopicModel()
    topic10.id = "11"
    topic10.name = "Dance"
    topic10.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/06/22/9/7/b/e/1592812587983_org.jpg"
    listTopics.add(topic10)

    val topic11 = TopicModel()
    topic11.id = "12"
    topic11.name = "Jazz"
    topic11.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/06/09/4/a/0/a/1591687218172_org.jpg"
    listTopics.add(topic11)

    val topic12 = TopicModel()
    topic12.id = "13"
    topic12.name = "DJ"
    topic12.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/06/18/9/5/f/7/1592468690285_org.jpg"
    listTopics.add(topic12)

    val topic13 = TopicModel()
    topic13.id = "14"
    topic13.name = "Driving"
    topic13.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2021/04/23/a/e/1/8/1619168953805_org.jpg"
    listTopics.add(topic13)

    val topic14 = TopicModel()
    topic14.id = "15"
    topic14.name = "Anime"
    topic14.urlImage = "https://avatar-ex-swe.nixcdn.com/topic/thumb/2020/06/18/9/5/f/7/1592451702890_org.jpg"
    listTopics.add(topic14)

    return listTopics
}