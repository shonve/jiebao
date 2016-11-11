package cn.aage.robot.service;

import cn.aage.robot.model.file.FileData;
import cn.aage.robot.repository.file.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by john on 2016/11/10.
 */
@Service("imageService")
public class ImageService {
    @Autowired
    private FileDataRepository fileDataRepository;

    public List<FileData> getImages() {
        return fileDataRepository.findAll();
    }
}
