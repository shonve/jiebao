package cn.aage.robot.web;

import cn.aage.robot.service.CookService;
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
@RequestMapping("/cook")
public class CookController {

    @Autowired
    private CookService cookService;

    @RequestMapping(value = "/qq", method = {RequestMethod.POST, RequestMethod.GET})
    public void qq(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        cookService.saveCookBook(name);

    }

    @RequestMapping(value = "/label", method = {RequestMethod.POST, RequestMethod.GET})
    public void label(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        cookService.saveStyle1();

    }
}
