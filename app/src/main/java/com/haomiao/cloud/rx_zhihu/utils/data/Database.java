// (c)2016 Flipboard Inc, All Rights Reserved.

package com.haomiao.cloud.rx_zhihu.utils.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haomiao.cloud.rx_zhihu.CApplication;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class Database {
    private static String DATA_FILE_NAME = "data.db";

    private static Database INSTANCE;

    File dataFile = new File(CApplication.getInstance().getFilesDir(), DATA_FILE_NAME);
    Gson gson = new Gson();

    private Database() {
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public ArrayList<ZhiHuDailyItem> readItems() {
        // Hard code adding some delay, to distinguish reading from memory and reading disk clearly
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Reader reader = new FileReader(dataFile);
            return gson.fromJson(reader, new TypeToken<ArrayList<ZhiHuDailyItem>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeItems(ArrayList<ZhiHuDailyItem> items) {
        String json = gson.toJson(items);
        try {
            if (!dataFile.exists()) {
                try {
                    dataFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Writer writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        dataFile.delete();
    }
}
