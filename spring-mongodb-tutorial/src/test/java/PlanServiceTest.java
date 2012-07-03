

import static org.easymock.EasyMock.expect;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.life.domain.Plan;
import com.life.repository.PlanRepository;
import com.life.service.PlanService;

public class PlanServiceTest extends UnitilsJUnit4
{
    private PlanService planService;
    
    @Mock
    @InjectInto(target = "planService", property = "planRepository")
    private PlanRepository planRepository;
    
    @Before
    public void init()
    {
        this.planService = new PlanService();
    }

    @Test
    public void planCreateTest()
    {
//        Plan plan = createDefaultPlan();
//        
//        expect(this.planRepository.save(plan)).andReturn(plan).once();
//        EasyMockUnitils.replay();
//        
//        Plan result = this.planService.create(plan);
//        assertReflectionEquals(plan, result, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
//        
    }
    
    @Test
    public void planUpdateTest()
    {
        Plan plan = createDefaultPlan();
        plan.setId(UUID.randomUUID().toString());
        
        expect(this.planRepository.findById(plan.getId())).andReturn(plan).anyTimes();
        expect(this.planRepository.save(plan)).andReturn(plan).once();
        EasyMockUnitils.replay();
        
        Plan original = this.planService.read(plan);
        original.setName("updated 6 aylik plan");
        
        Plan result = this.planService.update(plan);
        assertReflectionEquals(plan, result, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }

    private Plan createDefaultPlan()
    {
        Plan plan = new Plan();
        plan.setName("ilk 6 aylik plan");
        return plan;
    }
    
}
