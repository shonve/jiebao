package cn.aage.robot.service;

import cn.aage.robot.repository.file.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by john on 2016/11/10.
 */
@Service("imageService")
public class ImageService {
    @Autowired
    private FileDataRepository fileDataRepository;
}
