package cn.aage.robot.service;

import cn.aage.robot.repository.region.CountryRepository;
import cn.aage.robot.repository.region.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shonve on 2016/10/23.
 */
@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CountryRepository countryRepository;

    public void saveRegion() {
    }
}
