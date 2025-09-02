package ar.edu.huergo.jsanchezortega.gymness.repository.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
@Repository
public interface PlanRepository extends CrudRepository<Plan, Long>{
}
