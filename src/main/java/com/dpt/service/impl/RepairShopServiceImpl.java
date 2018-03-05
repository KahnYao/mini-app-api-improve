package com.dpt.service.impl;

import com.dpt.mapper.RepairShopMapper;
import com.dpt.model.City;
import com.dpt.model.Region;
import com.dpt.model.RepairShop;
import com.dpt.service.CityService;
import com.dpt.service.RegionService;
import com.dpt.service.RepairShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("repairShopService")
public class RepairShopServiceImpl implements RepairShopService {

    private final static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private RepairShopMapper repairShopMapper;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CityService cityService;

    @Override
    public List<RepairShop> getShopsOfRecommend() throws Exception {
        List<RepairShop> repairShopList = this.repairShopMapper.getShopsOfRecommend();
        return repairShopList;
    }

    @Override
    public int insertShop(RepairShop repairShop) throws Exception {
        try {
            RepairShop repairShop1 = new RepairShop();
            if (repairShop.getName() != null){
                repairShop1.setName(repairShop.getName());
            }
            if(repairShop.getContact() != null){
                repairShop1.setContact(repairShop.getContact());
            }
            if(repairShop.getMobile() != null){
                repairShop1.setMobile(repairShop.getMobile());
            }
            if(repairShop.getEmail() != null){
                repairShop1.setEmail(repairShop.getEmail());
            }
            if (repairShop.getProvinceName() != null){
                repairShop1.setProvinceName(repairShop.getProvinceName());
            }
            if(repairShop.getCityName() != null){
                repairShop1.setCityName(repairShop.getCityName());
            }
            if(repairShop.getDistictName() != null){
                repairShop1.setDistictName(repairShop.getDistictName());
            }
            Integer cityId = 0;
            if (repairShop.getProvinceName() != null && repairShop.getCityName() != null && repairShop.getDistictName() != null){
                String provinceName = repairShop.getProvinceName().replace("省","").replace("市","").replace("维吾尔自治区","").replace("自治区","");
                String cityName = repairShop.getCityName();
                if (cityName.length()>2){
                    cityName = cityName.replace("市","").replace("地区","").replace("回族自治州","");
                }
                String districtName = repairShop.getDistictName();
                List<Region> regionList = regionService.getIdByProvinceName(provinceName);
                if(regionList.size() >0){
                    Integer provinceId = regionList.get(0).getId();
                    repairShop1.setProvince(provinceId);
                    List<Region> cityList = regionService.getCityIdByNameAndParentId(cityName,provinceId);
                    cityId = cityList.get(0).getId();
                    repairShop1.setCity(cityId);
                    List<Region> districtList = regionService.getCityIdByNameAndParentId(districtName,cityId);
                    Integer district = districtList.get(0).getId();
                    repairShop1.setDistict(district);
                }

            }

            //获取门店编码
            if (cityId != 0){
                String code = getRepairShopCode(cityId);
                repairShop1.setCode(code);
            }
            repairShop1.setSource(1);
            repairShop1.setAmount(0);
            repairShop1.setIsrz(0);
            repairShop1.setChannel(1);
            if(repairShop.getAddress() != null){
                repairShop1.setAddress(repairShop.getAddress());
            }
            if(repairShop.getLicenceImg() != null){
                repairShop1.setLicenceImg(repairShop.getLicenceImg());
            }
            if(repairShop.getPosition() != null){
                repairShop1.setPosition(repairShop.getPosition());
            }
            if(repairShop.getTjperson() != null){
                repairShop1.setTjperson(repairShop.getTjperson());
            }
            if(repairShop.getMendianHead() != null){
                repairShop1.setMendianHead(repairShop.getMendianHead());
            }
            if (repairShop.getMendianView() != null){
                repairShop1.setMendianView(repairShop.getMendianView());
            }
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            repairShop1.setCreateDate(timestamp);
            repairShop1.setModifyDate(timestamp);
            this.repairShopMapper.insert(repairShop1);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------------------ERROR");
            return 0;
        }
    }

    @Override
    public List<RepairShop> getShopsByOpenIdRecommend(String referee) throws Exception {
        List<RepairShop> repairShopList = this.repairShopMapper.getShopsByOpenIdRecommend(referee);
        return repairShopList;
    }

    @Override
    public List<RepairShop> getCodeByRegionId(Integer city) throws Exception {
        List<RepairShop> list = this.repairShopMapper.getCodeByCityId(city);
        return list;
    }

    @Override
    public RepairShop getIsrz(String tjperson) throws Exception {
        RepairShop shop = this.repairShopMapper.getIsrz(tjperson);
        return shop;
    }


    public String getRepairShopCode(Integer cityId) throws Exception{
        String tmp = "";
        String cityCodeInfo = getCityCodeInfo(cityId);
        Map houThreeNumInfoMap = getHouThreeNumInfo(cityId);

        if (houThreeNumInfoMap.get("flag").equals(false)) {
            tmp = "6" + cityCodeInfo + houThreeNumInfoMap.get("houThreeNum");
        } else {
            tmp = houThreeNumInfoMap.get("houThreeNum").toString();
        }

        return tmp;
    }

    /* 查找出城市对应的编码 */
    public String getCityCodeInfo(Integer cityId) throws Exception{

        City city = this.cityService.getCityByRegionId(cityId);
        String cityRegionCode = city.getCode().toString();

        if (cityRegionCode.length() == 2) {
            cityRegionCode = "0" + cityRegionCode;
        }
        if (cityRegionCode.length() == 4) {
            cityRegionCode = cityRegionCode.substring(1, cityRegionCode.length());
        }
        return cityRegionCode;
    }


    /*
     * 处理整个编码的后三位
     *
     * 修改备注如下：门店之前考虑不周全，现在改为9位数 规则：6+三位城市区号+5位按顺序增长的数
     */
    @SuppressWarnings("rawtypes")
    public Map getHouThreeNumInfo(Integer cityId) throws Exception{

        String valStr = "";
        boolean flag = false;
        BigDecimal sumCodeVal = BigDecimal.ZERO;
        List<RepairShop> repairShopList = getCodeByRegionId(cityId);
        if (repairShopList.size() > 0) {
            RepairShop tmpObj = repairShopList.get(0);
            // 已经存在了 加 1
            String repairShopCode = tmpObj.getCode().toString();
            if (repairShopCode.length() == 7) {
                String fiveNO = repairShopCode.substring(4, repairShopCode.length());
                if (fiveNO.equals("999")) {
                    valStr = "00001";
                    flag = false;
                } else {
                    sumCodeVal = new BigDecimal(repairShopCode).add(new BigDecimal("1"));
                    valStr = sumCodeVal.toString();
                    flag = true;
                }
            } else {
                sumCodeVal = new BigDecimal(repairShopCode).add(new BigDecimal("1"));
                valStr = sumCodeVal.toString();
                flag = true;
            }
        }
        else {
            valStr = "00001";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("houThreeNum", valStr);
        map.put("flag", flag);

        return map;
    }




}
