package cn.bite.travel.web.servlet;

import cn.bite.travel.domain.ResultInfo;
import cn.bite.travel.domain.User;
import cn.bite.travel.service.UserService;
import cn.bite.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "RegistUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service方法完成注册功能
        UserService userService=new UserServiceImpl();
        boolean flag=userService.regist(user);
        ResultInfo resultInfo=new ResultInfo();
        //4.响应结果
        if(flag){
            resultInfo.setFlag(true);

        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败！");

        }
        //将info对象序列化为json对象
        ObjectMapper objectMapper =new ObjectMapper();
        String json=objectMapper.writeValueAsString(objectMapper);

        //将json对象写回客户端  设置字符格式
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
