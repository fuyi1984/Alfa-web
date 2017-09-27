package com.alfa.web;

import com.alfa.web.util.PropertiesUtil;
import org.junit.Before;
import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * jython库测试
 */
public class JavaExecutePython extends TestBase {

    //public PythonInterpreter interpreter ;
    public String basePath = JavaExecutePython.class.getResource("").getPath();

    //@Before
    //public void start(){
    //    interpreter = new PythonInterpreter();
    //}

    //在java中调用本机python脚本中的函数
    @Test
    public void test02() throws FileNotFoundException {
        PythonInterpreter interpreter=new PythonInterpreter();
        /*InputStream filepy = new FileInputStream(PropertiesUtil.getProperty("python.dir.location")+"my_util.py");*/
        interpreter.execfile(PropertiesUtil.getProperty("python.dir.location")+"my_util.py");
        /*PyFunction func = (PyFunction) interpreter.get("adder",PyFunction.class);

        int a = 2010, b = 2;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("anwser = " + pyobj.toString());*/
    }

}
