package cn.aage.robot.model.cook

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */

@Entity
@Table(name = "cook_album")
class CookAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;

    /**
     * 关联的食谱单
     */
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    CookBook cookBook;

    /**
     * 图片url
     */
    @Column(name = "image_url", nullable = false)
    String imageUrl;

}
