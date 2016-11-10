package cn.aage.robot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by john on 2016/11/8.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return "index";

    }
    @RequestMapping(value = "/cooleditor", method = {RequestMethod.POST, RequestMethod.GET})
    public String cooleditor(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return "cooleditor";

    }
}
