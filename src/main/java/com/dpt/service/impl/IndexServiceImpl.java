package com.dpt.service.impl;

import com.dpt.model.RepairShop;
import com.dpt.model.Technician;
import com.dpt.model.User;
import com.dpt.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

    private final static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Autowired
    private RepairShopService repairShopService;

    @Autowired
    private UserService userService;

    @Autowired
    private TechnicianService technicianService;

    public HashMap<String, Object> getIndex() throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            List<RepairShop> shops = repairShopService.getShopsOfRecommend();
            if (shops.size() >0){
                String NickName = null;
                for (int i = 0;i<shops.size();i++) {
                    String openId = shops.get(i).getTjperson();
                    String[] image = shops.get(i).getMendianHead() != null ?shops.get(i).getMendianHead().toString().split(","):null;
                    if (image != null && !"".equals(image)){
                        for(int k=0;k<image.length;k++){
                            shops.get(i).setMendianHead(image[k]);
                        }
                    }
                    List<User> user =  userService.getUserBonus(openId);
                    if (user != null){
                        for (int j = 0;j<user.size();j++){
                            shops.get(i).setTjperson(user.get(j).getNickName());
                        }
                    }
                }
            }
            hashMap.put("shops", shops);
            List<Technician> technicians = technicianService.getTechniciansOfRecommend();
            if (technicians.size() >0){
                for (int i = 0;i<technicians.size();i++){
                    if (technicians.get(i).getRealname() == null ||"".equals(technicians.get(i).getRealname())) {
                        String inviteCode = technicians.get(i).getReferee();
                        User user =  userService.getUserByInviteCode(inviteCode);
                        if (user != null){
                            technicians.get(i).setRealname(user.getNickName());
                            String openId = technicians.get(i).getOpenId();
                            User users =  userService.getUserByOpenId(openId);
                            if (users != null){
                                String[] image = users.getAvatarUrl() != null ? users.getAvatarUrl().split(","):null;
                                if (image != null && !"".equals(image)){
                                    for(int k=0;k<image.length;k++){
                                        technicians.get(i).setAvatar(image[k]);
                                    }
                                }
                            }

                        }
                    } else {
                        String openId = technicians.get(i).getOpenId();
                        User user =  userService.getUserByOpenId(openId);
                        if (user != null){
                            String[] image = user.getAvatarUrl() != null ? user.getAvatarUrl().split(","):null;
                            if (image != null && !"".equals(image)){
                                for(int k=0;k<image.length;k++){
                                    technicians.get(i).setAvatar(image[k]);
                                }
                            }

                        }
                    }

                }
            }

            hashMap.put("technicians", technicians);
            return hashMap;
        } catch (Exception e) {
            logger.error("----------------------------------ERROR");
            return hashMap;
        }
    }

}
