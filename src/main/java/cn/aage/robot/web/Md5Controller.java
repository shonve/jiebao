package cn.aage.robot.web;

import cn.aage.robot.service.Md5Service;
import cn.aage.robot.service.QQService;
import cn.aage.robot.util.BaoUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shonve on 2016/10/21.
 */
@Controller
@RequestMapping("/md5")
public class Md5Controller {
    private static final Logger LOGGER = Logger.getLogger(Md5Controller.class.getName());

    @Autowired
    private Md5Service md5Service;

    @RequestMapping(value = "/generate", method = {RequestMethod.POST, RequestMethod.GET})
    public void qq(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        md5Service.generateMd5();
    }



}
