

import static org.easymock.EasyMock.expect;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.life.domain.Role;
import com.life.domain.User;
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
}
