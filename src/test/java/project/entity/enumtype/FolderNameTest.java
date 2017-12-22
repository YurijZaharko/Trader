package project.entity.enumtype;

import org.junit.Assert;
import org.junit.Test;

public class FolderNameTest {
    @Test
    public void enumTest() throws Exception {
        Assert.assertNotNull(FolderName.IMAGE);
        Assert.assertNotNull(FolderName.TEXT_TEMPLATE);
        Assert.assertNotNull(FolderName.USER_FILES);
    }
}