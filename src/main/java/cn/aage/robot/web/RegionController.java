package cn.aage.robot.web;

import cn.aage.robot.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shonve on 2016/10/23.
 */
@Controller
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/qq", method = {RequestMethod.POST, RequestMethod.GET})
    public void qq(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        regionService.saveRegion();
    }
}
