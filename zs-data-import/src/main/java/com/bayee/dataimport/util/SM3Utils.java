package com.bayee.dataimport.util;




import org.bouncycastle.util.encoders.Hex;

public class SM3Utils {
    /** SM3 值的长度 */
    private static final int BYTE_LENGTH = 32;
    /** SM3 分组长度 */
    private static final int BLOCK_LENGTH = 64;
    /** 缓冲区长度 */
    private static final int BUFFER_LENGTH = BLOCK_LENGTH * 1;
    /** 缓冲区 */
    private byte[] xBuf = new byte[BUFFER_LENGTH];
    /** 缓冲区偏移量 */
    private int xBufOff;
    /** 初始向量 */
    private byte[] V = SM3.iv.clone();
    private int cntBlock = 0;
    public SM3Utils() { }

    /**
     * SM3 结果输出
     * @param out
     * 保存 SM3 结构的缓冲区
     * @param outOff
     * 缓冲区偏移量
     * @return
     */
    public int doFinal(byte[] out, int outOff) {
        byte[] tmp = doFinal();
        System.arraycopy(tmp, 0, out, 0, tmp.length);
        return BYTE_LENGTH;
    }

    public void update(byte[] in, int inOff, int len) {
        int partLen = BUFFER_LENGTH - xBufOff;
        int inputLen = len;
        int dPos = inOff;
        if (partLen < inputLen) {
            System.arraycopy(in, dPos, xBuf, xBufOff, partLen);
            inputLen -= partLen;
            dPos += partLen;
            doUpdate();
            while (inputLen > BUFFER_LENGTH) {
                System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
                inputLen -= BUFFER_LENGTH;
                dPos += BUFFER_LENGTH; doUpdate();
            }
        }
        System.arraycopy(in, dPos, xBuf, xBufOff, inputLen); xBufOff += inputLen;
    }

    private void doUpdate() {
        byte[] B = new byte[BLOCK_LENGTH];
        for (int i = 0; i < BUFFER_LENGTH; i += BLOCK_LENGTH) {
            System.arraycopy(xBuf, i, B, 0, B.length);
            doHash(B);
        }
        xBufOff = 0;
    }

    private void doHash(byte[] B) {
        byte[] tmp = SM3.CF(V, B);
        System.arraycopy(tmp, 0, V, 0, V.length);
        cntBlock++;
    }

    private byte[] doFinal() {
        byte[] B = new byte[BLOCK_LENGTH];
        byte[] buffer = new byte[xBufOff];
        System.arraycopy(xBuf, 0, buffer, 0, buffer.length);
        byte[] tmp = SM3.padding(buffer, cntBlock);
        for (int i = 0; i < tmp.length; i += BLOCK_LENGTH) {
            System.arraycopy(tmp, i, B, 0, B.length);
            doHash(B);
        }
        return V;
    }

    public void update(byte in) {
        byte[] buffer = new byte[] { in };
        update(buffer, 0, 1);
    }

    public int getDigestSize() { return BYTE_LENGTH; }

    public String sm3(String msg){
        byte[] sm = new byte[32];
        byte[] psw = msg.getBytes();
        update(psw, 0, psw.length);
        doFinal(sm, 0);
        return new String(Hex.encode(sm));
    }

    public static void main(String[] args) {
        String appKey = "99c56c08-edf3-4d7d-a67d-43081632a855";
        String tenantCode = "U0177Z0001";
        String password = "4M2y8HpTv9jn4Vhh";
        String secretKey = "26a5194942953b0d96fd19fd7b6f6c6740aab947583fb41cd0e3c6282859a039";

        System.out.println(new SM3Utils().sm3(tenantCode + "#" + appKey + "#" + password));

        String uuid = "e4eef751-2d89-4466-9695-754b8ec396f0";
        String date = "2021-06-10 10:08:43";

        System.out.println(new SM3Utils().sm3(uuid+"_"+date+"_"+secretKey));
    }
}
