package cn.mldn.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author 作者 Jackson Tang
* @version 创建时间：2022年11月15日 上午9:01:11 
* 类说明:使用Servlet生成一个验证码，来自CSDN
* 不足：验证码图片太大
*/
@WebServlet("/code")
public class VerifyCodeImg extends HttpServlet{
	private static final int WIDTH = 200;//设置验证码图片宽度
    private static final int HEIGHT = 60;//设置验证码图片高度
    private static final int LENGTH = 4;//设置验证码长度
    public static final int LINECOUNT = 20;//干扰线的数目
 
    //验证码的字符库
    private static final String str = "023456789" +
            "ABCDEFGHJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";
 
    //通过随机数取字符库中的字符组合成4位验证码
    private static Random random = new Random();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置数据类型为图片
        resp.setContentType("image/jpeg");
 
        //设置不进行缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setHeader("expires", "0");
 
 
        //获取画笔
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
 
        //设置背景颜色并绘制矩形背景
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
 
        //验证码的绘制
        String code = drawChar(g);
//        System.out.println("验证码:" + code);
 
 
        //随机线的绘制
        for (int i = 0; i < LINECOUNT; i++) {
            drawLine(g);
        }
 
        //在session中存入当前的code码，便于验证
        req.getSession().setAttribute("code", code);
 
        //绘制图片
        g.dispose();
 
        //将图片输出到response中
        ImageIO.write(image, "JPEG", resp.getOutputStream());
    }
 
 
    //获取不同颜色
    public Color getColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
 
    //获取字体样式
    public Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 50);
    }
 
    //绘制字符
    public String drawChar(Graphics g) {
        String code = "";
        g.setFont(getFont());
        for (int i = 0; i < LENGTH; i++) {
            char c = str.charAt(random.nextInt(str.length()));
            g.setColor(getColor());
            g.drawString(c + "", 50 * i + 12, 50);
            code = code + c;
        }
        return code;
    }
 
    //绘制随机线
    public void drawLine(Graphics g) {
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.setColor(getColor());
        g.drawLine(x, y, x + xl, y + yl);
    }

}
