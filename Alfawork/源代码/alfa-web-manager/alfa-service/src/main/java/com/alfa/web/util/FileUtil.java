package com.alfa.web.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 共通文件操作Util
 */
public final class FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 构造方法
     */
    private FileUtil() {

    }

    /**
     * 从系统Classpath中读取文件
     *
     * @param filePath
     *            文件相对路径
     * @return StringBuffer
     * @throws UnsupportedEncodingException
     */
    public static StringBuffer readFileByClasspath(String filePath) {

        StringBuffer sb = new StringBuffer();
        try {
            // 返回读取指定资源的输入流
            InputStream is = FileUtil.class.getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String s = "";
            try {
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
            } catch (IOException e) {
                log.error("文件读取失败!\n" + ExceptionUtils.getFullStackTrace(e));
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return sb;
    }

    /**
     * 根据文件地址读取文件
     *
     * @param filePath
     *            文件位置
     * @return StringBuffer
     */
    public static StringBuffer readFileByFilePath(String filePath) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            log.info("读取文件:" + filePath);
            log.info("文件内容为:" + sb.toString());
        } catch (FileNotFoundException e) {
            log.error("文件未找到!\n" + ExceptionUtils.getFullStackTrace(e));
        } catch (IOException e) {
            log.error("文件读取失败!\n" + ExceptionUtils.getFullStackTrace(e));
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb;
    }

    /**
     * 文件下载
     *
     * @param rsp
     *            HttpServletResponse对象
     * @param filePath
     *            文件在服务器上的路径
     * @param fileName
     *            用户下载时,保存的文件名
     * @return
     */
    public static InputStream fileDownLoad(HttpServletResponse rsp, String filePath, String fileName) {
        InputStream fis = null;
        OutputStream fos = null;

        File file = new File(filePath);
        if (!file.exists()) {
            return null;
            // 请不要删除，以后有用
            // file.createTempFile(prefix, suffix, directory)
        }
        try {
            // 读取临时文件下载
            fis = new BufferedInputStream(new FileInputStream(filePath));
            // 每次读取的缓冲数组大小
            int readBuffer = 2048;
            // 缓冲数组
            byte[] buffer = new byte[readBuffer];
            int byteRead = 0;
            // 计算最后一次缓冲区的大小
            int lastBufferSize = fis.available() % readBuffer;
            int leftByte = -1;
            // 如果文件的大小,小于缓冲区的值
            if (fis.available() < readBuffer) {
                leftByte = fis.available();
            }
            // 清空response
            rsp.reset();
            // 设置response的Header
            rsp.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gb2312"), "ISO-8859-1"));
            // rep.addHeader("Content-Length", "" + savedPath.length());
            rsp.setContentType("application/octet-stream;charset=GBK");

            fos = new BufferedOutputStream(rsp.getOutputStream());

            // 输出流写出到终端
            while ((byteRead = fis.read(buffer)) != -1) {
                // log.debug(fis.available());
                // 最后一次缓冲
                if (fis.available() == 0) {
                    // 文件大小是缓冲 整倍数
                    if (lastBufferSize == 0) {
                        fos.write(buffer, 0, buffer.length);
                    } else if (leftByte == -1) {
                        fos.write(buffer, 0, lastBufferSize);
                    }
                    // 文件大小小于缓冲
                    else {
                        fos.write(buffer, 0, leftByte);
                    }
                } else {
                    fos.write(buffer, 0, buffer.length);
                }
            }
        } catch (FileNotFoundException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     *
     * @param rsp
     * @param iStream
     */
    public static void ImageOutput(HttpServletResponse rsp, InputStream iStream) {
        InputStream fis = null;
        OutputStream fos = null;

        try {
            // 读取临时文件下载
            fis = new BufferedInputStream(iStream);
            // 每次读取的缓冲数组大小
            int readBuffer = 2048;
            // 缓冲数组
            byte[] buffer = new byte[readBuffer];
            int byteRead = 0;
            // 计算最后一次缓冲区的大小
            int lastBufferSize = fis.available() % readBuffer;
            int leftByte = -1;
            // 如果文件的大小,小于缓冲区的值
            if (fis.available() < readBuffer) {
                leftByte = fis.available();
            }
            // 清空response
            rsp.reset();
            // 设置response的Header
            //rsp.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("gb2312"), "ISO-8859-1"));
            // rep.addHeader("Content-Length", "" + savedPath.length());
            rsp.setContentType("application/octet-stream;charset=GBK");

            fos = new BufferedOutputStream(rsp.getOutputStream());

            // 输出流写出到终端
            while ((byteRead = fis.read(buffer)) != -1) {
                // log.debug(fis.available());
                // 最后一次缓冲
                if (fis.available() == 0) {
                    // 文件大小是缓冲 整倍数
                    if (lastBufferSize == 0) {
                        fos.write(buffer, 0, buffer.length);
                    } else if (leftByte == -1) {
                        fos.write(buffer, 0, lastBufferSize);
                    }
                    // 文件大小小于缓冲
                    else {
                        fos.write(buffer, 0, leftByte);
                    }
                } else {
                    fos.write(buffer, 0, buffer.length);
                }
            }
        } catch (FileNotFoundException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param dataHandler
     * @param file
     */
    public static void saveFile(DataHandler dataHandler, File file) {
        OutputStream os = null;
        try {
            FileUtils.touch(file);
            os = FileUtils.openOutputStream(file, false);
            dataHandler.writeTo(os);
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param fileName
     */
    public static void removeFile(String path, String fileName) {
        File file = new File(path, fileName);
        FileUtils.deleteQuietly(file);
    }

    /**
     * 单线程获取文件/文件夹大小
     * @param file
     * @return
     */
    public static long getFileSizeUsedSingleTread(File file){
        if(file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if(!StringUtil.isNullOrEmpty(children)){
            for(int i=0;i<children.length;i++){
                total += getFileSizeUsedSingleTread(children[i]);
            }
        }
        return total;
    }

    /**
     * 多线程获取文件/文件夹大小
     * @param file
     * @return
     */
    public static long getFileSizeUsedThreadPool(File file) {
        int threadNum = 100;
        final ExecutorService service = Executors.newFixedThreadPool(threadNum);
//		final CompletionService<Long> completionService  = new ExecutorCompletionService<Long>(service);
        try {
            return getTotalSize(service, file);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
            try {
                service.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static long getTotalSize(final ExecutorService service, File file) throws InterruptedException, ExecutionException, TimeoutException{
        if (file.isFile())
            return file.length();
        long total = 0;
        final File[] children = file.listFiles();
        if (children != null) {
            final List<Future<Long>> totalFutures = new ArrayList<Future<Long>>();
            for (final File child : children) {
                totalFutures.add(service.submit(new Callable<Long>() {
                    public Long call() throws InterruptedException,
                            ExecutionException, TimeoutException {
                        return getTotalSize(service, child);
                    }
                }));
            }
            for (final Future<Long> partialTotalFuture : totalFutures)
                total += partialTotalFuture.get(20000, TimeUnit.MILLISECONDS);
        }
        return total;
    }
}
