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

import static org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)

public class WAServerConfigurationTest {
	private static SWTWorkbenchBot wabot;

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
		if (Utility.isProjExist(Messages.projWithCmpnt)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.projWithCmpnt).select();
			Utility.deleteSelectedProject();
		}
		if (Utility.isProjExist(Messages.test)) {
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@After
	public void cleanUp() throws Exception {
		if (Utility.isProjExist(Messages.projWithCmpnt)) {
			Utility.selProjFromExplorer(Messages.projWithCmpnt).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 179
	public void testSrvConfPropertyPagePresent() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		assertTrue("testSrvConfPropertyPagePresent", wabot.
				checkBox(Messages.jdkChkBxTxt).isEnabled()
				&& wabot.button("OK").isEnabled()
				&& wabot.button(Messages.cnclBtn).isEnabled());
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 180
	public void testJdkChecked() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		boolean valJdk =
				wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isSelected()
    			&& wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valSrv =
				wabot.checkBox(Messages.srvChkBxTxt).isEnabled()
				&& !wabot.radioInGroup(
    					Messages.autoDlSrvCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		assertTrue("testJdkChecked", valJdk
				&& valSrv);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 181
	public void testJdkCheckUncheck() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		boolean isEnabledJdk =
				wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isSelected()
    			&& wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valEnabledSrv =
				wabot.checkBox(Messages.srvChkBxTxt).isEnabled()
				&& !wabot.radioInGroup(
    					Messages.autoDlSrvCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		wabot.tabItem(Messages.jdk).activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		boolean isDisabledJdk =
				!wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& !wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valDisabledSrv =
				!wabot.checkBox(Messages.srvChkBxTxt).isEnabled();
		assertTrue("testJdkChecked", isEnabledJdk
				&& valEnabledSrv
				&& isDisabledJdk
				&& valDisabledSrv);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 182
	public void testJdkPathEmpty() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		// Due to Auto discovery of JDK path
		assertFalse("testJdkPathEmpty", wabot.textInGroup(Messages.emltrGrp).
				getText().equals(""));
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 183
	public void testJdkPathInvalid() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.textInGroup(Messages.emltrGrp).setText(Messages.test);
		wabot.button("OK").click();
		SWTBotShell errShell = wabot.shell(Messages.jdkPathErr);
		errShell.activate();
		String errMsg = errShell.getText();
		assertTrue("testJdkPathInvalid",
				errMsg.equals(Messages.jdkPathErr));
		errShell.close();
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 184
	public void testOkToLeaveJdkPathEmpty() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		// Due to auto discovery of JDK path,
		// we need set path to empty
		// to check error message coming or not.
		wabot.textInGroup(Messages.emltrGrp).setText("");
		SWTBotShell errShell = Utility.selPageNode(Messages.role1, Messages.dbgEndPtName).
				bot().activeShell().activate();
		String errMsg = errShell.getText();
		wabot.button("OK").click();
		SWTBotShell shell = wabot.shell(String.format("%s%s%s",
				Messages.propPageTtl, " ", Messages.role1)).activate();
		String msg = shell.getText();
		// assert setErrorMessage test cases
		// by checking parent shell is active shell
		// and not the error shell
		assertTrue("testOkToLeaveJdkPathEmpty", errMsg.equals(String.format("%s%s",
				Messages.okToLeaveTtl, " "))
				&& msg.equalsIgnoreCase(String.format("%s%s%s",
						Messages.propPageTtl, " ", Messages.role1)));
		shell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 185
	public void testOkToLeaveJdkPathInvalid() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.textInGroup(Messages.emltrGrp).setText(Messages.test);
		SWTBotShell errShell = Utility.selPageNode(Messages.role1, Messages.dbgEndPtName).
				bot().activeShell().activate();
		String errMsg = errShell.getText();
		wabot.button("OK").click();
		SWTBotShell shell = wabot.shell(String.format("%s%s%s",
				Messages.propPageTtl, " ", Messages.role1)).activate();
		String msg = shell.getText();
		// assert setErrorMessage test cases
		// by checking parent shell is active shell
		// and not the error shell
		assertTrue("testOkToLeaveJdkPathInvalid", errMsg.equals(String.format("%s%s",
				Messages.okToLeaveTtl, " "))
				&& msg.equalsIgnoreCase(String.format("%s%s%s",
						Messages.propPageTtl, " ", Messages.role1)));
		shell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 186
	public void testJdkPreConf() throws Exception {
		Utility.createProjectJDK(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		boolean valJdk = wabot.
				checkBox(Messages.jdkChkBxTxt).isChecked()
				&& wabot.textInGroup(Messages.emltrGrp).getText().
				equalsIgnoreCase(Utility.getLoc(Messages.test, "\\cert"))
				&& wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.radioInGroup(
    					Messages.autoDlJdkCldRdBtnLbl, Messages.dlgDownloadGrp).isSelected()
    			&& wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valSrv = wabot.checkBox(Messages.srvChkBxTxt).isEnabled()
				&& !wabot.radioInGroup(
    					Messages.autoDlSrvCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& !wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		assertTrue("testJdkPreConf", valJdk
				&& valSrv);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 187
	public void testJdkPreConfCmpntEnvVar() throws Exception {
		Utility.createProjectJDK(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.cmntPage);
		boolean valCmpnt = wabot.table().cell(0, 0).equalsIgnoreCase(Messages.cmbValCopy)
				&& wabot.table().cell(0, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test, "\\cert"))
				&& wabot.table().cell(0, 3).equalsIgnoreCase(Messages.cmbValCopy);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.path);
		assertTrue("testJdkPreConfCmpntEnvVar", valCmpnt && valEnvVar);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 188
	public void testJdkConf() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		String autoDetectedPath = wabot.textInGroup(Messages.emltrGrp).getText();
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		boolean valCmpnt = wabot.table().cell(0, 0).equalsIgnoreCase(Messages.cmbValCopy)
				&& wabot.table().cell(0, 1).
				equalsIgnoreCase(autoDetectedPath)
				&& wabot.table().cell(0, 3).equalsIgnoreCase(Messages.cmbValNone);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(0, 1).endsWith(new File(autoDetectedPath).getName())
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.path);
		assertTrue("testJdkConf", valCmpnt && valEnvVar);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 189
	public void testModifyJdkPath() throws Exception {
		Utility.createProjectJDK(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.textInGroup(Messages.emltrGrp).setText("");
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.test,
				String.format("%s%s", "\\", Messages.role1)));
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		// require sleep for changes to get reflected in component table
		wabot.sleep(2000);
		boolean valCmpnt = wabot.table().cell(0, 0).equalsIgnoreCase(Messages.cmbValCopy)
				&& wabot.table().cell(0, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test,
						String.format("%s%s", "\\", Messages.role1)))
				&& wabot.table().cell(0, 3).equalsIgnoreCase(Messages.cmbValCopy);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(0, 1).endsWith(Messages.role1)
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.path);
		assertTrue("testModifyJdkPath", valCmpnt && valEnvVar);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 190
	public void testSrvChecked() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		boolean valSrv = wabot.comboBoxWithLabel(Messages.sel).isEnabled()
				&& wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& wabot.link().isEnabled()
				&& wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& wabot.radioInGroup(
    					Messages.autoDlSrvCldRdBtnLbl, Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.labelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.labelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.textWithLabelInGroup(Messages.dlgDlUrlLbl,
    					Messages.dlgDownloadGrp).isEnabled()
    			&& wabot.comboBoxWithLabelInGroup(Messages.dlgDlAccessKey,
    					Messages.dlgDownloadGrp).isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valApp = wabot.table().isEnabled()
				&& wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		assertTrue("testSrvChecked", valSrv
				&& valApp);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 191
	public void testSrvUnChecked() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		boolean valEnableSrv = wabot.comboBoxWithLabel(
				Messages.sel).isEnabled()
				&& wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& wabot.link().isEnabled()
				&& wabot.button(Messages.debugBrowseBtn).isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valEnableApp = wabot.table().isEnabled()
				&& wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		boolean valDisableSrv = !wabot.comboBoxWithLabel(Messages.sel).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& !wabot.link().isEnabled()
				&& !wabot.button(Messages.debugBrowseBtn).isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valDisableApp = !wabot.table().isEnabled()
				&& !wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		assertTrue("testSrvUnChecked", valEnableSrv
				&& valEnableApp
				&& valDisableSrv
				&& valDisableApp);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 192
	public void testSelSrv() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet7);
		wabot.textInGroup(Messages.emltrGrp).setText(Messages.test);
		boolean cmbTxt = wabot.comboBoxWithLabel(Messages.sel).
				getText().equalsIgnoreCase(Messages.cmbValJet7)
				&& wabot.textInGroup(Messages.emltrGrp).
				getText().equalsIgnoreCase(Messages.test);
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet8);
		boolean txtNotEmpty = !wabot.textInGroup(Messages.emltrGrp).
				getText().equalsIgnoreCase("");
		assertTrue("testSelSrv", cmbTxt && txtNotEmpty);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 193
	public void testSrvPathEmpty() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.textInGroup(Messages.emltrGrp).
		setText(Utility.getLoc(Messages.projWithCmpnt, "\\cert"));
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet7);
		wabot.button("OK").click();
		SWTBotShell errShell = wabot.shell(Messages.srvErr);
		errShell.activate();
		String errMsg = errShell.getText();
		assertTrue("testSrvPathEmpty",
				errMsg.equals(Messages.srvErr));
		errShell.close();
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 194
	public void testSrvPathInvalid() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.textInGroup(Messages.emltrGrp).
		setText(Utility.getLoc(Messages.projWithCmpnt, "\\cert"));
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet7);
		wabot.textInGroup(Messages.emltrGrp).
		setText(Messages.test);
		wabot.button("OK").click();
		SWTBotShell errShell = wabot.shell(Messages.srvErr);
		errShell.activate();
		String errMsg = errShell.getText();
		assertTrue("testSrvPathInvalid",
				errMsg.equals(Messages.srvErr));
		errShell.close();
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 195
	public void testOkToLeaveSrvPathEmpty() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.textInGroup(Messages.emltrGrp).
		setText(Utility.getLoc(Messages.projWithCmpnt, "\\cert"));
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet7);
		SWTBotShell errShell = Utility.selPageNode(Messages.role1, Messages.dbgEndPtName).
				bot().activeShell().activate();
		String errMsg = errShell.getText();
		wabot.button("OK").click();
		SWTBotShell shell = wabot.shell(String.format("%s%s%s",
				Messages.propPageTtl, " ", Messages.role1)).activate();
		String msg = shell.getText();
		// assert setErrorMessage test cases
		// by checking parent shell is active shell
		// and not the error shell
		assertTrue("testOkToLeaveSrvPathEmpty",
				errMsg.equals(String.format("%s%s",
				Messages.okToLeaveTtl, " "))
				&& msg.equalsIgnoreCase(String.format("%s%s%s",
						Messages.propPageTtl, " ", Messages.role1)));
		shell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 196
	public void testOkToLeaveSrvPathInvalid() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.textInGroup(Messages.emltrGrp).
		setText(Utility.getLoc(Messages.projWithCmpnt, "\\cert"));
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet7);
		wabot.textInGroup(Messages.emltrGrp).
		setText(Messages.test);
		SWTBotShell errShell = Utility.selPageNode(Messages.role1, Messages.dbgEndPtName).
				bot().activeShell().activate();
		String errMsg = errShell.getText();
		wabot.button("OK").click();
		SWTBotShell shell = wabot.shell(String.format("%s%s%s",
				Messages.propPageTtl, " ", Messages.role1)).activate();
		String msg = shell.getText();
		// assert setErrorMessage test cases
		// by checking parent shell is active shell
		// and not the error shell
		assertTrue("testOkToLeaveSrvPathInvalid",
				errMsg.equals(String.format("%s%s",
				Messages.okToLeaveTtl, " "))
				&& msg.equalsIgnoreCase(String.format("%s%s%s",
						Messages.propPageTtl, " ", Messages.role1)));
		shell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 197
	public void testJdkSrvPreConf() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt,
				Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		boolean valJdk = wabot.checkBox(Messages.jdkChkBxTxt).isChecked()
				&& wabot.textInGroup(Messages.emltrGrp).
				getText().equalsIgnoreCase(Utility.getLoc(Messages.test, "\\deploy"))
				&& wabot.button(Messages.debugBrowseBtn).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valSrv = wabot.checkBox(Messages.srvChkBxTxt).isChecked()
				&& wabot.comboBoxWithLabel(Messages.sel).
				getText().equalsIgnoreCase(Messages.cmbValJet7)
				&& wabot.textInGroup(Messages.emltrGrp).
				getText().equalsIgnoreCase(Utility.getLoc(Messages.test, "\\cert"))
				&& wabot.button(Messages.debugBrowseBtn).isEnabled();
		assertTrue("testJdkSrvPreConf", valJdk
				&& valSrv);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 198
	public void testJdkSrvPreConfCmpntEnvVar() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.cmntPage);
		boolean valCmpnt = 
				// JDK
				wabot.table().cell(0, 0).equalsIgnoreCase(Messages.cmbValCopy)
				&& wabot.table().cell(0, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test, "\\deploy"))
				&& wabot.table().cell(0, 3).equalsIgnoreCase(Messages.cmbValCopy)
				// server.deploy
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.cmbValZip)
				&& wabot.table().cell(1, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test, "\\cert"))
				&& wabot.table().cell(1, 2).equalsIgnoreCase("")
				&& wabot.table().cell(1, 3).equalsIgnoreCase(Messages.cmbValUnzip)
				// server.start
				&& wabot.table().cell(3, 0).equalsIgnoreCase(Messages.cmbValNone)
				&& wabot.table().cell(3, 1).equalsIgnoreCase(Messages.aprootPath)
				&&  wabot.table().cell(3, 3).equalsIgnoreCase(Messages.cmbValExec);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.jetHome)
				&& wabot.table().cell(2, 0).equalsIgnoreCase(Messages.path)
				&& wabot.table().cell(3, 0).equalsIgnoreCase(Messages.srvAppLoc);
		assertTrue("testJdkSrvPreConfCmpntEnvVar", valCmpnt && valEnvVar);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	 @Test
	 //(New Test Cases for 1.7) test case 199
	public void testJdkSrvConf() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		// JDK path detected automatically
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.cmbValJet7);
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.projWithCmpnt, "\\cert"));
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		boolean valCmpnt =
				// JDK
				wabot.table().cell(0, 0).equalsIgnoreCase(Messages.cmbValCopy)
				&& wabot.table().cell(0, 3).equalsIgnoreCase(Messages.cmbValNone)
				// server.deploy
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.cmbValZip)
				&& wabot.table().cell(1, 2).equalsIgnoreCase("")
				&& wabot.table().cell(1, 3).equalsIgnoreCase(Messages.cmbValUnzip)
				// server.start
				&& wabot.table().cell(2, 0).equalsIgnoreCase(Messages.cmbValNone)
				&& wabot.table().cell(2, 1).equalsIgnoreCase(Messages.aprootPath)
				&& wabot.table().cell(2, 3).equalsIgnoreCase(Messages.cmbValExec);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.jetHome)
				&& wabot.table().cell(2, 0).equalsIgnoreCase(Messages.path)
				&& wabot.table().cell(3, 0).equalsIgnoreCase(Messages.srvAppLoc);
		assertTrue("testJdkSrvConf", valCmpnt && valEnvVar);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 200
	public void testModifySrvType() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.tabItem(Messages.srv).activate();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.apache6);
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		// require sleep for changes to get reflected in component table
		wabot.sleep(2000);
		boolean valCmpnt = // server.deploy
				wabot.table().cell(1, 0).equalsIgnoreCase(Messages.cmbValCopy)
				&& wabot.table().cell(1, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test, "\\cert"))
				&& wabot.table().cell(1, 2).equalsIgnoreCase("")
				&& wabot.table().cell(1, 3).equalsIgnoreCase(Messages.cmbValCopy)
				// server.start
				&& wabot.table().cell(2, 0).equalsIgnoreCase(Messages.cmbValNone)
				&& wabot.table().cell(2, 1).equalsIgnoreCase(Messages.aprootPath)
				&& wabot.table().cell(2, 3).equalsIgnoreCase(Messages.cmbValExec)
				&& wabot.table().cell(2, 4).contains(Messages.ctlnHome);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.ctlnHome)
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(2, 0).equalsIgnoreCase(Messages.path)
				&& wabot.table().cell(3, 0).equalsIgnoreCase(Messages.srvAppLoc);
		assertTrue("testModifySrvType", valCmpnt && valEnvVar);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	 @Test
	// (New Test Cases for 1.7) test case 201
	public void testModifySrvPath() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.tabItem(Messages.srv).activate();
		wabot.textInGroup(Messages.emltrGrp).setText("");
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.test, String.format("%s%s", "\\", Messages.role1)));
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		// require sleep for changes to get reflected in component table
		wabot.sleep(2000);
		boolean valCmpnt = // server.deploy
				wabot.table().cell(1, 0).equalsIgnoreCase(Messages.cmbValZip)
				&& wabot.table().cell(1, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test,
						String.format("%s%s", "\\", Messages.role1)))
				&& wabot.table().cell(1, 2).equalsIgnoreCase("")
				&& wabot.table().cell(1, 3).equalsIgnoreCase(Messages.cmbValUnzip);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVar =  wabot.table().cell(0, 0).equalsIgnoreCase(Messages.javaHome)
				&& wabot.table().cell(1, 0).equalsIgnoreCase(Messages.jetHome)
				&& wabot.table().cell(2, 0).equalsIgnoreCase(Messages.path)
				&& wabot.table().cell(3, 0).equalsIgnoreCase(Messages.srvAppLoc);
		assertTrue("testModifySrvPath", valCmpnt && valEnvVar);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 202
	public void testDelJdkPathSrvTypeChanged() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		// Build project
		Utility.selProjFromExplorer(Messages.projWithCmpnt);
		wabot.menu("Project", 1).menu("Build Project").click();
		// Require wait for building project
		wabot.shell("Build Project").activate();
		wabot.waitUntil(shellCloses(wabot.shell("Build Project")));
		wabot.sleep(7000);
		// Check files present in approot
		SWTBotTreeItem proj = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode = proj.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode = workerRoleNode.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts = appRootNode.expand().getNodes();
		boolean valPresent = approotCntnts.contains("deploy")
				&& approotCntnts.contains("cert.zip");
		Utility.selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.textInGroup(Messages.emltrGrp).setText("");
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.test, String.format("%s%s", "\\", Messages.role1)));
		wabot.tabItem(Messages.srv).activate();
		wabot.comboBoxWithLabel(Messages.sel).
		setSelection(Messages.apache6);
		wabot.textInGroup(Messages.emltrGrp).setText("");
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.test, "\\cert"));
		wabot.button("OK").click();
		// Check files got deleted from approot (JDK folder & server file)
		SWTBotTreeItem proj1 = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode1 = proj1.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode1 = workerRoleNode1.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts1 = appRootNode1.expand().getNodes();
		boolean valDel = !approotCntnts1.contains("deploy")
				&& !approotCntnts1.contains("cert.zip");
		assertTrue("testDelJdkPathSrvTypeChanged", valPresent && valDel);
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 203
	public void testDelJdkPathSrvPathChanged() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.apache6);
		// Build project
		Utility.selProjFromExplorer(Messages.projWithCmpnt);
		wabot.menu("Project", 1).menu("Build Project").click();
		// Require wait for building project
		wabot.shell("Build Project").activate();
		wabot.waitUntil(shellCloses(wabot.shell("Build Project")));
		wabot.sleep(7000);
		// Check files present in approot
		SWTBotTreeItem proj = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode = proj.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode = workerRoleNode.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts = appRootNode.expand().getNodes();
		boolean valPresent = approotCntnts.contains("deploy")
				&& approotCntnts.contains("cert");
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		wabot.textInGroup(Messages.emltrGrp).setText("");
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.test, "\\cert"));
		wabot.tabItem(Messages.srv).activate();
		wabot.textInGroup(Messages.emltrGrp).setText("");
		wabot.textInGroup(Messages.emltrGrp).
		typeText(Utility.getLoc(Messages.test, "\\deploy"));
		wabot.button("OK").setFocus();
		wabot.button("OK").click();
		wabot.waitUntil(shellCloses(propShell));
		// Check files got deleted from approot (JDK folder & server folder)
		SWTBotTreeItem proj1 = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode1 = proj1.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode1 = workerRoleNode1.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts1 = appRootNode1.expand().getNodes();
		boolean valDel = !approotCntnts1.contains("deploy")
				&& !approotCntnts1.contains("cert");
		assertTrue("testDelJdkPathSrvPathChanged", valPresent && valDel);
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 204
	public void testUncheckJdk() throws Exception {
		Utility.createProjectJDK(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		boolean valDisableJdk = !wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valDisableSrv = !wabot.checkBox(Messages.srvChkBxTxt).isEnabled();
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		boolean valCmpntRmv = !wabot.table().containsItem(
				Utility.getLoc(Messages.test, "\\cert"));
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVarRmv = !wabot.table().containsItem(Messages.path)
				&& !wabot.table().containsItem(Messages.javaHome);
		assertTrue("testUncheckJdk", valDisableJdk
				&& valDisableSrv
				&& valCmpntRmv
				&& valEnvVarRmv);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 205
	public void testUncheckSrv() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		boolean valDisable = !wabot.comboBoxWithLabel(Messages.sel).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& !wabot.link().isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valDisableApp = !wabot.table().isEnabled()
				&& !wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		wabot.sleep(10000);
		boolean valCmpntRmv = wabot.table().cell(0, 1).
				equalsIgnoreCase(Utility.getLoc(Messages.test, "\\deploy"))
				&& !wabot.table().containsItem(Messages.cmbValZip)
				&& !wabot.table().containsItem(Messages.cmbValUnzip)
				&& !wabot.table().containsItem(Messages.cmbValExec);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVarRmv = wabot.table().containsItem(Messages.path)
				&& wabot.table().containsItem(Messages.javaHome)
				&& !wabot.table().containsItem(Messages.jetHome)
				&& !wabot.table().containsItem(Messages.srvAppLoc);
		assertTrue("testUncheckSrv", valDisable
				&& valDisableApp
				&& valCmpntRmv
				&& valEnvVarRmv);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}
	
	@Test
	// (New Test Cases for 1.7) test case 206
	public void testUncheckJdkSrv() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		boolean valDisable = !wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valDisableSrv =	!wabot.checkBox(Messages.srvChkBxTxt).isEnabled()
				&& !wabot.comboBoxWithLabel(Messages.sel).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& !wabot.link().isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valDisableApp = !wabot.table().isEnabled()
				&& !wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		boolean valCmpntRmv = !wabot.table().containsItem(
				Utility.getLoc(Messages.test, "\\deploy"))
				&& !wabot.table().containsItem(Messages.cmbValZip)
				&& !wabot.table().containsItem(Messages.cmbValUnzip)
				&& !wabot.table().containsItem(Messages.cmbValExec);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		boolean valEnvVarRmv = !wabot.table().containsItem(Messages.path)
				&& !wabot.table().containsItem(Messages.javaHome)
				&& !wabot.table().containsItem(Messages.jetHome)
				&& !wabot.table().containsItem(Messages.srvAppLoc);
		assertTrue("testUncheckJdkSrv", valDisable
				&& valDisableSrv
				&& valDisableApp
				&& valCmpntRmv
				&& valEnvVarRmv);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 207
	public void testModifyJdkCmpnt() throws Exception {
		Utility.createProjectJDK(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.cmntPage);
		wabot.table().select(0);
		wabot.button(Messages.roleEditBtn).click();
		SWTBotShell cmpntEdtShell = wabot.shell(Messages.editNtAlwErTtl);
		cmpntEdtShell.activate();
		assertTrue("testModifyJdkCmpnt",
				cmpntEdtShell.getText().equals(Messages.editNtAlwErTtl));
		wabot.button("OK").click();
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 208
	public void testModifySrvCmpnt() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt,
				Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt,
						Messages.role1, Messages.cmntPage);
		// server.deploy
		wabot.table().select(1);
		wabot.button(Messages.roleEditBtn).click();
		SWTBotShell cmpntEdtShell = wabot.shell(Messages.editNtAlwErTtl);
		cmpntEdtShell.activate();
		boolean valSrvDply = cmpntEdtShell.getText().
				equals(Messages.editNtAlwErTtl);
		wabot.button("OK").click();
		// server.start
		wabot.table().select(2);
		wabot.button(Messages.roleEditBtn).click();
		cmpntEdtShell = wabot.shell(Messages.editNtAlwErTtl);
		cmpntEdtShell.activate();
		boolean valSrvStart = cmpntEdtShell.getText().
				equals(Messages.editNtAlwErTtl);
		wabot.button("OK").click();
		// server.app
		wabot.table().select(3);
		wabot.button(Messages.roleEditBtn).click();
		cmpntEdtShell = wabot.shell(Messages.editNtAlwErTtl);
		cmpntEdtShell.activate();
		boolean valSrvApp = cmpntEdtShell.getText().
				equals(Messages.editNtAlwErTtl);
		wabot.button("OK").click();
		assertTrue("testModifySrvCmpnt", valSrvDply
				&& valSrvStart
				&& valSrvApp);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 209
	public void testCustLinkClick() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.link().click(Messages.custLink);
		SWTBotShell errShell = wabot.shell(Messages.custLinkTtl).activate();
		String msg = errShell.getText();
		errShell.close();
		assertTrue("testCustLinkClick",
				msg.equalsIgnoreCase(Messages.custLinkTtl));
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 210
	public void testCustLinkCancelClick() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.link().click();
		SWTBotShell errShell = wabot.shell(Messages.custLinkTtl).activate();
		String msg = errShell.getText();
		wabot.button(Messages.cnclBtn).click();
		assertTrue("testCustLinkCancelClick", msg.equalsIgnoreCase(Messages.custLinkTtl) 
				&& wabot.activeShell().getText().equalsIgnoreCase(String.format("%s%s%s",
						Messages.propPageTtl, " ", Messages.role1)));
		propShell.close();
	}

	@Test
    // (New Test Cases for 1.7) test case 211
    public void testCustLinkOkClick() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		Utility.selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
        wabot.checkBox(Messages.jdkChkBxTxt).click();
        wabot.tabItem(Messages.srv).activate();
        wabot.checkBox(Messages.srvChkBxTxt).click();
        wabot.link().click();
        SWTBotShell errShell = wabot.shell(Messages.custLinkTtl).activate();
        wabot.button("OK").click();
        wabot.waitUntil(shellCloses(errShell));
        assertTrue("testCustLinkOkClick", wabot.activeEditor().getTitle().
        		equalsIgnoreCase(Messages.cmpntFile));
        wabot.activeEditor().close();
    }
	
	@Test
	// (New Test Cases for 1.7) test case 212
    public void testAddClick() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
        wabot.checkBox(Messages.jdkChkBxTxt).click();
        wabot.tabItem(Messages.srv).activate();
        wabot.checkBox(Messages.srvChkBxTxt).click();
        wabot.tabItem(Messages.app).activate();
        wabot.button(Messages.roleAddBtn).click();
        SWTBotShell appShell = wabot.shell(Messages.addAppTtl).activate();
        String msg = appShell.getText();
        appShell.close();
        assertTrue("testAddClick", msg.equalsIgnoreCase(Messages.addAppTtl));
        propShell.close();
    }
	
	@Test
	// (New Test Cases for 1.7) test case 213
    public void testAddClickFirstRadioEnable() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
        wabot.checkBox(Messages.jdkChkBxTxt).click();
        wabot.tabItem(Messages.srv).activate();
        wabot.checkBox(Messages.srvChkBxTxt).click();
        wabot.tabItem(Messages.app).activate();
        wabot.button(Messages.roleAddBtn).click();
        SWTBotShell appShell = wabot.shell(Messages.addAppTtl).activate();
        assertTrue("testAddClickFirstRadioEnable", wabot.radio(0).isSelected() && 
        		wabot.button(Messages.debugBrowseBtn).isEnabled() 
        		&& !wabot.button("OK").isEnabled());
        appShell.close();
        propShell.close();
    }

	@Test
	// (New Test Cases for 1.7) test case 219
	public void testRmvBtnEnblDsbl() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.tabItem(Messages.app).activate();
		boolean disVal = !wabot.button(Messages.roleRemBtn).isEnabled();
		wabot.table().select(0);
		boolean enblVal = wabot.button(Messages.roleRemBtn).isEnabled();
		assertTrue("testRmvBtnEnblDsbl", disVal
				&& enblVal);
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 220
	public void testRmvBtnYesPressed() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.tabItem(Messages.app).activate();
		wabot.table().select(0);
		wabot.button(Messages.roleRemBtn).click();
		wabot.shell(Messages.appRemTtl).activate();
		wabot.button("Yes").click();
		assertFalse("testRmvBtnYesPressed", wabot.table().
				containsItem(Messages.hlWrld));
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 221
	public void testAddAppCancelPressed() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.tabItem(Messages.app).activate();
		wabot.button(Messages.roleAddBtn).click();
		wabot.shell(Messages.addAppTtl).activate();
		wabot.button(Messages.cnclBtn).click();
		assertTrue("testAddAppCancelPressed", wabot.activeShell().getText().
				equalsIgnoreCase(String.format("%s%s%s",
						Messages.propPageTtl, " ", Messages.role1)));
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 222
	public void testRmvJdkEnvVar() throws Exception {
		Utility.createProjectJDK(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		wabot.table().select(0);
		wabot.button(Messages.roleRemBtn).click();
		SWTBotShell warnShell = wabot.shell(Messages.jdkDsblErrTtl);
		warnShell.activate();
		String msg = warnShell.getText();
		wabot.button("OK").click();
		assertTrue("testRmvJdkEnvVar",
				msg.equalsIgnoreCase(Messages.jdkDsblErrTtl));
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 223
	public void testRmvSrvEnvVar() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		Utility.selPageNode(Messages.role1, Messages.envVarPage);
		wabot.table().select(3);
		wabot.button(Messages.roleRemBtn).click();
		SWTBotShell warnShell = wabot.shell(Messages.jdkDsblErrTtl);
		warnShell.activate();
		String msg = warnShell.getText();
		wabot.button("OK").click();
		assertTrue("testRmvSrvEnvVar",
				msg.equalsIgnoreCase(Messages.jdkDsblErrTtl));
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 224
	public void testUncheckSrvCheckApp() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell =  Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		boolean valDisableSrv = !wabot.comboBoxWithLabel(Messages.sel).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& !wabot.link().isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valDisableApp = !wabot.table().isEnabled()
				&& !wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		boolean appPresent = wabot.table().cell(0, 0).
				equalsIgnoreCase(Messages.hlWrld);
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		boolean cmpntPresent = wabot.table().cell(1, 2).
				equalsIgnoreCase(Messages.hlWrld);
		assertTrue("testUncheckSrvCheckApp", valDisableSrv
				&& valDisableApp
				&& appPresent
				&& cmpntPresent);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 225
	public void testUncheckJdkCheckApp() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		SWTBotShell propShell =  Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		boolean valJdkDisable = !wabot.button(Messages.debugBrowseBtn).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled();
		wabot.tabItem(Messages.srv).activate();
		boolean valSrvDisable = !wabot.checkBox(Messages.srvChkBxTxt).isEnabled()
				&& !wabot.comboBoxWithLabel(Messages.sel).isEnabled()
				&& !wabot.textInGroup(Messages.emltrGrp).isEnabled()
				&& !wabot.link().isEnabled();
		wabot.tabItem(Messages.app).activate();
		boolean valDisableApp = !wabot.table().isEnabled()
				&& !wabot.button(Messages.roleAddBtn).isEnabled()
				&& !wabot.button(Messages.roleRemBtn).isEnabled();
		boolean appPresent = wabot.table().cell(0, 0).
				equalsIgnoreCase(Messages.hlWrld);
		Utility.selPageNode(Messages.role1, Messages.cmntPage);
		boolean cmpntPresent = wabot.table().cell(0, 2).
				equalsIgnoreCase(Messages.hlWrld);
		assertTrue("testUncheckSrvCheckApp", valJdkDisable
				&& valSrvDisable
				&& valDisableApp
				&& appPresent
				&& cmpntPresent);
		propShell.close();
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 251
	public void testRmvBtnNoPressed() throws Exception {
		Utility.createProject(Messages.projWithCmpnt);
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.tabItem(Messages.app).activate();
		wabot.table().select(0);
		wabot.button(Messages.roleRemBtn).click();
		wabot.shell(Messages.appRemTtl).activate();
		wabot.button("No").click();
		assertTrue("testRmvBtnNoPressed", wabot.table().cell(0, 0).
				equalsIgnoreCase(Messages.hlWrld));
		propShell.close();
	}

	@Test
	// (New Test Cases for 1.7) test case 265
	public void testDelJdkSrvUncheck() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		// Build project
		Utility.selProjFromExplorer(Messages.projWithCmpnt);
		wabot.menu("Project", 1).menu("Build Project").click();
		// Require wait for building project
		wabot.shell("Build Project").activate();
		wabot.waitUntil(shellCloses(wabot.shell("Build Project")));
		wabot.sleep(7000);
		// Check files present in approot
		SWTBotTreeItem proj = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode = proj.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode = workerRoleNode.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts = appRootNode.expand().getNodes();
		boolean valPresent = approotCntnts.contains("deploy")
				&& approotCntnts.contains("cert.zip");
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		// First un check server otherwise server check box is not available
		wabot.tabItem(Messages.srv).activate();
		wabot.checkBox(Messages.srvChkBxTxt).click();
		wabot.tabItem(Messages.jdk).activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.button("OK").click();
		// Check files got deleted from approot (JDK folder & server file)
		SWTBotTreeItem proj1 = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode1 = proj1.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode1 = workerRoleNode1.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts1 = appRootNode1.expand().getNodes();
		boolean valDel = !approotCntnts1.contains("deploy")
				&& !approotCntnts1.contains("cert.zip");
		assertTrue("testDelJdkSrvUncheck", valPresent && valDel);
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}

	@Test
	// (New Test Cases for 1.7) test case 266
	public void testDelJdkUncheck() throws Exception {
		Utility.createProjectSrvJDK(Messages.projWithCmpnt, Messages.cmbValJet7);
		// Build project
		Utility.selProjFromExplorer(Messages.projWithCmpnt);
		wabot.menu("Project", 1).menu("Build Project").click();
		// Require wait for building project
		wabot.shell("Build Project").activate();
		wabot.waitUntil(shellCloses(wabot.shell("Build Project")));
		wabot.sleep(7000);
		// Check files present in approot
		SWTBotTreeItem proj = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode = proj.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode = workerRoleNode.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts = appRootNode.expand().getNodes();
		boolean valPresent = approotCntnts.contains("deploy")
				&& approotCntnts.contains("cert.zip");
		SWTBotShell propShell = Utility.
				selectPageUsingContextMenu(Messages.projWithCmpnt, Messages.role1, Messages.serConfPage);
		propShell.activate();
		wabot.checkBox(Messages.jdkChkBxTxt).click();
		wabot.button("OK").click();
		// Check files got deleted from approot (JDK folder & server file)
		SWTBotTreeItem proj1 = Utility.
				selProjFromExplorer(Messages.projWithCmpnt);
		SWTBotTreeItem workerRoleNode1 = proj1.expand().
				getNode(Messages.role1);
		SWTBotTreeItem appRootNode1 = workerRoleNode1.expand().
				getNode(Messages.appRoot);
		List<String> approotCntnts1 = appRootNode1.expand().getNodes();
		boolean valDel = !approotCntnts1.contains("deploy")
				&& !approotCntnts1.contains("cert.zip");
		assertTrue("testDelJdkUncheck", valPresent && valDel);
		if (Utility.isProjExist(Messages.test)) {
			// delete existing project
			Utility.selProjFromExplorer(Messages.test).select();
			Utility.deleteSelectedProject();
		}
	}
}
