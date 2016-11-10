package cn.aage.robot.model.file

import javax.persistence.*

/**
 * Created by john on 2016/11/9.
 */
@Entity
@Table(name = "file_data")
class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    String remark;
    /**
     * 描述
     */
    @Column(name = "description")
    String description;
    /**
     * 名称
     */
    @Column(name = "name")
    String name;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    String fileName;
    /**
     * 文件路径
     */
    @Column(name = "path")
    String path;
    /**
     * 文件路径
     */
    @Column(name = "url")
    String url;
    /**
     * 文件路径
     */
    @Column(name = "source_url")
    String sourceUrl;
    /**
     * 文件cdn路径
     */
    @Column(name = "cdn_url")
    String cdnUrl;
    /**
     * 文件类型
     */
    @Column(name = "mime_type")
    String mimeType;
    /**
     * 文件hash值
     */
    @Column(name = "hash")
    String hash;
    /**
     * 文件大小
     */
    @Column(name = "file_size")
    Double fileSize;
}
