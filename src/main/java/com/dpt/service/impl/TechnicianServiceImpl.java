package com.dpt.service.impl;

import com.dpt.mapper.TechnicianMapper;
import com.dpt.model.Technician;
import com.dpt.service.TechnicianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("technicianService")
public class TechnicianServiceImpl implements TechnicianService {

    private final static Logger logger = LoggerFactory.getLogger(TechnicianServiceImpl.class);

    @Autowired
    private TechnicianMapper technicianMapper;

    @Override
    public int insertTechnician(Technician technician) throws Exception {
        try {
            Technician t = new Technician();
            if(technician.getOpenId() != null){
                t.setOpenId(technician.getOpenId());
            }
            if(technician.getRealname() != null){
                t.setRealname(technician.getRealname());
            }
            if(technician.getAvatar() != null){
                t.setAvatar(technician.getAvatar());
            }
            if(technician.getMobile() != null){
                t.setMobile(technician.getMobile());
            }
            t.setLevel(1);
            if(technician.getExperience() != null){
                t.setExperience(technician.getExperience());
            }
            t.setSource(0);
            if(technician.getAdept() != null){
                t.setAdept(technician.getAdept());
            }
            if (technician.getProvince() != null){
                t.setProvince(technician.getProvince());
            }
            if(technician.getCity() != null){
                t.setCity(technician.getCity());
            }
            if(technician.getDistrict() != null){
                t.setDistrict(technician.getDistrict());
            }
            t.setVerify(1);
            if(technician.getShopName() != null){
                t.setShopName(technician.getShopName());
            }
            if(technician.getReferee() != null){
                t.setReferee(technician.getReferee());
            }
            t.setAmount(0.0);
            Long seconds = System.currentTimeMillis() / 1000;
            t.setCreatedTime(seconds);
            t.setUpdatedTime(seconds);
            this.technicianMapper.insert(t);
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return 0;
        }
    }

    @Override
    public Technician getTechnicianById(int id) {
        return this.technicianMapper.getTechnicianById(id);
    }

    @Override
    public List<Technician> getTechnicians() {
        return this.technicianMapper.getTechnicians();
    }

    @Override
    public List<Technician> getTechniciansOfRecommend() {
        return this.technicianMapper.getTechniciansOfRecommend();
    }

    @Override
    public List<Technician> getTechniciansByInviteCodeRecommend(String referee) throws Exception {
        List<Technician> technicianList = this.technicianMapper.getTechniciansByInviteCodeRecommend(referee);
        return technicianList;
    }

    @Override
    public Technician getVerify(String openId) {
        Technician technician = this.technicianMapper.getVerify(openId);
        return technician;
    }

    @Override
    public int updateTechnician(Technician technician) throws Exception {
        try {
            this.technicianMapper.updateTechnician(technician);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return 0;
        }

    }
}
