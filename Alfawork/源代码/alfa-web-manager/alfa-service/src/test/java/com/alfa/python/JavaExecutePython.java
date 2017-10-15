package com.alfa.python;

import com.alfa.web.util.PropertiesUtil;
import org.junit.Before;
import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.*;
import java.util.Properties;

/**
 * jython库测试
 */
public class JavaExecutePython {

    public PythonInterpreter interpreter ;
    public String basePath = JavaExecutePython.class.getResource("").getPath();

    @Before
    public void start(){
        interpreter = new PythonInterpreter();
    }

    //在java中调用本机python脚本中的函数
    @Test
    public void test02() throws IOException {
        //InputStream filepy = new FileInputStream(new File("d:\\my_util.py"));

        Properties props = new Properties();
        props.put("python.home","D:/Python27/Lib");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site","false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        interpreter.execfile("d:/my_util.py");
//        PyFunction func = (PyFunction) interpreter.get("adder",PyFunction.class);
//
//        int a = 2010, b = 2;
//        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
//        System.out.println("anwser = " + pyobj.toString());
        //filepy.close();

        //interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
        //interpreter.exec("print days;");
    }

}
