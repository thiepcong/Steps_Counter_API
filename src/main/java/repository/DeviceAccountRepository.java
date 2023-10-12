package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.DeviceAccount;

@Repository
public interface DeviceAccountRepository extends JpaRepository<Integer, DeviceAccount>{

}
