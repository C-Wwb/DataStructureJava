package cn.edu.bistu.cs;

import java.io.*;
import java.util.Base64;

public class HTSerDes implements SerDes {
    @Override
    public byte[] serBin(Serializable serializable) throws IOException {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(ba);
        oos.writeObject(serializable);//将对象t序列化为字节数组
        return ba.toByteArray();//return 序列化后的字节数组
    }

    @Override
    public String serTxt(Serializable serializable) throws IOException {
        //将对象t序列化为一个字符串。
        HTSerDes ht = new HTSerDes();
        byte[] b = ht.serBin(serializable);
        //先使用serBin方法将对象t序列化为字节数组
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        //再将字节数组用Base64编码为字符串
        return encoder.encodeToString(b);
    }

    @Override
    public Serializable des(byte[] bin) throws IOException, ClassNotFoundException {
        /**
         * 将序列化后的字节数组反序列化为一个对象
         * @param bin
         * @return
         */
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream inputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bin);
            inputStream = new ObjectInputStream(byteArrayInputStream);
            return (Serializable) inputStream.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        /*
        finally
        {
            if (null != byteInputStream)
            {
                try
                {
                    byteInputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            if (null != inputStream)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
        }
         */
    }

    @Override
    public Serializable des(String text) throws IOException, ClassNotFoundException {
        /**
         * 将序列化后的字符串反序列化为一个对象，
         * 字符串应该是使用serText方法序列化得到的
         * @param text
         * @return
         */
        HTSerDes ht = new HTSerDes();
        byte[] b = ht.serBin(text);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return (Serializable) object;
    }

    @Override
    public boolean serToFile(Serializable serializable, String path, String file) throws IOException {
        /**
         * 将对象序列化并写入磁盘文件。
         * 提示：可以使用serBin将对象t序列化，
         * 然后将序列化后的字节数组写入文件
         * @param t
         * @param path
         * @param file
         * @return
         */
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream != null)
            {
                try {
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Serializable desFromFile(String path, String file) throws IOException, ClassNotFoundException {
        /**
         * 将序列化后的对象从磁盘文件中读出。
         * 提示：可以首先从磁盘中读出字节数组，
         * 然后使用des方法将对象反序列化
         * @param path
         * @param file
         * @return
         */
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Serializable)objectInputStream.readObject();
    }
}
