package project.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import project.configuration.MeanBeanConfiguration;


public class TemplateLinkTest extends MeanBeanConfiguration {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getterAndSetterCorrectness(){
        beanTester.testBean(TemplateLink.class, configuration);
    }

    @Test
    public void equalsAndHashCorrectness() {
        String sameLinkName = "testName";

        TemplateLink templateLinkBlack = new TemplateLink();
        templateLinkBlack.setLinkName(sameLinkName);

        TemplateLink templateLinkRed = new TemplateLink();
        templateLinkRed.setLinkName(sameLinkName);

        Assert.assertTrue(templateLinkBlack.equals(templateLinkRed));
        Assert.assertTrue(templateLinkBlack.hashCode() == templateLinkRed.hashCode());
    }


}