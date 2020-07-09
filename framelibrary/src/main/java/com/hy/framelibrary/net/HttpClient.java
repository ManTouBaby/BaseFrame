package com.hy.framelibrary.net;

import android.os.Environment;

import java.io.File;

public class HttpClient {
    private long readTimeout;
    private long writeTimeout;
    private long connectTimeout;
    private Level printLevel;
    private String cachePath;

    public HttpClient(long readTimeout, long writeTimeout, long connectTimeout, Level printLevel, String cachePath) {
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        this.connectTimeout = connectTimeout;
        this.printLevel = printLevel;
        this.cachePath = cachePath;
    }

    public String getCachePath() {
        return cachePath;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public Level getPrintLevel() {
        return printLevel;
    }

    public static class Builder {
        long readTimeout = 30000;
        long writeTimeout = 30000;
        long connectTimeout = 30000;
        Level printLevel = Level.NONE;
        String cachePath;

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setCachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        public Builder setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setPrintLevel(Level printLevel) {
            this.printLevel = printLevel;
            return this;
        }

        public HttpClient build() {
            if (cachePath == null) {
                cachePath = "hy-file";
            }
            File file = new File(cachePath);
            if (!file.exists()) {
                file.mkdirs();
                System.out.println("文件夹创建成功");
            }

            return new HttpClient(readTimeout, writeTimeout, connectTimeout, printLevel, cachePath);
        }
    }

    public enum Level {
        NONE,       //不打印log
        BASIC,      //只打印 请求首行 和 响应首行
        HEADERS,    //打印请求和响应的所有 Header
        BODY        //所有数据全部打印
    }
}
