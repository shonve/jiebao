package cn.aage.robot.web;

import cn.aage.robot.model.file.FileData;
import cn.aage.robot.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by john on 2016/11/10.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return "image/index";
    }

    @RequestMapping(value = "/images", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List images(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        List<FileData> list = imageService.getImages();
        return list;
    }

}
