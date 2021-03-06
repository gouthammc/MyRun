/**
 * Copyright 2014 Persistent Systems Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.persistent.uiautomation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(SWTBotJunit4ClassRunner.class)
public class WAPropertyTest {

    private static SWTWorkbenchBot	wabot;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wabot = new SWTWorkbenchBot();
        try {
            wabot.viewByTitle(Messages.welCm).close();
        } catch (Exception e) {
        }
    }

    @Before
    public void setUp() throws Exception {
        wabot.closeAllShells();
        if(Utility.isProjExist(Messages.projName)) {
            //delete existing project
            Utility.selProjFromExplorer(Messages.projName).select();
            Utility.deleteSelectedProject();
        }
        Utility.createProject(Messages.projName);
    }

    @After
    public void cleanUp() throws Exception {
        if(Utility.isProjExist(Messages.projName)) {
            Utility.selProjFromExplorer(Messages.projName).select();
            Utility.deleteSelectedProject();
            }
    }


    @Test
     public void testWaView() throws Exception {
        Utility.getPropertyPage(Messages.projName, Messages.waPage);
        wabot.sleep(5000);
        assertEquals("AzureDeploymentProject", 
        		wabot.textWithLabel("Service name:").getText());
        String [] actTtems = wabot.comboBox().items();
        String [] expItems = {Messages.testInEm, Messages.dplyToCld};
        assertArrayEquals("testWaView", expItems, actTtems);
        wabot.button("OK").click();
    }


    @Test
    public void testWaChangeComboValueCloud() throws Exception {
        Utility.getPropertyPage(Messages.projName, Messages.waPage);
        wabot.comboBox().setSelection(Messages.dplyToCld);
        wabot.button("OK").click();
        Utility.getPropertyPage(Messages.projName, Messages.waPage);
        assertEquals("testWaChangeComboValueCloud", 
        		Messages.dplyToCld, wabot.comboBox().getText());
        wabot.button("OK").click();
    }

    @Test
    public void testWaChangeComboValueEmu() throws Exception {
        Utility.getPropertyPage(Messages.projName, Messages.waPage);
        wabot.comboBox().setSelection(Messages.testInEm);
        wabot.button("OK").click();
        Utility.getPropertyPage(Messages.projName, Messages.waPage);
        assertEquals("testWaChangeComboValueEmu", 
        		Messages.testInEm, wabot.comboBox().getText());
        wabot.button("OK").click();
    }
}
