

import static org.easymock.EasyMock.expect;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.google.common.collect.Lists;
import com.life.domain.Plan;
import com.life.domain.Role;
import com.life.domain.User;
import com.life.repository.PlanRepository;
import com.life.repository.RoleRepository;
import com.life.repository.UserRepository;
import com.life.service.UserService;

public class UserServiceTest extends UnitilsJUnit4
{
    private UserService userService;
    
    @Mock
    @InjectInto(target = "userService", property = "userRepository")
    private UserRepository userRepository;

    @Mock
    @InjectInto(target = "userService", property = "roleRepository")
    private RoleRepository roleRepository;

    @Mock
    @InjectInto(target = "userService", property = "planRepository")
    private PlanRepository planRepository;

    @Before
    public void init()
    {
        this.userService = new UserService();
    }

    @Test
    public void userCreateTest()
    {
        User newUser = new User();
        newUser.setUsername("username");
        Role role = new Role();
        role.setRole(1);
        newUser.setRole(role);
        expect(this.roleRepository.save(role)).andReturn(role).once();
        expect(this.userRepository.save(newUser)).andReturn(newUser).once();
        EasyMockUnitils.replay();
        
        User expectedUser = this.userService.create(newUser);
        assertReflectionEquals(newUser, expectedUser, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }
    
    @Test
    public void userFriendRelationTest()
    {
        User user1 = new User();
        user1.setUsername("user1");
        Role role = new Role();
        role.setRole(1);
        user1.setRole(role);
        
        User user2 = new User();
        user2.setUsername("user2");
        user2.setRole(role);
        
//        user1.addFriend(user2);
//        user2.addFriend(user1);
        
        expect(this.roleRepository.save(role)).andReturn(role).once();
        expect(this.userRepository.save(user1)).andReturn(user1).once();
        expect(this.userRepository.save(user2)).andReturn(user2).once();
        EasyMockUnitils.replay();
        
        User expectedUser = this.userService.create(user1);
        assertReflectionEquals(user1, expectedUser, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }
    
    
    @Test
    public void userCreatePlanTest()
    {
        User user1 = new User();
        user1.setUsername("user1");
        Role role = new Role();
        role.setRole(1);
        user1.setRole(role);
        
        Plan plan = new Plan();
        plan.setId("1");
        plan.setName("first six month plan");
        
        Plan plan2 = new Plan();
        plan2.setId("3");
        plan2.setName("second six month plan");
        
        user1.addPlan(plan);
        user1.addPlan(plan2);
        
        expect(this.roleRepository.save(role)).andReturn(role).once();
        expect(this.planRepository.save(Lists.newArrayList(plan, plan2))).andReturn(Lists.newArrayList(plan, plan2)).once();
        expect(this.userRepository.save(user1)).andReturn(user1).once();
        EasyMockUnitils.replay();
        
        User expectedUser = this.userService.create(user1);
        assertReflectionEquals(user1, expectedUser, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }
}
