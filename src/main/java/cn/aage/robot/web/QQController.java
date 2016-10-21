package cn.aage.robot.web;

import cn.aage.robot.service.QQService;
import cn.aage.robot.util.BaoUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Created by john on 2016/8/25.
 */
@Controller
@RequestMapping("/qq")
public class QQController {
    private static final Logger LOGGER = Logger.getLogger(QQController.class);

    @Autowired
    public QQService qqService;

    @RequestMapping(value = "/qq", method = {RequestMethod.POST, RequestMethod.GET})
    public void qq(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final String key = BaoUtil.getString("qq.bot.key");
        if (!key.equals(request.getParameter("key"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String msg = request.getParameter("msg");
        if (StringUtils.isBlank(msg)) {
            LOGGER.warn("Empty msg body");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        LOGGER.info("Push QQ groups [msg=" + msg + "]");
        qqService.sendToPushQQGroups(msg);
    }

    @RequestMapping(value = "/init", method = {RequestMethod.POST, RequestMethod.GET})
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (final Exception e) {
                    LOGGER.error(e.getMessage());
                }
                qqService.initQQClient();
            }
        }).start();

    }

    @RequestMapping(value = "/show", method = {RequestMethod.POST, RequestMethod.GET})
    public void show(final HttpServletRequest request, final HttpServletResponse response) {
        response.addHeader("Cache-Control", "no-store");

        OutputStream output = null;
        try {
            final String filePath = new File("qrcode.png").getCanonicalPath();
            final byte[] data = IOUtils.toByteArray(new FileInputStream(filePath));

            output = response.getOutputStream();
            IOUtils.write(data, output);
            output.flush();
        } catch (final Exception e) {
            LOGGER.error("在线显示二维码图片异常", e);
        } finally {
            IOUtils.closeQuietly(output);
        }

    }
}
