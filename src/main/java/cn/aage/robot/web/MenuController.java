package cn.aage.robot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shonve on 2016/10/27.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping(value = "/cook", method = {RequestMethod.POST, RequestMethod.GET})
    public String cook(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return "test/qq";

    }
}
