package com.qbc.parses;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.MySerialPort;

public class qbc_parses {
    List<Byte> buffer = new ArrayList<Byte>();
    byte[] data;

    public int getState() {
        return state;
    }

    private int state;
    byte[] by = new byte[5];

    public void Parsing(MySerialPort port) {
        try {
            data = port.Read();
            //state = 0;
            if (data != null) {
                for (byte aByte : data) {
                    buffer.add(aByte);
                }
                //Log.e("TAG", "Parsing: " + buffer.size());
                if (buffer.size() >= 5) {
                    for (int i = 0; i < buffer.size(); i++) {
                        if (buffer.get(i) == 0x55) {
                            if (i + 4 < buffer.size())//测量结束标志包
                            {
                                by = GetData(i, 5, buffer);
                                i = i - 1;
                            }
                        }
                        switch (by[2]) {
                            case 0x01://准备好可以拍照
                                state = 1;
                                break;
                            case 0x02://准备好可以取管
                                state = 2;
                                break;
                            case 0x03://仓门关闭
                                state = 3;
                                break;
                            case 0x04://错误
                                state = 4;
                                break;
                            default:
                                state = 0;
                                break;

                        }
                    }
                }
            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] GetData(int i, int len, List<Byte> list) {
        byte[] data = new byte[len];
        if (list.size() >= len) {
            for (int index = 0; index < len; index++) {
                data[index] = list.get(i);
                list.remove(i);
            }
        } else {
            data = null;
        }
        return data;
    }
}
