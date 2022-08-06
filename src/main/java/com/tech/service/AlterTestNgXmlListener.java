package com.tech.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.tech.annotations.Platform;
import com.tech.annotations.Testable;
import com.tech.config.TestConfig;
import com.tech.constants.PlatformType;
import com.tech.constants.TestType;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AlterTestNgXmlListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("Test Automation");

        String group = TestConfig.getTestGroup();
        if(!group.equals(TestType.REGRESSION)){
            xmlSuite.addIncludedGroup(group);
        }

        xmlSuite.setThreadCount(getParallelThreadCount());
        xmlSuite.setParallel(XmlSuite.ParallelMode.CLASSES);
        xmlSuite.setVerbose(10);

        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName("All Test Automation");

        Multimap<String, String> testableClasses = findTestableClasses();
        String selectedTestClass = TestConfig.getTestClass();
        List<XmlClass> xmlClasses = new LinkedList<>();
        testableClasses.keySet().stream()
                .filter(testName -> {
                            if (selectedTestClass.equals("none")) return true;
                            else if (selectedTestClass.equals(testName)) return true;
                            else return false;
                        }
                )
                .forEach(
                        testName -> {
                            xmlClasses.addAll(getXmlClasses(testableClasses, testName));

                        }
                );
        xmlTest.setXmlClasses(xmlClasses);
        suites.add(xmlSuite);
    }

    private List<XmlClass> getXmlClasses(Multimap<String, String> testableClasses, String testName) {
        List<XmlClass> xmlClasses = new ArrayList<>();
        testableClasses.get(testName).stream()
                .forEach(
                        testClass -> {
                            xmlClasses.add(new XmlClass(testClass));
                        }
                );
        return xmlClasses;
    }

    /**
     * Used Java Reflection to get the all testable annotated classes info.
     * @return
     */
    private Multimap<String, String> findTestableClasses(){
        Multimap<String, String> testNameToTestClass = ArrayListMultimap.create();
        Reflections reflections = new Reflections("com.tech");
        String selectedPlatform = TestConfig.getTestPlatform();

        for (Class<?> cl : reflections.getTypesAnnotatedWith(Testable.class)) {
            Testable testable = cl.getAnnotation(Testable.class);
            Platform platform = cl.getSuperclass().getAnnotation(Platform.class);

            if (selectedPlatform.equals(PlatformType.ALL)){
                testNameToTestClass.put(testable.testName(), cl.getName());
            }else if (platform.name().equals(selectedPlatform)){
                testNameToTestClass.put(testable.testName(), cl.getName());
            }
        }
        return testNameToTestClass;
    }

    /**
     * Its based on current system's cores.
     * thread count = no. of cores * 1.5
     * Also it's depend on memory and some research on this.
     * @return
     */
    private static int getParallelThreadCount(){
        int cores = Runtime.getRuntime().availableProcessors();
        if (cores == 0){
            log.error("No free cores are available.");
            return 1;
        }
        int threads = (int) Math.floor(cores * 1.5);
        return threads;
    }
}
