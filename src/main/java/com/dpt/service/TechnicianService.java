package com.dpt.service;

import com.dpt.model.Technician;

import java.util.List;

public interface TechnicianService {

    public int insertTechnician(Technician technician) throws Exception;

    public Technician getTechnicianById(int id);

    public List<Technician> getTechnicians();

    public List<Technician> getTechniciansOfRecommend();

    public List<Technician> getTechniciansByInviteCodeRecommend(String referee) throws Exception;

    public Technician getVerify(String referee);

    public int updateTechnician(Technician technician) throws Exception;

}
