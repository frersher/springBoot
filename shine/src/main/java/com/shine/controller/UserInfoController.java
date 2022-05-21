package com.shine.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shine.basic.rep.UserQuery;
import com.shine.basic.rsp.PageResponse;
import com.shine.model.UserInfo;
import com.shine.service.FileSystemService;
import com.shine.service.StrategyMain;
import com.shine.service.UserService;
import com.sun.crypto.provider.DESKeyFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 *
 * @author wb-cb236432
 * @create 2018-08-02 15:08
 **/
@Controller
@RequestMapping("/user/*")
public class UserInfoController {

  private static final Logger logger = LogManager.getLogger(UserInfoController.class);
  @Resource
  private UserService userService;
  @Resource
  private List<StrategyMain> strategys;
  @Resource
  private FileSystemService fileSystemService;


  @GetMapping("/login")
  public String login(String username, String password) throws Exception {
    fileSystemService.downloadFile();
    return "login";
  }


  @RequestMapping("/loginPost")
  public String loginPost(UserQuery query) {
    // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟 shiro 的拦截地址内．不然后会报空指针
//        Subject sub = SecurityUtils.getSubject();
//        // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
//        // 认证执行者交由 com.battcn.config.AuthRealm 中 doGetAuthenticationInfo 处理
//        // 当以上认证成功后会向下执行,认证失败会抛出异常
//        UsernamePasswordToken token = new UsernamePasswordToken(query.getUserNick(), query.getUserPassword());
//        try {
//            sub.login(token);
//        } catch (UnknownAccountException e) {
//            logger.error("对用户[{}]进行登录验证,验证未通过,用户不存在", query.getUserNick());
//            System.out.println(String.format("对用户[{}]进行登录验证,验证未通过,用户不存在 userName = %s", query.getUserNick()));
//            token.clear();
//            return "login";
//        } catch (LockedAccountException lae) {
//            logger.error("对用户[{}]进行登录验证,验证未通过,账户已锁定", query.getUserNick());
//            System.out.println(String.format("对用户[{}]进行登录验证,验证未通过,账户已锁定 userName = %s", query.getUserNick()));
//            token.clear();
//            return "login";
//        } catch (ExcessiveAttemptsException e) {
//            logger.error("对用户[{}]进行登录验证,验证未通过,错误次数过多", query.getUserNick());
//            System.out.println(String.format("对用户[{}]进行登录验证,验证未通过,错误次数过多 userName = %s", query.getUserNick()));
//            token.clear();
//            return "login";
//        } catch (AuthenticationException e) {
//            logger.error("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下", query.getUserNick(), e);
//            System.out.println(String.format("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下 userName = %s", query.getUserNick()));
//
//            token.clear();
//            return "login";
//        }
    return "index";
  }


  @RequestMapping(value = "/logout")
  public String logout() {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "login";
  }

  @RequestMapping("/queryPage")
  @ResponseBody
  public Map<String, Object> queryPage(UserQuery query) {
//        System.out.println(String.format("pageNum = %s,pageSize = %s",query.getPageNum(),query.getPageSize()));

    PageHelper.startPage(query.getPageNum(), query.getPageSize());
    Page<UserInfo> userList = (Page<UserInfo>) userService.userListByCondition(query);
    Map resultMap = new HashMap<String, Object>();

    resultMap.put("data", userList.getResult());
    //页数信息配置
    resultMap.put("limit", userList.getPageSize());
    resultMap.put("page", userList.getPageSize());
    resultMap.put("total", userList.getTotal());
    // resultMap.put("iTotalDisplayRecords",userList.getTotal());
    return resultMap;
  }


  @RequestMapping("/queryPageUser")
  public PageResponse<List<UserInfo>> queryPageUser(UserQuery query) {
    PageResponse<List<UserInfo>> response = new PageResponse();
    UserQuery userQuery = new UserQuery();
    PageHelper.startPage(query.getPageNum(), query.getPageSize());
    Page<UserInfo> userList = (Page<UserInfo>) userService.userListByCondition(userQuery);
    response.setCount(userList.getTotal());
    response.setPageNum(userList.getPageNum());
    response.setPages(userList.getPages());
    response.setData(userList.getResult());
    return response;
  }


  public static void main(String[] args) {
//    int[] nums = new int[]{-2, 0, 0, 2, 2};
//    System.out.println(JSON.toJSONString(threeNum(nums)));

    System.out.println(String.class.getClassLoader());
    System.out.println(DESKeyFactory.class.getClassLoader().getClass().getName());

  }

  private static List<List<Integer>> threeNum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    int len = nums.length;
    // 边界条件判断
    if (len < 3) {
      return res;
    }
    // 先将数组排序
    Arrays.sort(nums);
    // 变量数组
    for (int i = 0; i < len; i++) {
      // 如果有重复的元素，跳过（不跳过的话可能出现重复的3个数和为0的情况）
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      // 定义两个指针
      int l = i + 1, r = len - 1;
      // 左指针必须小于右指针
      while (l < r) {
        // 如果第一个元素大于0了三个数之和不可能为0（因为素组已经排序好了）
        if (nums[i] > 0) {
          break;
        }
        // 求和
        int sum = nums[i] + nums[l] + nums[r];
        // 如果和为0，存到结果集中，并且左右指针各自移动一位
        if (sum == 0) {
          res.add(Arrays.asList(nums[i], nums[l], nums[r]));
          while (l < r && nums[l] == nums[l + 1]) {
            l++;
          }
          while (l < r && nums[r] == nums[r - 1]) {
            r--;
          }
          l++;
          r--;
        } else if (sum < 0) {
          // 如果和小于0 左指针移动；相反右指针移动
          l++;
        } else {
          r--;
        }
      }
    }
    return res;
  }


}
