package com.anicloud.sunny.infrastructure.persistence.util;

import java.io.*;

/**
 * Created by MRK on 2016/3/11.
 */
public class SerializeUtil {

    /**
     *
     * @param obj
     * @return byte[]
     */
    public static byte[] toSerialize(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byte[] objByte = bos.toByteArray();
            return objByte;
        } catch (IOException e) {
            throw new RuntimeException("Serializable failed...");
        }
    }

    /**
     *
     * @param  objByte
     * @return Object
     */
    public static Object toObject(byte[] objByte) {
        if (objByte == null) {
            return null;
        }
        ObjectInputStream ois = null;
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(objByte);
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("unSerializable failed...");
        }
    }
}
