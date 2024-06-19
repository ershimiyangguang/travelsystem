package cn.ag.web.servlet;

import cn.ag.util.AlipayUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AlipayServlet")
public class BuyServlet extends HttpServlet {
    private String message;

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int price = Integer.parseInt(request.getParameter("price"));
        String pay = AlipayUtils.pay("订单", (double) price);
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write(pay);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}