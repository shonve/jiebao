package cn.aage.robot.service;

import cn.aage.robot.model.region.Country;
import cn.aage.robot.model.region.Re;
import cn.aage.robot.model.region.Region;
import cn.aage.robot.repository.region.CountryRepository;
import cn.aage.robot.repository.region.ReRepository;
import cn.aage.robot.repository.region.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shonve on 2016/10/23.
 */
@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ReRepository reRepository;

    public void saveRegion() {
//        List<Country> countrys = countryRepository.findAll();
        List<Re> Res = reRepository.findByLevel("4");
        List<Re> cccc=new ArrayList<>();
        List<Region> cc = regionRepository.findByLevel(4);
        for(Re e : Res){
            boolean flag=true;
            for(Region c : cc){
                if(c.getTemp().equals(e.getCode())){
                    flag=false;
                }
            }
            if(flag){
                cccc.add(e);
            }
        }
        System.out.println("-----数量：" + cccc.size());


//        List<Region> rs = new ArrayList<>();
//
//        for (Country c : countrys) {
//            for (Re e : Res) {
//                if (null != e.getLevel() && e.getLevel().equals("4")) {
//                    for (Region reg : cc) {
//                        if(reg.getTemp().equals(e.getUpper_region())){
//                            System.out.println("-----添加：" + e.getRegion_name_c());
//                            if (e.getCountry_code().equals(c.getCode())) {
//                                Region region = new Region();
//                                region.setCountry(c);
//                                region.setName(e.getRegion_name_c());
//                                region.setLevel(Integer.valueOf(e.getLevel()));
//                                region.setFullName(e.getRegion_name_e());
//                                region.setTemp(e.getCode());
//                                region.setParent(reg);
//                                rs.add(region);
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//        regionRepository.save(rs);
    }
}
