package br.com.vlsimple.vlsimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
public class VlsimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VlsimpleApplication.class, args);
	}

}

@RestController
@RequestMapping("/simple")
class SimpleController {
	SimpleService simpleService;

	public SimpleController(SimpleService simpleService) {
		this.simpleService = simpleService;
	}

	@GetMapping("/{id}")
	public Simple getSimple(@PathVariable("id") Long id) {
		return simpleService.getSimple(id).orElse(other:null);
	}
}

@Service
class SimpleService {
	SimpleRepository simpleRepository
	
	public SimpleService(SimpleRepository simpleRepository) {
		this.simpleRepository = simpleRepository;
	}
	public Optional<Simple> getSimple(Long id) {
		return simpleRepository.findById(id);
	}
}

interface SimpleRepository extends JpaRepository<Simple, Long> {
 	
}
