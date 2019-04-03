package esp.controller;

import esp.repository.EquipmentCategoryRepository;
import esp.repository.EquipmentRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public abstract class BaseTest {

	@Autowired
	protected EquipmentRepository equipmentRepository;
	@Autowired
	protected EquipmentCategoryRepository equipmentCategoryRepository;

	protected void clear() {
		equipmentRepository.deleteAll();
		equipmentCategoryRepository.deleteAll();
	}

	protected <T> T first(JpaRepository<T, Long> repository) {
		return repository.findAll().stream().findFirst().orElse(null);
	}
}