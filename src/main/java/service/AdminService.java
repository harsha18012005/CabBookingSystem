package service;

import cab.demo.cab;
import cab.demo.driver;
import repository.CabRepository;
import repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;

@Service
public class AdminService {
    private final DriverRepository driverRepo;
    private final CabRepository cabRepo;

    public AdminService(DriverRepository d, CabRepository c){ this.driverRepo = d; this.cabRepo = c; }

    public Driver addDriver(driver d){ d.setActive(true);
        return (Driver) driverRepo.save(d);
    }
    public List<driver>allDrivers(){
        return driverRepo.findAll();
    }

    public cab addCab(cab c){ return cabRepo.save(c); }
    public List<cab> allCabs(){ return cabRepo.findAll(); }
}
