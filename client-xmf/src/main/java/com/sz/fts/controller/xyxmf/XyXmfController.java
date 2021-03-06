package com.sz.fts.controller.xyxmf;


import com.sz.fts.service.xyxmf.XyXmfService;
import com.sz.fts.utils.BaseRquestAndResponse;
import com.sz.fts.utils.CommonUtil;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;

/**
 * @author 征华兴
 * @date 上午 11:03  2018/5/11 0011
 * @Copyright 江苏鸿信系统集成有限公司 All rights reserved
 */

@Controller
@RequestMapping(value = "/xyXmf")
public class XyXmfController extends BaseRquestAndResponse {

    @Autowired
    private XyXmfService xyXmfService;

    private static final Logger logger = LogManager.getLogger(XyXmfController.class);


    /**
     * 获取openId
     *
     * @return
     */
    @RequestMapping(value = "/getOpenId.do")
    public ModelAndView getOpenid(@RequestParam String openId) {
        String URL = "http://app1.118114sz.com/active/shareMfXiaoYuan/pages/tixian.html?llbOpenId=" + openId;
        System.out.println("===========" + URL);
        String jumpURL = "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx220ef99f7d84059b&redirect_uri=http%3a%2f%2fquanzi.118114sz.com%2fwechat%2ffindOpenid%3ft%3d7%26u%3d" +
                URLEncoder.encode(URL) + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        System.out.println("===========" + jumpURL);
        //跳转路径
        return new ModelAndView(jumpURL);
    }

    /**
     * 入口，通过openid获取用户信息
     */
    @RequestMapping("getUserInfo.do")
    public void getUserInfo() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        logger.info("--------------校园小蜜蜂OpenId-----------：" + jsonObject);
        JSONObject out = this.xyXmfService.getUserInfo(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 通过手机号码，查询校园套餐计划
     */

    @RequestMapping("queryTaocanByMobile.do")
    public void queryTaocanByMobile() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = this.xyXmfService.queryTaocanByMobile(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 通过openid,status
     * 查询用户进行中，全部的订单信息
     */
    @RequestMapping("queryProcessOrder.do")
    public void queryProcessOrder() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = this.xyXmfService.getProcessOrder(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 通过openid  查询订单审核完成信息
     *
     * @param
     * @return
     * @throws
     */
    @RequestMapping("querySuccessOrderList.do")
    public void queryOrderList() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = this.xyXmfService.querySuccessOrderList(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }


    /**
     * 小蜜蜂提现
     */
    @RequestMapping("payMoney.do")
    public void payMoney() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        logger.info("********传入参数*******" + jsonObject);
        JSONObject out = this.xyXmfService.payMoney(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 提现记录
     */
    @RequestMapping("queryTxInfo.do")
    public void queryTxInfo() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = this.xyXmfService.queryTxInfo(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 点击套餐 查询套餐内容
     */
    @RequestMapping(value = "/description.do")
    public void queryTaocanContent() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = this.xyXmfService.taocanInfo(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 通过套餐Id提交订单
     *
     * @param
     */
    @RequestMapping(value = "/saveXmfByTaocan.do")
    public void saveInfoByTaocan() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        logger.info("--------------小蜜蜂传入参数-----------：" + jsonObject);
        JSONObject out = this.xyXmfService.holdInfoByTaocan(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    // 发送验证码
    @RequestMapping(value = "sendCode.do")
    public void sendCode() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = xyXmfService.sendCode(jsonObject.toString(), getRequest());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }

    /**
     * 通过用户手机号，
     * 查询用户订单情况
     */

    @RequestMapping("queryOrder.do")
    public void queryOrder() throws Exception {
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = CommonUtil.outDecryptMsg(getRequest());
        JSONObject out = xyXmfService.queryOrderByMobile(jsonObject.toString());
        CommonUtil.printEncryptMsg(getRequest(), getResponse(), out);
    }
}

