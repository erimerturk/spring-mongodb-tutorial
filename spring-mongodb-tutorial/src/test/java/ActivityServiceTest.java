

import static org.easymock.EasyMock.expect;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.easymock.EasyMockUnitils;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.google.common.collect.Lists;
import com.life.domain.Activity;
import com.life.domain.Feed;
import com.life.domain.Preparation;
import com.life.domain.Risk;
import com.life.repository.ActivityRepository;
import com.life.repository.FeedRepository;
import com.life.repository.PreparationRepository;
import com.life.repository.RiskRepository;
import com.life.service.ActivityService;

public class ActivityServiceTest extends UnitilsJUnit4
{
    private ActivityService activityService;
    
    @Mock
    @InjectInto(target = "activityService", property = "activityRepository")
    private ActivityRepository activityRepository;
    
    @Mock
    @InjectInto(target = "activityService", property = "preparationRepository")
    private PreparationRepository preparationRepository;
    
    @Mock
    @InjectInto(target = "activityService", property = "riskRepository")
    private RiskRepository riskRepository;
    
    @Mock
    @InjectInto(target = "activityService", property = "feedRepository")
    private FeedRepository feedRepository;

    @Before
    public void init()
    {
        this.activityService = new ActivityService();
    }

    @Test
    public void activityCreateTest()
    {
        Activity activity = createDefaultActivity();
        
        Activity expectedActivity = createDefaultActivity();
        expectedActivity.setId(UUID.randomUUID().toString());
        expect(this.riskRepository.save(new ArrayList<Risk>())).andReturn(new ArrayList<Risk>()).once();
        expect(this.preparationRepository.save(new ArrayList<Preparation>())).andReturn(new ArrayList<Preparation>()).once();
        expect(this.feedRepository.save(new ArrayList<Feed>())).andReturn(new ArrayList<Feed>()).once();
        expect(this.activityRepository.save(activity)).andReturn(expectedActivity).once();
        EasyMockUnitils.replay();
        
        Activity result = this.activityService.create(activity);
        assertReflectionEquals(expectedActivity, result, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }
    
    @Test
    public void activityCreateWithPreparationTest()
    {
        Activity activity = createDefaultActivity();
        
        Preparation p1 = new Preparation();
        p1.setName("kitabın fiyatını öğren");
        p1.setFinished(false);
        
        Preparation p2 = new Preparation();
        p2.setName("sahaf bul");
        p2.setFinished(false);
        
        activity.addPrepataion(p1);
        activity.addPrepataion(p2);
        
        Activity expectedActivity = createDefaultActivity();
        expectedActivity.setId(UUID.randomUUID().toString());
        expect(this.preparationRepository.save(Lists.newArrayList(p1, p2))).andReturn(Lists.newArrayList(p1, p2)).once();
        expect(this.riskRepository.save(new ArrayList<Risk>())).andReturn(new ArrayList<Risk>()).once();
        expect(this.feedRepository.save(new ArrayList<Feed>())).andReturn(new ArrayList<Feed>()).once();
        expect(this.activityRepository.save(activity)).andReturn(expectedActivity).once();
        EasyMockUnitils.replay();
        
        Activity result = this.activityService.create(activity);
        assertReflectionEquals(expectedActivity, result, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }
    
    @Test
    public void activityCreateWithRiskTest()
    {
        Activity activity = createDefaultActivity();
        
        Risk r1 = new Risk();
        r1.setName("kitap çok pahalı olabilir");
        
        Risk r2 = new Risk();
        r2.setName("kitabın basım yılı çok eski olduğundan bulunamayabilir");
        
        activity.addRisk(r1);
        activity.addRisk(r2);
        
        Activity expectedActivity = createDefaultActivity();
        expectedActivity.setId(UUID.randomUUID().toString());
        expect(this.riskRepository.save(Lists.newArrayList(r1, r2))).andReturn(Lists.newArrayList(r1, r2)).once();
        expect(this.preparationRepository.save(new ArrayList<Preparation>())).andReturn(new ArrayList<Preparation>()).once();
        expect(this.feedRepository.save(new ArrayList<Feed>())).andReturn(new ArrayList<Feed>()).once();
        expect(this.activityRepository.save(activity)).andReturn(expectedActivity).once();
        EasyMockUnitils.replay();
        
        Activity result = this.activityService.create(activity);
        assertReflectionEquals(expectedActivity, result, ReflectionComparatorMode.IGNORE_DEFAULTS, ReflectionComparatorMode.LENIENT_ORDER);
        
    }
    

    private Activity createDefaultActivity()
    {
        Activity activity = new Activity();
        activity.setDuration(5);
        activity.setName("read book");
        return activity;
    }
}
