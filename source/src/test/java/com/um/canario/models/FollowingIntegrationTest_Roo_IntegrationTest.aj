// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.um.canario.models;

import com.um.canario.models.Following;
import com.um.canario.models.FollowingDataOnDemand;
import com.um.canario.models.FollowingIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect FollowingIntegrationTest_Roo_IntegrationTest {
    
    declare @type: FollowingIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: FollowingIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: FollowingIntegrationTest: @Transactional;
    
    @Autowired
    private FollowingDataOnDemand FollowingIntegrationTest.dod;
    
    @Test
    public void FollowingIntegrationTest.testCountFollowings() {
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", dod.getRandomFollowing());
        long count = Following.countFollowings();
        Assert.assertTrue("Counter for 'Following' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void FollowingIntegrationTest.testFindFollowing() {
        Following obj = dod.getRandomFollowing();
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Following' failed to provide an identifier", id);
        obj = Following.findFollowing(id);
        Assert.assertNotNull("Find method for 'Following' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Following' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void FollowingIntegrationTest.testFindAllFollowings() {
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", dod.getRandomFollowing());
        long count = Following.countFollowings();
        Assert.assertTrue("Too expensive to perform a find all test for 'Following', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Following> result = Following.findAllFollowings();
        Assert.assertNotNull("Find all method for 'Following' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Following' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void FollowingIntegrationTest.testFindFollowingEntries() {
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", dod.getRandomFollowing());
        long count = Following.countFollowings();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Following> result = Following.findFollowingEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Following' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Following' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void FollowingIntegrationTest.testFlush() {
        Following obj = dod.getRandomFollowing();
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Following' failed to provide an identifier", id);
        obj = Following.findFollowing(id);
        Assert.assertNotNull("Find method for 'Following' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyFollowing(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Following' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void FollowingIntegrationTest.testMergeUpdate() {
        Following obj = dod.getRandomFollowing();
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Following' failed to provide an identifier", id);
        obj = Following.findFollowing(id);
        boolean modified =  dod.modifyFollowing(obj);
        Integer currentVersion = obj.getVersion();
        Following merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Following' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void FollowingIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", dod.getRandomFollowing());
        Following obj = dod.getNewTransientFollowing(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Following' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Following' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Following' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void FollowingIntegrationTest.testRemove() {
        Following obj = dod.getRandomFollowing();
        Assert.assertNotNull("Data on demand for 'Following' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Following' failed to provide an identifier", id);
        obj = Following.findFollowing(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Following' with identifier '" + id + "'", Following.findFollowing(id));
    }
    
}
